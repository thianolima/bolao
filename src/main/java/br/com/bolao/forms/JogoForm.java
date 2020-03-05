package br.com.bolao.forms;

import java.time.LocalTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.dao.EmptyResultDataAccessException;

import br.com.bolao.entities.Campeonato;
import br.com.bolao.entities.Jogo;
import br.com.bolao.entities.Time;
import br.com.bolao.repositories.CampeonatoRepository;
import br.com.bolao.repositories.TimeRepository;
import lombok.Setter;

@Setter
public class JogoForm {

	@NotNull(message = "Obrigatório informar o time da casa !")
	private Integer casa;
	
	@NotNull(message = "Obrigatório informar o time visitante !")
	private Integer visitante;
	
	@NotNull(message = "Obrigatório informar o campeonato !")
	private Integer campeonato;
		
	@Min(value = 1, message = "Obrigatorio informar a rodada !")
	private Integer rodada;
	
	@Future(message = "O horário precisa ser maior que o atual !")
	private LocalTime horario;
	
	
	public Jogo toEntity(TimeRepository timeRepository, CampeonatoRepository campeonatoRepository) {
		Jogo jogo = new Jogo();
		
		Time visitante = timeRepository.findById(this.visitante)		
			.orElseThrow(() ->  new EmptyResultDataAccessException("Time visitante não cadastrado no sistema !", this.visitante));
		
		jogo.setVisitante(visitante);
		
		Time casa = timeRepository.findById(this.casa)		
			.orElseThrow(() ->  new EmptyResultDataAccessException("Time da casa não cadastrado no sistema !", this.casa));
		
		jogo.setCasa(casa);
		
		Campeonato campeonato = campeonatoRepository.findById(this.campeonato)		
			.orElseThrow(() ->  new EmptyResultDataAccessException("Campeonato não cadastrado no sistema !", this.visitante));
		
		jogo.setCampeonato(campeonato);
		
		jogo.setRodada(this.rodada);
		jogo.setHorario(this.horario);
		
		return jogo;
	}
}
