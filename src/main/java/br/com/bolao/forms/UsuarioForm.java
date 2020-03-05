package br.com.bolao.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.bolao.entities.Usuario;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class UsuarioForm {

	@NotEmpty(message = "Obrigatório preenchimento do campo login !")
	@Email(message = "Obrigatório informar um e-mail válido !")
	private String login;
	
	@NotEmpty(message = "Obrigatório preenchimento do campo senha !")
	@Size(min = 6, message = "A senha dever ter no mínimo 6 caracteres !")
	private String senha;
	
	public Usuario toEntity() {
		Usuario entity = new Usuario();
		entity.setLogin(this.login);
		entity.setSenha(this.senha);
		return entity;
	}
}
