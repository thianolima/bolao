	package br.com.bolao.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ConviteForm {
	
	@NotEmpty
	@Email
	private String email;
}
