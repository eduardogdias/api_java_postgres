package br.com.fiap.gsJava.dtos.publicacao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.gsJava.entities.Publicacao;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PublicacaoResponseDTO {

	private Long id;
	private String titulo;
	private String descricao;
	private String imagem;
	private String cidade;
	private String estado;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date data;

	private Boolean status;
	private Long votosConfiavel;
	private Long votosFalso;
	private Long usuarioId;
	private String usuarioNome;
	private String selo;


	public PublicacaoResponseDTO() {
		
	}

	
	public PublicacaoResponseDTO(Publicacao publicacao) {
		this.id = publicacao.getId();
		this.titulo = publicacao.getTitulo();
		this.descricao = publicacao.getDescricao();
		this.imagem = publicacao.getImagem();
		this.cidade = publicacao.getCidade();
		this.estado = publicacao.getEstado();
		this.data = publicacao.getData();
		this.status = publicacao.getStatus();
		this.votosConfiavel = publicacao.getVotosConfiavel();
		this.votosFalso = publicacao.getVotosFalso();
		this.usuarioId = publicacao.getUsuario().getId();
		this.usuarioNome = publicacao.getUsuario().getNome();
		this.selo = publicacao.getUsuario().getSelo().getDescricao();
	}
	
}
