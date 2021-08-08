package br.com.kentec.energymeu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.kentec.energymeu.domain.FichaFinanceira;

@Repository
public interface FichaFinanceiraRepository extends JpaRepository<FichaFinanceira, Long> {
	
	@Query("SELECT ff FROM FichaFinanceira ff ")
	public List<FichaFinanceira> findByFichaFinanceira();
	
	@Query("SELECT ff FROM FichaFinanceira ff "
			+ "JOIN ff.cadastro c "
			+ "WHERE c.id = :alunoId ")
	public Optional<FichaFinanceira> findByAlunoId(@Param("alunoId") Long alunoId);
}
