package br.com.bolao.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bolao.dtos.TokenDto;
import br.com.bolao.dtos.UsuarioDto;
import br.com.bolao.forms.TokenForm;
import br.com.bolao.forms.UsuarioForm;
import br.com.bolao.services.TokenService;
import br.com.bolao.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<UsuarioDto> inserir(@Valid @RequestBody UsuarioForm form, UriComponentsBuilder uriBuilder){
		UsuarioDto dto = service.inserir(form);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("/token")
	public ResponseEntity<?> token(@RequestBody @Valid TokenForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.toUsernamePasswordAuthenticationToken();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
