package br.com.bolao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.TimeDto;
import br.com.bolao.entities.Time;
import br.com.bolao.forms.TimeForm;
import br.com.bolao.repositories.TimeRepository;

@Service
public class TimeService {
	
	@Autowired
	TimeRepository repository;
	
	@Transactional
	public TimeDto inserir(TimeForm form) {
		Time time = form.toEntity();
		return new TimeDto(repository.save(time));
	}
}
