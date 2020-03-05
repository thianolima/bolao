package br.com.bolao.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.dao.EmptyResultDataAccessException;

import br.com.bolao.entities.Jogo;
import br.com.bolao.entities.Resultado;
import br.com.bolao.repositories.JogoRepository;
import lombok.Setter;

@Setter
public class ResultadoForm {

	@Min(value = 0, message = "Não existe placar negativo no futebol !")
	private Integer golsCasa;
	
	@Min(value = 0, message = "Não existe placar negativo no futebol !")
	private Integer golsVisitante;
	
	@NotNull(message = "Obrigatório informar o jogo !")
	private Integer jogo;
	
	public Resultado toEntity(JogoRepository jogoRepository) {
		Resultado resultado = new Resultado();
		
		resultado.setGolsCasa(this.golsCasa);
		resultado.setGolsVisitante(this.golsVisitante);
		
		Jogo jogo = jogoRepository.findById(this.jogo)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Jogo não cadastrado no sistema !", this.jogo));
		
		resultado.setJogo(jogo);
		
		return resultado;
	}
}
