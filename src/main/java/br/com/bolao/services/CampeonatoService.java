package br.com.bolao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.CampeonatoDto;
import br.com.bolao.entities.Campeonato;
import br.com.bolao.forms.CampeonatoForm;
import br.com.bolao.repositories.CampeonatoRepository;
import br.com.bolao.repositories.TimeRepository;

@Service
public class CampeonatoService {

	@Autowired
	CampeonatoRepository repository;
	
	@Autowired
	TimeRepository timeRepository;
	
	@Transactional
	public CampeonatoDto inserir(CampeonatoForm form) {
		Campeonato campeonato = form.toEntity(timeRepository);
		return new CampeonatoDto(repository.save(campeonato));
	}
}
