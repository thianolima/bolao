package br.com.bolao.dtos;

import br.com.bolao.entities.Resultado;
import lombok.Getter;

@Getter
public class ResultadoDto {

	private Integer id;
	
	private Integer golsCasa;
	
	private Integer golsVisitante;
	
	private String jogo;
	
	public ResultadoDto(Resultado resultado) {
		this.id = resultado.getId();
		this.golsCasa = resultado.getGolsCasa();
		this.golsVisitante = resultado.getGolsVisitante();
		this.jogo = resultado.getJogo().getCasa().getNome() + " X " + resultado.getJogo().getVisitante().getNome();
	}
}
