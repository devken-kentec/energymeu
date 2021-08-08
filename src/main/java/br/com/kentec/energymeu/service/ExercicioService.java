package br.com.kentec.energymeu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import br.com.kentec.energymeu.domain.Exercicio;
import br.com.kentec.energymeu.repository.ExercicioRepository;

@Service
public class ExercicioService {
	
	@Autowired
	private ExercicioRepository er;
	
	public Iterable<Exercicio> findAll() {
		return er.findAll();
	}
	
	public Optional<Exercicio> findById(Long id){
		return er.findById(id);
	}
	
	public Page<Exercicio> listarExercicio(Integer page, Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		return er.findAll(pageRequest);
	}
	
	public void create(Exercicio exercicio) {
		er.save(exercicio);
	}
	
	public void update(Exercicio exercicio) {
		Optional<Exercicio> e = er.findById(exercicio.getId());
		
		if(e.isPresent()) {
			er.save(exercicio);
		}
	}
	
	public void mudarStatusExercicio(Long id) {
		Optional<Exercicio> e = er.findById(id);
		
		if(e.isPresent()) {
			e.get().setStatusExercicio("Inativo");
			er.save(e.get());
		}
	}
}
