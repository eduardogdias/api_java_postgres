package br.com.fiap.gsJava.dtos.local;

import br.com.fiap.gsJava.entities.Local;
import br.com.fiap.gsJava.enums.LocalEventoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalRequestDTO {

	
	private Long id;
	
	@NotBlank(message = "A rua é obrigatória")
    private String rua;

    @NotNull(message = "O número é obrigatório")
    @Min(value = 1, message = "O número deve ser maior que zero")
    private Long numero;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    private String estado;

    @NotNull(message = "O evento é obrigatório e deve ser: 'EMERGENCIA', 'ABRIGO' ou 'ALAGAMENTO'")
    @Enumerated(EnumType.STRING)
    private LocalEventoEnum evento;
	
    private boolean status; 
    
	public LocalRequestDTO(Local local) {
		this.id = local.getId();
		this.rua = local.getRua();
		this.numero = local.getNumero();
		this.cidade = local.getCidade();
		this.estado = local.getEstado();
		this.evento = local.getEvento();
		this.status = true;
	}

	 
}

