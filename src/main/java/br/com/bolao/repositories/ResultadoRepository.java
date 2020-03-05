package br.com.bolao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.bolao.entities.Resultado;

public interface ResultadoRepository extends JpaRepository<Resultado, Integer>{

	Optional<Resultado> findByJogoId(@Param("idJogo") Integer idJogo);
}
