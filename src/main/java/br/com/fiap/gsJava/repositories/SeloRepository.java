package br.com.fiap.gsJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.gsJava.entities.Selo;

@Repository
public interface SeloRepository extends JpaRepository<Selo, Long>{

}
