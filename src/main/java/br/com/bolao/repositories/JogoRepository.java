package br.com.bolao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bolao.entities.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer>{

	
	@Query( " select count(*) from Jogo j "
		  + " where (j.casa.id = :idTimeCasa or j.visitante.id = :idTimeVisitante) "
		  + " and j.rodada = :rodada")
	public Integer pesquisarJogoRodada(@Param("rodada") Integer rodada, 
			                           @Param("idTimeCasa") Integer idTimeCasa, 
			                           @Param("idTimeVisitante") Integer idTimeVisitante);
}
