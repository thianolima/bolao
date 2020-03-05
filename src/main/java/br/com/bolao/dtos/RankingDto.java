package br.com.bolao.dtos;

import br.com.bolao.entities.Ranking;
import lombok.Getter;

@Getter
public class RankingDto {

	public String participante;
	public String jogo;
	public String resultado;
	public String palpite;
	public Integer pontos; 
	
	public RankingDto(Ranking ranking) {
		this.participante = ranking.getParticipante().getLogin();
		this.jogo = ranking.getJogo().getCasa().getNome() + " X " + ranking.getJogo().getVisitante().getNome();
		this.palpite = ranking.getPalpite().getGolsCasa() + " X " + ranking.getPalpite().getGolsVisitante();
		this.resultado = ranking.getResultado().getGolsCasa() + " X " + ranking.getResultado().getGolsVisitante();
		this.pontos = ranking.getPontos();
	}
}
