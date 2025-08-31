package br.com.fiap.gsJava.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.gsJava.dtos.usuario.UsuarioRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cidade;
	private String estado;
	private String telefone;

	@ManyToOne
	@JoinColumn(name = "selo_id")
	private Selo selo;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Emergencia> emergencias = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE) //ao excluir o usuário, suas publicações e emergências também serão excluidas
	@JsonIgnore
	private List<Publicacao> publicacoes = new ArrayList<>();
	
	
	public Usuario(UsuarioRequestDTO usuarioRequestDTO) {
		this.nome = usuarioRequestDTO.getNome();
		this.email = usuarioRequestDTO.getEmail();
		this.senha = usuarioRequestDTO.getSenha();
		this.cidade = usuarioRequestDTO.getCidade();
		this.estado = usuarioRequestDTO.getEstado();
		this.telefone = usuarioRequestDTO.getTelefone();
	
	}


	public Usuario(Long id, String nome, String email, String senha, String cidade, String estado, String telefone, Selo selo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
		this.selo = selo;
	}

	public Usuario(Long id) { //construtor para PublicacaoDTO
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Selo getSelo() {
		return selo;
	}


	public void setSelo(Selo selo) {
		this.selo = selo;
	}



}
