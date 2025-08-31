package br.com.fiap.gsJava.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.gsJava.dtos.local.LocalRequestDTO;
import br.com.fiap.gsJava.enums.LocalEventoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_local")
@Getter
@Setter
@NoArgsConstructor
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private Long numero;
	private String cidade;
	private String estado;
	
	@Enumerated(EnumType.STRING)
	private LocalEventoEnum evento;
	private Boolean status;
	
	@OneToMany(mappedBy = "local")
	@JsonIgnore
	private List<Emergencia> emergencias = new ArrayList<>();
	
	public Local(LocalRequestDTO localDTO) {
		this.id = localDTO.getId();
		this.rua = localDTO.getRua();
		this.numero = localDTO.getNumero();
		this.cidade = localDTO.getCidade();
		this.estado = localDTO.getEstado();
		this.evento = localDTO.getEvento();
		this.status = true;
	}

	public Local(Long id, String rua, Long numero, String cidade, String estado, LocalEventoEnum evento, Boolean status) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.evento = evento;
		this.status = status;
	}

	
}
