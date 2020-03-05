package br.com.bolao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bolao.entities.Time;

public interface TimeRepository extends JpaRepository<Time, Integer>{

}
