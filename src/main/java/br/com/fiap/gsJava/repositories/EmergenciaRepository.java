package br.com.fiap.gsJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.gsJava.entities.Emergencia;

@Repository
public interface EmergenciaRepository extends JpaRepository<Emergencia, Long>{

}
