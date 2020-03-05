package br.com.bolao.forms;

import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class TokenForm {
	
	@NotNull(message = "Obirgatório preencher o login !")
	String login;	
	
	@NotNull(message = "Obirgatório preencher a senha !")
	String senha;
	
	public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}
