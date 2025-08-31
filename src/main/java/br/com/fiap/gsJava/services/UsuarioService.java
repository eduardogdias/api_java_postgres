package br.com.fiap.gsJava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.gsJava.dtos.usuario.UsuarioDTO;
import br.com.fiap.gsJava.dtos.usuario.UsuarioRequestDTO;
import br.com.fiap.gsJava.entities.Selo;
import br.com.fiap.gsJava.entities.Usuario;
import br.com.fiap.gsJava.exceptions.EntityNotFoundException;
import br.com.fiap.gsJava.repositories.SeloRepository;
import br.com.fiap.gsJava.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SeloRepository seloRepository;

	public List<UsuarioDTO> findAll() {
		List<Usuario> entities = new ArrayList<>();
		entities = usuarioRepository.findAll();
		
		List<UsuarioDTO> dtos = new ArrayList<>();
		
		for (Usuario usuario : entities) {  //convertendo cada Entity em um DTO
			UsuarioDTO dto = new UsuarioDTO(usuario);
			dtos.add(dto);
		}
		
		return dtos; 
	}

	public UsuarioDTO findById(Long id) {
		Usuario entity = usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Usuário não encontrado"));
		UsuarioDTO dto = new UsuarioDTO(entity);
		return dto;

	}
	

	public UsuarioDTO save(UsuarioRequestDTO usuarioRequestDTO) {

	    // Carrega o selo do banco
		Optional<Selo> optionalSelo = seloRepository.findById(usuarioRequestDTO.getSeloId());
		if (optionalSelo.isEmpty()) {
		    //throw new SeloNotFoundException(usuarioRequestDTO.getSeloId());
			throw new EntityNotFoundException("Selo de ID " + usuarioRequestDTO.getSeloId() + " não encontrado");
		}

	    Usuario entity = new Usuario(usuarioRequestDTO);
	    entity.setSelo(optionalSelo.get()); // seta o selo carregado

	    Usuario usuario = usuarioRepository.save(entity);

	    return findById(usuario.getId());
	}


		
		

	public UsuarioDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) {
	    Usuario usuarioExistente = usuarioRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

	    usuarioExistente.setNome(usuarioRequestDTO.getNome());
	    usuarioExistente.setEmail(usuarioRequestDTO.getEmail());
	    usuarioExistente.setSenha(usuarioRequestDTO.getSenha());
	    usuarioExistente.setCidade(usuarioRequestDTO.getCidade());
	    usuarioExistente.setEstado(usuarioRequestDTO.getEstado());
	    usuarioExistente.setTelefone(usuarioRequestDTO.getTelefone());

	    // Busca o selo
	    Selo selo = seloRepository.findById(usuarioRequestDTO.getSeloId())
	            .orElseThrow(() -> 
	            //new SeloNotFoundException(usuarioRequestDTO.getSeloId())
	            new EntityNotFoundException("Selo de ID " + usuarioRequestDTO.getSeloId() + " não encontrado")
	            		);

	    usuarioExistente.setSelo(selo);

	    Usuario entity = usuarioRepository.save(usuarioExistente);
	    UsuarioDTO dto = new UsuarioDTO(entity);
	    return dto;
	}


	public Usuario deleteById(Long id) {

		Optional<Usuario> optional = usuarioRepository.findById(id);

		if (optional.isEmpty()) {
			return usuarioRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException("Usuario não encontrado"));
		}

		Usuario Usuario = optional.get();
		usuarioRepository.deleteById(id);
		return Usuario;
	}
}
