package br.com.bolao.dtos;

import br.com.bolao.entities.Palpite;
import lombok.Getter;

@Getter
public class PalpiteDto {

	private Integer id;
	
	private Integer golsCasa;
	
	private Integer golsVisitante;
	
	private String jogo;
	
	private String participante;
	
	public PalpiteDto (Palpite palpite) {
		this.id = palpite.getId();
		this.golsCasa = palpite.getGolsCasa();
		this.golsVisitante = palpite.getGolsVisitante();
		this.jogo = palpite.getJogo().getCasa().getNome() + " X " + palpite.getJogo().getVisitante().getNome();
		this.participante = palpite.getParticipante().getLogin();
	}
}
