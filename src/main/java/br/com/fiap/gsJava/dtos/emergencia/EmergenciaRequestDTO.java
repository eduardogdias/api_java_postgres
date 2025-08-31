package br.com.fiap.gsJava.dtos.emergencia;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.gsJava.entities.Local;
import br.com.fiap.gsJava.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmergenciaRequestDTO {

	private Long id;
	
	@NotBlank(message = "mensagem é obrigatório")
	private String mensagem;
	private Boolean status = true;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date data = new Date(); 
	
	@NotNull(message = "O usuário e id são obrigatórios")
    private Usuario usuario;
	
	@NotNull(message = "O local e id são obrigatórios")
    private Local local;
	
}
