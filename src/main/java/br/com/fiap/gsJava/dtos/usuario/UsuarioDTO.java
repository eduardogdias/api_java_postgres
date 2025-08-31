package br.com.fiap.gsJava.dtos.usuario;

import br.com.fiap.gsJava.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
	
	private Long id;
	
	@NotBlank(message = "nome não pode ser nulo ou vazio")
    @Size(min = 2, max = 100, message = "nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "email não pode ser nulo ou vazio")
    @Email(message = "email deve ser válido")
    private String email;

    @NotBlank(message = "cidade não pode ser nula ou vazia")
    private String cidade;

    //private String senha;      está no UsuarioRequestDTO
    
    @NotBlank(message = "estado não pode ser nulo ou vazio")
    private String estado;

    @NotBlank(message = "telefone não pode ser nulo ou vazio")
    @Pattern(regexp = "\\d{11}", message = "telefone deve conter 11 dígitos (DDD + número)")
    private String telefone;

   
    private String selo;
	
	
	public UsuarioDTO(Usuario usuario) {
		id = usuario.getId();
		nome = usuario.getNome();
		email = usuario.getEmail();
		cidade = usuario.getCidade();
		estado = usuario.getEstado();
		telefone = usuario.getTelefone();
		selo = usuario.getSelo().getDescricao();
		
	}

}