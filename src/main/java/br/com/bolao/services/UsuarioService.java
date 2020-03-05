package br.com.bolao.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bolao.dtos.UsuarioDto;
import br.com.bolao.entities.Permissao;
import br.com.bolao.entities.Usuario;
import br.com.bolao.enums.PermissaoEnum;
import br.com.bolao.forms.UsuarioForm;
import br.com.bolao.repositories.PermissaoRepository;
import br.com.bolao.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	@Transactional
	public UsuarioDto inserir(UsuarioForm form) {	
		Usuario usuario = form.toEntity();		
		
		String senha = usuario.getSenha(); 
		senha = new BCryptPasswordEncoder().encode(senha);
		usuario.setSenha(senha);
		
		Permissao permissao = permissaoRepository.findById(PermissaoEnum.JOGADOR.toString())
			.orElseThrow(() ->  new UsernameNotFoundException("Dados inválidos!"));
		
		List<Permissao> permissoes = new ArrayList<Permissao>();
		permissoes.add(permissao);
		usuario.setPermissoes(permissoes);
		
		return new UsuarioDto(usuarioRepository.save(usuario));
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioRepository.findByLogin(login)
			.orElseThrow(() ->  new EmptyResultDataAccessException("Usuario não cadastrado no sistema !", 0));

	}
}
