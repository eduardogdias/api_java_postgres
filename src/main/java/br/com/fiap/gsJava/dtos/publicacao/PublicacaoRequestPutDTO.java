package br.com.fiap.gsJava.dtos.publicacao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacaoRequestPutDTO {

	private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    private String imagem;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    private String estado;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date data = new Date(); // gerada automaticamente

    private Boolean status = true; // gerado automaticamente

    @NotNull(message = "'Votos confiavel' não podem ser nulo ou vazio")
    @Min(value = 0, message = "Os votos confiáveis devem ser zero ou mais")
    private Long votosConfiavel;

    @NotNull(message = "'Votos falso' não podem ser nulo ou vazio")
    @Min(value = 0, message = "Os votos falsos devem ser zero ou mais")
    private Long votosFalso;

    




	public PublicacaoRequestPutDTO(Long id, String titulo, String descricao, String imagem, String cidade, String estado, 
			Date data, Long votosConfiavel, Long votosFalso) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.imagem = imagem;
		this.cidade = cidade;
		this.estado = estado;	   
		this.votosConfiavel = votosConfiavel;
		this.votosFalso = votosFalso;
	}
	
	
	
}
