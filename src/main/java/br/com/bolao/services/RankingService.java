package br.com.bolao.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.RankingDto;
import br.com.bolao.entities.Bolao;
import br.com.bolao.entities.Palpite;
import br.com.bolao.entities.Ranking;
import br.com.bolao.entities.Resultado;
import br.com.bolao.entities.Usuario;
import br.com.bolao.enums.GanhadorEnum;
import br.com.bolao.enums.TipoAcertoEnum;
import br.com.bolao.forms.RankingForm;
import br.com.bolao.repositories.BolaoRepository;
import br.com.bolao.repositories.ConviteRepository;
import br.com.bolao.repositories.JogoRepository;
import br.com.bolao.repositories.PalpiteRepository;
import br.com.bolao.repositories.RankingRepository;
import br.com.bolao.repositories.ResultadoRepository;

@Service
public class RankingService {

	@Autowired
	RankingRepository rankingRepository;
	
	@Autowired
	ResultadoRepository resultadoRepository;
	
	@Autowired
	PalpiteRepository palpiteRepository;
	
	@Autowired
	BolaoRepository bolaoRepository;
	
	@Autowired
	JogoRepository jogoRepository;
	
	@Autowired
	ConviteRepository conviteRepository;
	
	@Transactional
	public List<RankingDto> processar(RankingForm form){
		
		rankingRepository.excluirProcessamentoJogoBolao(form.getJogo(), form.getBolao());
		
		Resultado resultado = resultadoRepository.findByJogoId(form.getJogo())
			.orElseThrow(() ->  new EmptyResultDataAccessException("Resultado não cadastrado no sistema !", 0));
		
		Bolao bolao = bolaoRepository.findById(form.getBolao())
			.orElseThrow(() ->  new EmptyResultDataAccessException("Bolão não cadastrado no sistema !", 0));

		List<RankingDto> dtos = new ArrayList<RankingDto>();
		
		Iterator<Usuario> participantes = bolao.getParticipantes().iterator(); 
		while(participantes.hasNext()) {
			Usuario participante = participantes.next();
			
			Palpite palpite = palpiteRepository
				.pesquisarPalpitesJogoBolaoParticipante(form.getJogo(), form.getBolao(), participante.getId())
				.orElseThrow(() ->  new EmptyResultDataAccessException(
						" Palpite do participante " + participante.getLogin() + 
						" não cadastrado no sistema !", participante.getId()));					
			
			GanhadorEnum resultadoGanhador = getGanhadorResultado(resultado);						
			GanhadorEnum palpiteGanhador = getGanhadorPalpite(palpite);															
			
			Ranking ranking = new Ranking();			
			ranking.setPontos(calcularPontos(resultado, palpite, resultadoGanhador, palpiteGanhador));
			ranking.setJogo(resultado.getJogo());
			ranking.setParticipante(participante);
			ranking.setBolao(bolao);
			ranking.setResultado(resultado);
			ranking.setPalpite(palpite);
			
			dtos.add(new RankingDto(rankingRepository.save(ranking)));			
		}
		
		return dtos;
	}
	
	private GanhadorEnum getGanhadorPalpite(Palpite palpite) {
		if(palpite.getGolsCasa() > palpite.getGolsVisitante()) {
			return GanhadorEnum.CASA;			
		}
		else if(palpite.getGolsCasa() < palpite.getGolsVisitante()) {
			return GanhadorEnum.VISITANTE;
		} else {
			return GanhadorEnum.EMPATE;
		}
	}
	
	private GanhadorEnum getGanhadorResultado(Resultado resultado) {
		if(resultado.getGolsCasa() > resultado.getGolsVisitante()) {
			return GanhadorEnum.CASA;			
		}
		else if(resultado.getGolsCasa() < resultado.getGolsVisitante()) {
			return GanhadorEnum.VISITANTE;
		} else {
			return GanhadorEnum.EMPATE;
		}
	}
	
	private Integer calcularPontos(Resultado resultado, Palpite palpite, GanhadorEnum resultadoGanhador, GanhadorEnum palpiteGanhador) {		
		
		if((palpite.getGolsCasa() == resultado.getGolsCasa()) && (palpite.getGolsVisitante() == resultado.getGolsVisitante())){
			
			return TipoAcertoEnum.NaLata.pontos;			
		
		}else if((palpiteGanhador == resultadoGanhador) && 
				 ((palpite.getGolsCasa() == resultado.getGolsCasa()) || (palpite.getGolsVisitante() == resultado.getGolsVisitante()))) {

			return TipoAcertoEnum.GanhadorGols.pontos;
		
		}else if((palpiteGanhador == resultadoGanhador) && 
				 ((palpite.getGolsCasa() != resultado.getGolsCasa()) && (palpite.getGolsVisitante() != resultado.getGolsVisitante()))){

			return TipoAcertoEnum.Ganhador.pontos;
		
		}else if((palpiteGanhador != resultadoGanhador) && 
				 ((palpite.getGolsCasa() == resultado.getGolsCasa()) || (palpite.getGolsVisitante() == resultado.getGolsVisitante()))) {
			
			return TipoAcertoEnum.Gols.pontos;
		}
		
		return TipoAcertoEnum.Errado.pontos;
	}
}
