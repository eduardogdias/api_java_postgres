package br.com.fiap.gsJava.dtos.emergencia;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fiap.gsJava.entities.Emergencia;
import br.com.fiap.gsJava.enums.LocalEventoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmergenciaResponseDTO {

	private Long id;
	private String mensagem;
	private Boolean status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date data = new Date(); 
	
    private Long usuarioId;
    private String usuarioNome;
    
    private String rua;
	private Long numero;
	private String cidade;
	private String estado;
	private LocalEventoEnum evento;
    
	public EmergenciaResponseDTO(Emergencia emergencia) {
		this.id = emergencia.getId();
		this.mensagem = emergencia.getMensagem();
		this.status = emergencia.getStatus();
		this.data = emergencia.getData();
		this.usuarioId = emergencia.getUsuario().getId();
		this.usuarioNome = emergencia.getUsuario().getNome();
		
		this.rua = emergencia.getLocal().getRua();
		this.numero = emergencia.getLocal().getNumero();
		this.cidade = emergencia.getLocal().getCidade();
		this.estado = emergencia.getLocal().getEstado();
		this.evento = emergencia.getLocal().getEvento();
	}
    
    
	
}
