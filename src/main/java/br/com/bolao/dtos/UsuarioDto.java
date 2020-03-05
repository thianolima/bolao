package br.com.bolao.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.bolao.entities.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDto {

	private Integer id;
	
	private String login;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.dataCadastro = usuario.getDataCadastro();
	}	
}
