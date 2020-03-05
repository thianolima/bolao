package br.com.bolao.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bolao.entities.Campeonato;
import br.com.bolao.entities.Time;
import lombok.Getter;

@Getter
public class CampeonatoDto {

	private Integer id;
	
	private String nome;
	
	private Integer qtdTimes;
	
	@JsonFormat(pattern="dd/MM/yyyy")	
    private LocalDate dataInicio = LocalDate.now();
	
	private List<TimeDto> times = new ArrayList<TimeDto>();
	
	public CampeonatoDto(Campeonato campeonato) {
		this.id = campeonato.getId();
		this.nome = campeonato.getNome();
		this.dataInicio = campeonato.getDataInicio();
		this.qtdTimes = campeonato.getQtdTimes();
		
		Iterator<Time> it = campeonato.getTimes().iterator();
		while(it.hasNext()) {
			times.add(new TimeDto(it.next()));
		}
	}
}
