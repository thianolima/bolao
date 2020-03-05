package br.com.bolao.dtos;

import lombok.Getter;

@Getter
public class TokenDto {
	String token;
	String tipo;
	
	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}
	
}
