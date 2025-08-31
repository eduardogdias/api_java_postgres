package br.com.fiap.gsJava.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.gsJava.dtos.publicacao.PublicacaoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_publicacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Publicacao(PublicacaoRequestDTO publicacaoRequestDTO) {
		this.id = publicacaoRequestDTO.getId();
		this.titulo = publicacaoRequestDTO.getTitulo();
		this.descricao = publicacaoRequestDTO.getDescricao();
		this.imagem = publicacaoRequestDTO.getImagem();
		this.cidade = publicacaoRequestDTO.getCidade();
		this.estado = publicacaoRequestDTO.getEstado();
		this.data = publicacaoRequestDTO.getData();
		this.status = publicacaoRequestDTO.getStatus();
		this.votosConfiavel = publicacaoRequestDTO.getVotosConfiavel();
		this.votosFalso = publicacaoRequestDTO.getVotosFalso();
		this.usuario = publicacaoRequestDTO.getUsuario();
	}
	
	
	
	
}
