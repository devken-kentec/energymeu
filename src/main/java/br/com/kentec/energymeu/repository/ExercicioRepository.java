package br.com.kentec.energymeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kentec.energymeu.domain.Exercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long>{
	
}
