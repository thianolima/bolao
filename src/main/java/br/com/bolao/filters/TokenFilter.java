package br.com.bolao.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.bolao.entities.Usuario;
import br.com.bolao.repositories.UsuarioRepository;
import br.com.bolao.services.TokenService;

public class TokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public TokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if (valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);		
	}
	
	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario.intValue()).get();
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}		
		return token.substring(7, token.length());
	}

}
