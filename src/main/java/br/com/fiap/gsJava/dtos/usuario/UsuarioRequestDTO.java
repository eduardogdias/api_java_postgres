package br.com.fiap.gsJava.dtos.usuario;

import br.com.fiap.gsJava.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRequestDTO extends UsuarioDTO {
	
	@NotBlank(message = "senha não pode ser nulo ou vazio")
	@Size(min = 2, max = 10, message = "senha deve ter entre 2 e 10 caracteres")
	private String senha;
	
	@NotNull(message = "seloId não pode ser nulo")
	@Positive(message = "seloId deve ser um número positivo")
	private Long seloId;
	

	public UsuarioRequestDTO(Usuario usuario, String senha, Long seloId) {
		super(usuario);
		this.senha = senha;
		this.seloId = seloId;
	}
	
	
}
