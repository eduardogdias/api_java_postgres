package br.com.fiap.gsJava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.gsJava.dtos.publicacao.PublicacaoRequestDTO;
import br.com.fiap.gsJava.dtos.publicacao.PublicacaoRequestPutDTO;
import br.com.fiap.gsJava.dtos.publicacao.PublicacaoResponseDTO;
import br.com.fiap.gsJava.entities.Publicacao;
import br.com.fiap.gsJava.entities.Usuario;
import br.com.fiap.gsJava.exceptions.EntityNotFoundException;
import br.com.fiap.gsJava.repositories.PublicacaoRepository;
import br.com.fiap.gsJava.repositories.UsuarioRepository;

@Service
public class PublicacaoService {

	@Autowired
	private PublicacaoRepository publicacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<PublicacaoResponseDTO> findAll() { //listar todas as publicações com status 'true' (listagem padrão)
		List<Publicacao> publicacoes = publicacaoRepository.findAll();
		List<PublicacaoResponseDTO> publicacoesDTO = new ArrayList<>();
		
		for (Publicacao entity : publicacoes) {   //convertendo a lista de entidades em dtos
			if(entity.getStatus()) {
				PublicacaoResponseDTO dto = new PublicacaoResponseDTO(entity);
				publicacoesDTO.add(dto);
			}			
		}
		
		return publicacoesDTO;
	}
	
	

	public PublicacaoResponseDTO findById(Long id) {
		Optional<Publicacao> optionalPublicacao = publicacaoRepository.findById(id);
				
				
		if (optionalPublicacao.isEmpty() || !optionalPublicacao.get().getStatus()) { //verifica se a publicação está com status 'false' também
			throw new EntityNotFoundException("Publicação não encontrada");
		}
				
		PublicacaoResponseDTO dto = new PublicacaoResponseDTO(optionalPublicacao.get());
		
		return dto;
	
	}

	
	public List<PublicacaoResponseDTO> findAllByStatus(boolean status) {
	    List<Publicacao> publicacoes = publicacaoRepository.findByStatus(status);

	    List<PublicacaoResponseDTO> listaDto = new ArrayList<>();

	    for (Publicacao publicacao : publicacoes) {
	        PublicacaoResponseDTO dto = new PublicacaoResponseDTO(publicacao);
	        listaDto.add(dto);
	    }

	    return listaDto;
	}

	
	
	public PublicacaoResponseDTO save(PublicacaoRequestDTO publicacaoRequestDTO) {

	    // Carrega o usuario do banco
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(publicacaoRequestDTO.getUsuario().getId());
		if (optionalUsuario.isEmpty()) {
			throw new EntityNotFoundException("Usuário de ID " + publicacaoRequestDTO.getUsuario().getId() + " não encontrado");
		}

		Usuario usu = new Usuario(); //completando os dados de usuário na publicação
		usu.setId(optionalUsuario.get().getId());
		usu.setNome(optionalUsuario.get().getNome());
		usu.setEmail(optionalUsuario.get().getEmail());
		usu.setSenha(optionalUsuario.get().getSenha());
		usu.setCidade(optionalUsuario.get().getCidade());
		usu.setEstado(optionalUsuario.get().getEstado());
		usu.setTelefone(optionalUsuario.get().getTelefone());
		usu.setSelo(optionalUsuario.get().getSelo());
		publicacaoRequestDTO.setUsuario(usu);
		
	    Publicacao entity = new Publicacao(publicacaoRequestDTO);
	    publicacaoRepository.save(entity);

	    PublicacaoResponseDTO response = new PublicacaoResponseDTO(entity);
	    
	    return response;
	}
	
	
	
	public PublicacaoResponseDTO update(Long id, PublicacaoRequestPutDTO publicacaoRequestPutDTO) {

		Optional<Publicacao> optionalPublicacao = publicacaoRepository.findById(id);

		if (optionalPublicacao.isEmpty() || !optionalPublicacao.get().getStatus()) {
			throw new EntityNotFoundException("Publicação não encontrada");
		}
		
		Publicacao entity = optionalPublicacao.get();
		entity.setTitulo(publicacaoRequestPutDTO.getTitulo());
		entity.setDescricao(publicacaoRequestPutDTO.getDescricao());
		entity.setImagem(publicacaoRequestPutDTO.getImagem());
		entity.setCidade(publicacaoRequestPutDTO.getCidade());
		entity.setEstado(publicacaoRequestPutDTO.getEstado());
		//a 'data' não é alterada, é definida pelo dia que foi postado. E o 'status' o 'usuario' também não são alterados
		
		publicacaoRepository.save(entity);

		PublicacaoResponseDTO dto = new PublicacaoResponseDTO(entity);
		return dto;
	}


	
	public PublicacaoResponseDTO deleteById(Long id) { //ao deletar a publi é trocado o 'status' para 'false', mas ela ainda vai estar no banco

		Optional<Publicacao> optional = publicacaoRepository.findById(id);

		if (optional.isEmpty() || !optional.get().getStatus()) { //não é possivel deletar uma publi que já foi deletada (que o 'status' está com o valor 'false')
			throw new EntityNotFoundException("Publicação não encontrada ou já deletada");
		}
		
		Publicacao publicacao = optional.get();
		publicacao.setStatus(false);
		publicacaoRepository.save(publicacao);
		
		PublicacaoResponseDTO dto = new PublicacaoResponseDTO(publicacao);
		
		return dto;
	}
}
