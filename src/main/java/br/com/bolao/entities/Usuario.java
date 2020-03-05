package br.com.bolao.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"login"}, name="uk_usuario"))
public class Usuario  implements UserDetails{

	private static final long serialVersionUID = -1064985354538278536L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include	
	private Integer id;
	
	@NotNull
	@Column(length = 50, nullable = false)
	private String login;
	
	@NotNull
	@Column(length = 256, nullable = false)
	private String senha;
	
	@NotNull
    private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Permissao> permissoes = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return this.permissoes;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
