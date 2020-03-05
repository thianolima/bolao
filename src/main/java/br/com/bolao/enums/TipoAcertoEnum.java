package br.com.bolao.enums;

public enum TipoAcertoEnum {
	NaLata(10), GanhadorGols(7), Ganhador(5), Gols(2), Errado(0);
	
	public final Integer pontos;
	
	private TipoAcertoEnum(Integer pontos) {
		this.pontos = pontos;
	}
}
