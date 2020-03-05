package br.com.bolao.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bolao.dtos.TimeDto;
import br.com.bolao.forms.TimeForm;
import br.com.bolao.services.TimeService;

@RestController
@RequestMapping("/times")
public class TimeController {

	@Autowired
	private TimeService service;
	
	//Todo habilitar trecho abaixo assim que configurar o spring security
	//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") 
	@PostMapping
	public ResponseEntity<TimeDto> inserir(@Valid @RequestBody TimeForm form, UriComponentsBuilder uriBuilder){
		TimeDto dto = service.inserir(form);
		URI uri = uriBuilder.path("/times/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
