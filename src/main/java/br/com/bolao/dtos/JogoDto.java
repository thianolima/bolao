package br.com.bolao.dtos;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bolao.entities.Jogo;
import lombok.Getter;

@Getter
public class JogoDto {

	private Integer id;
	
	private String campeonato;
	
	private String casa;
	
	private String visitante;
	
	@JsonFormat(pattern="HH:mm:ss")	
	private LocalTime horairo;
	
	private Integer rodada;
	
	public JogoDto(Jogo jogo) {
		this.id = jogo.getId();
		this.campeonato = jogo.getCampeonato().getNome();
		this.visitante = jogo.getVisitante().getNome();
		this.casa = jogo.getCasa().getNome();
		this.horairo = jogo.getHorario();
		this.rodada = jogo.getRodada();
	}
}
