package br.com.bolao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErroDto {
	
	private String mensagem;
	private String campo;	
	private String exception;
}
