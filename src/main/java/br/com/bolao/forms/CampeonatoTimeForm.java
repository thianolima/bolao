package br.com.bolao.forms;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CampeonatoTimeForm {

	@NotEmpty
	Integer id;
}
