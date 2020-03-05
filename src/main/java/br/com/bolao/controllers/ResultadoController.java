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

import br.com.bolao.dtos.ResultadoDto;
import br.com.bolao.forms.ResultadoForm;
import br.com.bolao.services.ResultadoService;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

	@Autowired
	ResultadoService service;
	
	@PostMapping
	public ResponseEntity<ResultadoDto> inserir(@Valid @RequestBody ResultadoForm form, UriComponentsBuilder uriBuilder){	
		ResultadoDto dto = service.inserir(form);
		URI uri = uriBuilder.path("/boloes/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
