package br.com.bolao.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.dao.EmptyResultDataAccessException;

import br.com.bolao.entities.Bolao;
import br.com.bolao.entities.Campeonato;
import br.com.bolao.entities.Convite;
import br.com.bolao.entities.Usuario;
import br.com.bolao.repositories.CampeonatoRepository;
import br.com.bolao.repositories.UsuarioRepository;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class BolaoForm {
	
	@NotNull
	private Integer campeonato;
		
	@Size(min = 1, message = "Obrigatório informar no mínimo um convite !")
	List<ConviteForm> convites;

	public Bolao toEntity(UsuarioRepository usuarioRepository, CampeonatoRepository campeonatoRepository) {
		Bolao entity = new Bolao();
	
		Campeonato campeonato = campeonatoRepository.findById(this.campeonato)
			.orElseThrow(() ->	new EmptyResultDataAccessException("Campeonato não cadastrado no sistema !", this.campeonato));
		
		entity.setCampeonato(campeonato);
				
		List<Convite> convitesBolao = new ArrayList<Convite>();
		for(int i = 0; i < convites.size() ; i++) {			
			Convite convite = new Convite();
			
			String email = convites.get(i).getEmail();			
			convite.setEmail(email);			
			
			Optional<Usuario> usuario = usuarioRepository.findByLogin(email);
			convite.setCadastrado(usuario.isPresent());
			
			convitesBolao.add(convite);
		}						
		entity.setConvites(convitesBolao);
		
		return entity;
	}
	
}
