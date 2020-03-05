package br.com.bolao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bolao.entities.Palpite;

public interface PalpiteRepository extends JpaRepository<Palpite, Integer>{

	@Query( " select p from Palpite p "
	  	  + " where p.jogo.id = :idJogo"
		  + " and p.bolao.id = :idBolao"
		  + " and p.participante.id = :idParticipante")
	Optional<Palpite> pesquisarPalpitesJogoBolaoParticipante(@Param("idJogo") Integer idJogo, 
			                                                 @Param("idBolao")Integer idBolao,
			                                                 @Param("idParticipante") Integer idParticipante);	
}
