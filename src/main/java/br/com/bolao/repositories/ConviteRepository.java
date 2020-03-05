package br.com.bolao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bolao.entities.Convite;

public interface ConviteRepository extends JpaRepository<Convite, Integer>{

	@Query(" select c from Convite c "
		+  " where c.email = :email "
		+  " and c.bolao.id = :idBolao"
		+  " and c.dataAceite is not null ")
	Optional<Convite> pesquisarConviteAtivo(@Param("email") String email, 
			                                @Param("idBolao") Integer idBolao);
}
