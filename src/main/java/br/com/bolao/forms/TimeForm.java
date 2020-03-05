package br.com.bolao.forms;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bolao.entities.Time;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class TimeForm {

	@NotEmpty(message = "Obrigatório preenchimento do campo nome !")
	private String nome;
	
	@Past(message = "A data de fundação precisa ser menor que hoje !")
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataFundacao;
	
	public Time toEntity() {
		Time entity = new Time();
		entity.setNome(this.nome);
		entity.setDataFundacao(this.dataFundacao);
		return entity;
	}
}
