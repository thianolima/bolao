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

import br.com.bolao.dtos.JogoDto;
import br.com.bolao.forms.JogoForm;
import br.com.bolao.services.JogoService;

@RestController
@RequestMapping("/jogos")
public class JogoController {

	@Autowired
	JogoService service;
	
	@PostMapping
	public ResponseEntity<JogoDto> inserir(@Valid @RequestBody JogoForm form, UriComponentsBuilder uriBuilder){
		JogoDto dto = service.inserir(form);
		URI uri = uriBuilder.path("/jogos/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
