package br.com.fiap.gsJava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.gsJava.dtos.emergencia.EmergenciaRequestDTO;
import br.com.fiap.gsJava.dtos.emergencia.EmergenciaRequestPutDTO;
import br.com.fiap.gsJava.dtos.emergencia.EmergenciaResponseDTO;
import br.com.fiap.gsJava.entities.Emergencia;
import br.com.fiap.gsJava.entities.Local;
import br.com.fiap.gsJava.entities.Usuario;
import br.com.fiap.gsJava.enums.LocalEventoEnum;
import br.com.fiap.gsJava.exceptions.BusinessException;
import br.com.fiap.gsJava.exceptions.EntityNotFoundException;
import br.com.fiap.gsJava.repositories.EmergenciaRepository;
import br.com.fiap.gsJava.repositories.LocalRepository;
import br.com.fiap.gsJava.repositories.UsuarioRepository;

@Service
public class EmergenciaService {

	@Autowired
	private EmergenciaRepository emergenciaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LocalRepository localRepository;

	public List<EmergenciaResponseDTO> findAll() {
		List<Emergencia> entities = emergenciaRepository.findAll();
		List<EmergenciaResponseDTO> dtos = new ArrayList<>();
		
		for (Emergencia entity : entities) {
			if(entity.getStatus()) {
				EmergenciaResponseDTO dto = new EmergenciaResponseDTO(entity);
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	public EmergenciaResponseDTO findById(Long id) {
		Optional<Emergencia> optional = emergenciaRepository.findById(id);	
		
		if (optional.isEmpty() || !optional.get().getStatus()) {
			throw new EntityNotFoundException("Emergência não encontrada");
		}
				
		EmergenciaResponseDTO dto = new EmergenciaResponseDTO(optional.get());
		
		return dto;
	
	}

	public EmergenciaResponseDTO save(EmergenciaRequestDTO emergenciaDTO) {

	    Usuario usuario = usuarioRepository.findById(emergenciaDTO.getUsuario().getId())
	            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

	    Local local = localRepository.findById(emergenciaDTO.getLocal().getId())
	            .orElseThrow(() -> new EntityNotFoundException("Local não encontrado"));

	    if(!local.getStatus()) {
	    	throw new EntityNotFoundException("Local não encontrado");
	    }
	    
	    // o ID do local deve ser ter o atributo 'evento' do tipo EMERGENCIA
	    if(local.getEvento() != LocalEventoEnum.EMERGENCIA) {
	        throw new BusinessException("O ID do Local deve ser um Local cujo Evento seja do tipo 'EMERGENCIA'");
	    }
	    
	    Emergencia entity = new Emergencia(emergenciaDTO);
	    entity.setUsuario(usuario);
	    entity.setLocal(local);

	    Emergencia entitySave = emergenciaRepository.save(entity);

	    return new EmergenciaResponseDTO(entitySave);
	}



	public EmergenciaResponseDTO update(Long id, EmergenciaRequestPutDTO emergenciaDTO) {

	    Optional<Emergencia> optional = emergenciaRepository.findById(id);

	    if (optional.isEmpty() || !optional.get().getStatus()) {
	        throw new EntityNotFoundException("Emergência não encontrada");
	    }

	    Emergencia emergenciaExistente = optional.get();    

	    // só permite que a mensagem seja alterada
	    // data, status e usuario são definidos somente criação da emergencia
	    emergenciaExistente.setMensagem(emergenciaDTO.getMensagem());
	    
	    Emergencia entity = emergenciaRepository.save(emergenciaExistente);

	    return new EmergenciaResponseDTO(entity);
	}


	
	public EmergenciaResponseDTO deleteById(Long id) {
		Optional<Emergencia> entity = emergenciaRepository.findById(id);
		
		if (entity.isEmpty() || !entity.get().getStatus()) {
			throw new EntityNotFoundException("Emergência não encontrada");
		}
		
		emergenciaRepository.deleteById(id);
	
		return new EmergenciaResponseDTO(entity.get());
	}
}
