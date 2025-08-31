package br.com.fiap.gsJava.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.gsJava.dtos.emergencia.EmergenciaRequestDTO;
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
@Table(name = "tb_emergencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emergencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	private Boolean status;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "local_id")
	private Local local;
	
	public Emergencia(EmergenciaRequestDTO dto) {
		this.id = dto.getId();
		this.mensagem = dto.getMensagem();
		this.status = dto.getStatus();
		this.data = dto.getData();
		this.usuario = dto.getUsuario();
	}
	
	

	
}
