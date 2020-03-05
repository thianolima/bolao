package br.com.bolao.forms;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingForm {

	@NotNull(message = "Obirgatório informar o id do bolão que ira processar !")	
	private Integer bolao;
	
	@NotNull(message = "Obirgatório informar o id do jogo que ira processar !")
	private Integer jogo;
}
