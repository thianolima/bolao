package br.com.bolao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bolao.entities.Ranking;

public interface RankingRepository extends JpaRepository<Ranking, Integer>{
	
	@Modifying
	@Query( " delete from Ranking r "
		  + " where r.jogo.id = :idJogo "
		  + " and r.bolao.id = :idBolao ")
	void excluirProcessamentoJogoBolao(@Param("idJogo") Integer idJogo, 
			                           @Param("idBolao") Integer idBolao);
}
