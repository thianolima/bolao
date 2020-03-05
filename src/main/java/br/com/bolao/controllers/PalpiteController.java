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

import br.com.bolao.dtos.PalpiteDto;
import br.com.bolao.forms.PalpiteForm;
import br.com.bolao.services.PalpiteService;

@RestController
@RequestMapping("/palpites")
public class PalpiteController {

	@Autowired
	PalpiteService service;
	
	@PostMapping
	public ResponseEntity<PalpiteDto> inserir(@Valid @RequestBody PalpiteForm form, UriComponentsBuilder uriBuilder){	
		PalpiteDto dto = service.inserir(form);
		URI uri = uriBuilder.path("/palpites/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
