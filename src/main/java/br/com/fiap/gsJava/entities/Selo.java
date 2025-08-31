package br.com.fiap.gsJava.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_selo")
public class Selo {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "descricao nao pode ser nulo ou vazio")
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "selo")
	private List<Usuario> usuarios = new ArrayList<>();
	
	
	public Selo() {
		
	}

	public Selo(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Selo(Long id) { //construtor para criar o selo só com o 'seloId' no POST e PUT de 'usuario'
		this.id = id;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}


}
