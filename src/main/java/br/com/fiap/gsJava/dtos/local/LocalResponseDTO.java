package br.com.fiap.gsJava.dtos.local;

import br.com.fiap.gsJava.entities.Local;
import br.com.fiap.gsJava.enums.LocalEventoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalResponseDTO {

	
	private Long id;
	
    private String rua;
    private Long numero;
    private String cidade;
    private String estado;
    private LocalEventoEnum evento;
    private Boolean status; 
    
	public LocalResponseDTO(Local local) {
		this.id = local.getId();
		this.rua = local.getRua();
		this.numero = local.getNumero();
		this.cidade = local.getCidade();
		this.estado = local.getEstado();
		this.evento = local.getEvento();
		this.status = local.getStatus();
	}

	 
}

