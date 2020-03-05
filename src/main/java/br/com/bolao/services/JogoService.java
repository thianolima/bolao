package br.com.bolao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.JogoDto;
import br.com.bolao.entities.Jogo;
import br.com.bolao.forms.JogoForm;
import br.com.bolao.repositories.CampeonatoRepository;
import br.com.bolao.repositories.JogoRepository;
import br.com.bolao.repositories.TimeRepository;

@Service
public class JogoService {

	@Autowired
	JogoRepository jogoRepository;
	
	@Autowired
	CampeonatoRepository campeonatoRepository;
	
	@Autowired
	TimeRepository timeRepository;
	
	@Transactional
	public JogoDto inserir(JogoForm form) {		
		Jogo jogo = form.toEntity(timeRepository, campeonatoRepository);
		
		if(jogo.getVisitante().equals(jogo.getCasa())) {
			throw new IllegalArgumentException("Os times precisam ser diferentes para havar um jogo !");
		}
		
		if (jogoRepository.pesquisarJogoRodada(
				jogo.getRodada(), 
				jogo.getCasa().getId(), 
				jogo.getVisitante().getId()) > 0) {
			throw new IllegalArgumentException("Um dos times ja irá jogar nessa rodada");
		}
		
		return new JogoDto(jogoRepository.save(jogo));				
	}
}
