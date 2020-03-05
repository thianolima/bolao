package br.com.bolao.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.dao.EmptyResultDataAccessException;

import br.com.bolao.entities.Bolao;
import br.com.bolao.entities.Jogo;
import br.com.bolao.entities.Palpite;
import br.com.bolao.entities.Usuario;
import br.com.bolao.repositories.BolaoRepository;
import br.com.bolao.repositories.JogoRepository;
import br.com.bolao.repositories.UsuarioRepository;
import lombok.Setter;

@Setter
public class PalpiteForm {

	@Min(value = 0, message = "Não existe placar negativo no futebol !")
	private Integer golsCasa;
	
	@Min(value = 0, message = "Não existe placar negativo no futebol !")
	private Integer golsVisitante;
	
	@NotNull(message = "Obrigatório informar o jogo !")
	private Integer jogo;
	
	@NotNull(message = "Obrigatório informar o bolão !")
	private Integer bolao;
	
	@NotNull(message = "Obrigatório informar o participante !")
	private Integer participante;
	
	public Palpite toEntity(JogoRepository jogoRepository, UsuarioRepository usuarioRepository, BolaoRepository bolaoRepository) {
		Palpite palpite = new Palpite();
		palpite.setGolsCasa(this.golsCasa);
		palpite.setGolsVisitante(this.golsVisitante);
		
		Jogo jogo = jogoRepository.findById(this.jogo)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Jogo não cadastrado no sistema !", this.jogo));
		
		palpite.setJogo(jogo);
		
		Usuario participante = usuarioRepository.findById(this.participante)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Participante não cadastrado no sistema !", this.participante));
		
		palpite.setParticipante(participante);
		
		Bolao bolao = bolaoRepository.findById(this.bolao)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Bolão não cadastrado no sistema !", this.bolao));		
		
		palpite.setBolao(bolao);
		
		return palpite;
	}
	
}
