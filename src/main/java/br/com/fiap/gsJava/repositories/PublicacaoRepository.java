package br.com.fiap.gsJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.gsJava.entities.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{
	List<Publicacao> findByStatus(boolean status);

}
