package br.com.bolao.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.bolao.entities.Bolao;
import br.com.bolao.entities.Convite;
import br.com.bolao.entities.Usuario;
import br.com.bolao.forms.UsuarioForm;
import br.com.bolao.repositories.BolaoRepository;
import br.com.bolao.repositories.ConviteRepository;
import br.com.bolao.repositories.UsuarioRepository;

@Service
public class ConviteService {

	@Autowired
	ConviteRepository conviteRepository;
	
	@Autowired
	BolaoRepository bolaoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
		
	public String validar(Integer id) {
		Convite convite = conviteRepository.findById(id)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Convite não cadastrado no sistema !", id));
		
		LocalDateTime hoje = LocalDateTime.now();
		if(hoje.isAfter(convite.getDataExpiracao())) {
			throw new IllegalArgumentException("Convite expirado");
		
		} else if(convite.getCadastrado()) {
			associarUsuarioBolao(convite.getEmail(), convite.getBolao().getId());
			
			convite.setDataAceite(hoje);
			conviteRepository.save(convite);
			
			return "Ativo";
			
		} else{
			return "Cadastro";
		}
	}
	
	public String cadastrar(String login, String senha, Integer idConvite) {		
		UsuarioForm form = new UsuarioForm();
		form.setLogin(login);
		form.setSenha(senha);							

		Convite convite = conviteRepository.findById(idConvite)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Convite não cadastrado no sistema !", idConvite));
		
		usuarioService.inserir(form);						
		associarUsuarioBolao(login, convite.getBolao().getId());
		
		return "Ativo"; 		
	}
	
	@Transactional
	private void associarUsuarioBolao(String email, Integer idBolao) {
		Usuario participante = usuarioRepository.findByLogin(email)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Usuario não cadastrado no sistema !", 0));
		
		Bolao bolao = bolaoRepository.findById(idBolao)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Bolão não cadastrado no sistema !", 0));
		
		Set<Usuario> participantes = new HashSet<Usuario>();
		participantes = bolao.getParticipantes();
		participantes.add(participante);
		bolao.setParticipantes(participantes);
		bolaoRepository.save(bolao);
	}
}
