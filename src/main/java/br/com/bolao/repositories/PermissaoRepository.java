package br.com.bolao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bolao.entities.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, String>{

}
