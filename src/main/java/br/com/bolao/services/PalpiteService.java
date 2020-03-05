package br.com.bolao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.PalpiteDto;
import br.com.bolao.entities.Palpite;
import br.com.bolao.forms.PalpiteForm;
import br.com.bolao.repositories.BolaoRepository;
import br.com.bolao.repositories.JogoRepository;
import br.com.bolao.repositories.PalpiteRepository;
import br.com.bolao.repositories.UsuarioRepository;

@Service
public class PalpiteService {

	@Autowired
	PalpiteRepository palpiteRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	JogoRepository jogoRepository;
	
	@Autowired
	BolaoRepository bolaoRepository;
	
	@Transactional
	public PalpiteDto inserir(PalpiteForm form) {
		Palpite palpite = form.toEntity(jogoRepository, usuarioRepository, bolaoRepository);
		
		if (!palpite.getBolao().getParticipantes().contains(palpite.getParticipante())) {
			throw new IllegalArgumentException("O usuario informado não esta participando desse bolão !");
		}
				
		return new PalpiteDto(palpiteRepository.save(palpite));		
	}

}
