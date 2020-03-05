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

import br.com.bolao.dtos.CampeonatoDto;
import br.com.bolao.forms.CampeonatoForm;
import br.com.bolao.services.CampeonatoService;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	@Autowired
	CampeonatoService service;
	
	//Todo habilitar trecho abaixo assim que configurar o spring security
	//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") 
	@PostMapping
	public ResponseEntity<CampeonatoDto> inserir(@Valid @RequestBody CampeonatoForm form, UriComponentsBuilder uriBuilder){
		CampeonatoDto dto = service.inserir(form);
		URI uri = uriBuilder.path("/campeonatos/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
