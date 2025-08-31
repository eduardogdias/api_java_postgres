package br.com.fiap.gsJava.dtos.emergencia;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmergenciaRequestPutDTO {

	
	@NotBlank(message = "mensagem é obrigatório")
	private String mensagem;
	
}
