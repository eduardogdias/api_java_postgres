package br.com.fiap.gsJava;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fiap.gsJava.entities.Emergencia;
import br.com.fiap.gsJava.entities.Local;
import br.com.fiap.gsJava.entities.Publicacao;
import br.com.fiap.gsJava.entities.Selo;
import br.com.fiap.gsJava.entities.Usuario;
import br.com.fiap.gsJava.enums.LocalEventoEnum;
import br.com.fiap.gsJava.repositories.EmergenciaRepository;
import br.com.fiap.gsJava.repositories.LocalRepository;
import br.com.fiap.gsJava.repositories.PublicacaoRepository;
import br.com.fiap.gsJava.repositories.SeloRepository;
import br.com.fiap.gsJava.repositories.UsuarioRepository;

@SpringBootApplication
public class GsJavaApplication implements CommandLineRunner{

	
	@Autowired
	private SeloRepository seloRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PublicacaoRepository publicacaoRepository;
	
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private EmergenciaRepository emergenciaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GsJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		//instanciando todas as entidades
		
		Selo selo1 = new Selo(null, "comum");
		Selo selo2 = new Selo(null, "confiavel");
		Selo selo3 = new Selo(null, "heroi");		
		seloRepository.save(selo1);
		seloRepository.save(selo2);
		seloRepository.save(selo3);
		
		
		Usuario usu1 = new Usuario(null, "Eduardo", "edu@gmail.com", "123", "Sao Paulo", "São Paulo", "11990001234", selo3);
		Usuario usu2 = new Usuario(null, "Juliano", "juliano@gmail.com", "456", "Salvador", "Bahia", "11990003333", selo1);
		Usuario usu3 = new Usuario(null, "Abel", "abel@gmail.com", "789", "Curitiba", "Paraná", "11990004444", selo2);
		usuarioRepository.save(usu1);
		usuarioRepository.save(usu2);
		usuarioRepository.save(usu3);
		
		
		Publicacao pub1 = new Publicacao(null, "Alagamento na Av. Brasil", "A avenida está completamente alagada. Evitem passar por lá.", "https://exemplo.com/imagem1.jpg", "São Paulo", "São Paulo", new Date(), true, 23L, 2L, usu1);
		Publicacao pub2 = new Publicacao(null, "Deslizamento de terra no Morro Azul","Deslizamento bloqueou parte da estrada. Autoridades foram acionadas.", "https://exemplo.com/imagem2.jpg", "Petrópolis", "Rio de Janeiro", new Date(), true, 47L, 1L, usu2);
		Publicacao pub3 = new Publicacao(null, "Fake News sobre ponte caída", "Circula nas redes uma fake news dizendo que a ponte caiu. Isso é falso.", "https://exemplo.com/imagem3.jpg", "Belo Horizonte", "Minas Gerais", new Date(), true, 5L, 19L, usu3);
		publicacaoRepository.save(pub1);
		publicacaoRepository.save(pub2);
		publicacaoRepository.save(pub3);
		
			
		Local local1 = new Local(null, "Rua das Flores", 123L, "São Paulo", "São Paulo", LocalEventoEnum.ABRIGO, true);
		Local local2 = new Local(null, "Avenida Brasil", 456L, "Rio de Janeiro", "Rio de Janeiro", LocalEventoEnum.ALAGAMENTO, true);
		Local local3 = new Local(null, "Rua da Esperança", 789L, "Belo Horizonte", "Minas Gerais", LocalEventoEnum.EMERGENCIA, true);
		localRepository.save(local1);
		localRepository.save(local2);
		localRepository.save(local3);
		
		
		Emergencia emergencia1 = new Emergencia(null, "Preciso de um bote para usar na enchente.", true, new Date(),usu1, local3);
		Emergencia emergencia2 = new Emergencia(null, "Preciso de ajuda para subir os meus móveis.", true, new Date(), usu2, local3);
		Emergencia emergencia3 = new Emergencia(null, "Estou no telhado aguardando resgate.", true, new Date(), usu3, local3);
		emergenciaRepository.save(emergencia1);
		emergenciaRepository.save(emergencia2);
		emergenciaRepository.save(emergencia3);

			
	}

}
