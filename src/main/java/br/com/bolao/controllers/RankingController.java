package br.com.bolao.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bolao.dtos.RankingDto;
import br.com.bolao.forms.RankingForm;
import br.com.bolao.services.RankingService;

@RestController
@RequestMapping("/rankings")
public class RankingController {

	@Autowired
	RankingService service;
	
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") 
	@PutMapping
	public List<RankingDto> processar(@Valid @RequestBody RankingForm form){	
		return service.processar(form);
	}
}
