package br.com.bolao.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bolao.entities.Time;
import lombok.Getter;

@Getter
public class TimeDto {

	private Integer id;
	private String nome;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataFundacao;
	
	public TimeDto(Time time) {
		this.id = time.getId();
		this.nome = time.getNome();
		this.dataFundacao = time.getDataFundacao();
	}
}
