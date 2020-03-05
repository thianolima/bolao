package br.com.bolao.dtos;

import br.com.bolao.entities.Convite;
import lombok.Getter;

@Getter
public class ConviteDto {

	String email;
	
	public ConviteDto(Convite convite) {
		this.email = convite.getEmail();
	}
}
