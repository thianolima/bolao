package br.com.bolao.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bolao.entities.Bolao;
import br.com.bolao.entities.Convite;
import lombok.Getter;

@Getter
public class BolaoDto {

	Integer id;
	String campeonato;
	String dono;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	LocalDateTime dataCriacao;
	
	List<ConviteDto> convites = new ArrayList<ConviteDto>();
	
	public BolaoDto(Bolao bolao) {
		this.id = bolao.getId();
		this.campeonato = bolao.getCampeonato().getNome();
		this.dono = bolao.getDono().getLogin();
		this.dataCriacao = bolao.getDataCriacao();
		
		for(Convite convite : bolao.getConvites()) {
			convites.add(new ConviteDto(convite));
		}				
	}

}
