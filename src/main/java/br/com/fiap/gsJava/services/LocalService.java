package br.com.fiap.gsJava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.gsJava.dtos.local.LocalRequestDTO;
import br.com.fiap.gsJava.dtos.local.LocalResponseDTO;
import br.com.fiap.gsJava.entities.Local;
import br.com.fiap.gsJava.exceptions.EntityNotFoundException;
import br.com.fiap.gsJava.repositories.LocalRepository;

@Service
public class LocalService {

	@Autowired
	private LocalRepository localRepository;

	public List<LocalResponseDTO> findAll() {
		List<Local> entities = localRepository.findAll();
		List<LocalResponseDTO> dtos = new ArrayList<>();
		
		for (Local entity : entities) {
			if(entity.getStatus()) {
				LocalResponseDTO dto = new LocalResponseDTO(entity);
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	public List<LocalResponseDTO> findByStatus(boolean status){
		List<Local> entities = localRepository.findByStatus(status);
		List<LocalResponseDTO> dtos = new ArrayList<>();
		for (Local entity : entities) {
			LocalResponseDTO dto = new LocalResponseDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public LocalResponseDTO findById(Long id) {
		Optional<Local> optional = localRepository.findById(id);	
		
		if (optional.isEmpty() || !optional.get().getStatus()) {
			throw new EntityNotFoundException("Local não encontrado");
		}
				
		LocalResponseDTO dto = new LocalResponseDTO(optional.get());
		
		return dto;
	
	}

	public LocalResponseDTO save(LocalRequestDTO localDTO) {	
		Local entity = new Local(localDTO);
		Local entitySave = localRepository.save(entity);
		
		LocalResponseDTO dto = new LocalResponseDTO(entitySave);
		
		return dto;
	}

	
	public LocalResponseDTO update(Long id, LocalRequestDTO localDTO) {
		Optional<Local> optional = localRepository.findById(id);

		if (optional.isEmpty() || !optional.get().getStatus()) {
			throw new EntityNotFoundException("Local não encontrado");
		}

		Local localExistente = optional.get();	
		localExistente.setRua(localDTO.getRua());
		localExistente.setNumero(localDTO.getNumero());
		localExistente.setCidade(localDTO.getCidade());
		localExistente.setEstado(localDTO.getEstado());
		localExistente.setEvento(localDTO.getEvento());
		
		Local entity = localRepository.save(localExistente);
		LocalResponseDTO dto = new LocalResponseDTO(entity);
		
		return dto;
		
	}

	
	public LocalResponseDTO deleteById(Long id) {
		Optional<Local> entity = localRepository.findById(id);
		
		if (entity.isEmpty() || !entity.get().getStatus()) {
			throw new EntityNotFoundException("Local não encontrado ou já deletado");
		}
		
		entity.get().setStatus(false);
		localRepository.save(entity.get());
	
		return new LocalResponseDTO(entity.get());
	}
}
