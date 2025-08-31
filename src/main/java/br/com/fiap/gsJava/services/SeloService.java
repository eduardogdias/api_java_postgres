package br.com.fiap.gsJava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.gsJava.entities.Selo;
import br.com.fiap.gsJava.exceptions.BusinessException;
import br.com.fiap.gsJava.exceptions.EntityNotFoundException;
import br.com.fiap.gsJava.repositories.SeloRepository;

@Service
public class SeloService {

	@Autowired
	private SeloRepository seloRepository;

	public List<Selo> findAll() {
		return seloRepository.findAll();
	}

	public Selo findById(Long id) {
		return seloRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Selo não encontrado"));

	}

	public Selo save(Selo selo) {
		return seloRepository.save(selo);
	}

	public Selo update(Long id, Selo selo) {

		Optional<Selo> optional = seloRepository.findById(id);

		if (optional.isEmpty()) {
			return seloRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException("Selo não encontrado"));
		}

		Selo seloExistente = optional.get();
		seloExistente.setDescricao(selo.getDescricao());
		return seloRepository.save(seloExistente);
	}

	public Selo deleteById(Long id) {

		Optional<Selo> optional = seloRepository.findById(id);

		if (optional.isEmpty()) {
			return seloRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException("Selo não encontrado"));
		}

		
		Selo selo = optional.get();
		
		if (!selo.getUsuarios().isEmpty()) {
	        throw new BusinessException("Não é possível deletar um selo que está em uso por usuários");
	    }
		
		seloRepository.deleteById(id);
		return selo;
	}
}
