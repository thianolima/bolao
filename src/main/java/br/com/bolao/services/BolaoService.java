package br.com.bolao.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.BolaoDto;
import br.com.bolao.entities.Bolao;
import br.com.bolao.entities.Convite;
import br.com.bolao.entities.Usuario;
import br.com.bolao.forms.BolaoForm;
import br.com.bolao.repositories.BolaoRepository;
import br.com.bolao.repositories.CampeonatoRepository;
import br.com.bolao.repositories.ConviteRepository;
import br.com.bolao.repositories.UsuarioRepository;

@Service
public class BolaoService {

	@Autowired
	BolaoRepository bolaoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ConviteRepository conviteRepository;
	
	@Autowired
	CampeonatoRepository campeonatoRepository;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	EmailService emailService;
	
	@Value("${convite.expiration}")
	private String expiration;
	
	
	@Transactional
	public BolaoDto inserir(BolaoForm form, String token) {
		Bolao bolao = form.toEntity(usuarioRepository, campeonatoRepository);

		Integer idUsuario = tokenService.tokenToIdUsuario(token);

		Usuario dono = usuarioRepository.findById(idUsuario)
			.orElseThrow(() -> new EmptyResultDataAccessException("Usuário não cadastrado no sistema !", idUsuario));

		bolao.setDono(dono);
		
		bolao = bolaoRepository.save(bolao);
		
		for(Convite convite : bolao.getConvites()) {
			convite.setBolao(bolao);
			convite.setDataExpiracao(LocalDateTime.now().plusMinutes(Long.parseLong(expiration)));
			
			conviteRepository.save(convite);
			
			emailService.enviar(
				convite.getEmail(),
				"Bolão - " + bolao.getCampeonato().getNome(),
				"Clique abaixo para ativar sua participação no Bolão <br/>" +
			    "<a href='http://localhost:8080/convites/"+ convite.getId() +"'> Aceitar</a>");
		}
						
		return new BolaoDto(bolao);
	}
	
}
