package br.com.fiap.gsJava.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ControllerExceptionHandler { //vai interceptar todas as exception do controller automaticamente

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<MessageException> entityNotFound(EntityNotFoundException e){
		MessageException msg = new MessageException();
		msg.setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        // Pegando o primeiro erro de validação
        FieldError firstError = ex.getBindingResult().getFieldErrors().get(0);
        String errorMessage = firstError.getDefaultMessage();

        // Criando a resposta personalizada
        Map<String, String> response = new HashMap<>();
        response.put("message", errorMessage);

        return ResponseEntity.badRequest().body(response);
    }
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class) 
	public ResponseEntity<MessageException> handleInvalidEnum(HttpMessageNotReadableException ex) {
		MessageException msg = new MessageException();
		
	    if (ex.getMessage() != null && ex.getMessage().contains("LocalEventoEnum")) {
	        
	        msg.setMessage("Valor inválido para o campo 'evento'. Use: 'EMERGENCIA', 'ABRIGO' ou 'ALAGAMENTO'");
	        return ResponseEntity.badRequest().body(msg);
	    }

	    // Retorno genérico para outros erros de leitura
	    msg.setMessage("Erro ao processar a requisição");
	    return ResponseEntity.badRequest().body(msg);
	}

	
	@ExceptionHandler(BusinessException.class) //captura o Evento do Local ao criar uma Emergencia
	public ResponseEntity<MessageException> handleBusinessException(BusinessException e) {
	    MessageException msg = new MessageException();
	    msg.setMessage(e.getMessage());
	    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg); // 422 Unprocessable Entity
	}

	
	
}
