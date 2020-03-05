package br.com.bolao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.ResultadoDto;
import br.com.bolao.entities.Resultado;
import br.com.bolao.forms.ResultadoForm;
import br.com.bolao.repositories.JogoRepository;
import br.com.bolao.repositories.ResultadoRepository;

@Service
public class ResultadoService {

	@Autowired
	ResultadoRepository resultadoRepository;
	
	@Autowired
	JogoRepository jogoRepository;
	
	@Transactional
	public ResultadoDto inserir(ResultadoForm form) {
		Resultado resultado = form.toEntity(jogoRepository);
		return new ResultadoDto(resultadoRepository.save(resultado));
	}
}
