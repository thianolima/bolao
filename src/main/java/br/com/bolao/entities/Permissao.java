package br.com.bolao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"nome"}, name="uk_permissao"))
public class Permissao implements GrantedAuthority{

	private static final long serialVersionUID = 4020422192020935275L;
	
	@Id
	@EqualsAndHashCode.Include	
	String nome;
	
	@Override
	public String getAuthority() {
		return this.nome;
	}

}
