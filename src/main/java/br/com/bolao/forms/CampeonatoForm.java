package br.com.bolao.forms;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.dao.EmptyResultDataAccessException;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bolao.entities.Campeonato;
import br.com.bolao.entities.Time;
import br.com.bolao.repositories.TimeRepository;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CampeonatoForm {
	
	@NotEmpty(message = "Obrigatório o preenchimento do campo nome")
	private String nome;
	
	@FutureOrPresent(message = "A data de início precisa ser maior ou igual a hoje !")
	@JsonFormat(pattern="dd/MM/yyyy")	
    private LocalDate dataInicio;
	
	@Min(value = 2, message = "É necessário no mínimo dois times para se ter uma partida de futebol !")
	private Integer qtdTimes;
	
	@Size(min = 2, message = "É necessário no mínimo dois times para se ter uma partida de futebol !")
	private List<CampeonatoTimeForm> times;
	
	public Campeonato toEntity(TimeRepository timeRepository) {
		Campeonato entity = new Campeonato();
		entity.setNome(this.nome);
		entity.setDataInicio(this.dataInicio);
		entity.setQtdTimes(this.qtdTimes);
		
		Set<Time> campeonatoTimes = new HashSet<Time>();
		for(int i = 0; i < times.size() ; i++) {
			Integer idTime = times.get(i).getId();
			Time time = timeRepository.findById(idTime)
				.orElseThrow(() ->  new EmptyResultDataAccessException("Time não cadastrado no sistema !", idTime));
			
			campeonatoTimes.add(time);
		}						
		entity.setTimes(campeonatoTimes);
		
		return entity;
	}
}
