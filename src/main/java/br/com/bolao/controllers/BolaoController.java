package br.com.bolao.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bolao.dtos.BolaoDto;
import br.com.bolao.forms.BolaoForm;
import br.com.bolao.services.BolaoService;

@RestController
@RequestMapping("/boloes")
public class BolaoController {

	@Autowired
	BolaoService service;
	
	@PostMapping
	public ResponseEntity<BolaoDto> inserir(@Valid @RequestBody BolaoForm form, UriComponentsBuilder uriBuilder, 
			@RequestHeader HttpHeaders headers){
		
		String token = headers.get("Authorization").toString();		
		BolaoDto dto = service.inserir(form, token);
		URI uri = uriBuilder.path("/boloes/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
