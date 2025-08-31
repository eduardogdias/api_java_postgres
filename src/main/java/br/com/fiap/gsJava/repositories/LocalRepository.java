package br.com.fiap.gsJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.gsJava.entities.Local;


@Repository
public interface LocalRepository extends JpaRepository<Local, Long>{
	List<Local> findByStatus(boolean status);
}
