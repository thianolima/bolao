package br.com.bolao.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Bolao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include	
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idusuario", nullable = false, foreignKey= @ForeignKey(name = "fk_bolao_usuario"))
	private Usuario dono;
	
	@NotNull
	private LocalDateTime dataCriacao = LocalDateTime.now();;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idcampeonato", nullable = false, foreignKey= @ForeignKey(name = "fk_bolao_campeonato"))
	private Campeonato campeonato;
	
	@OneToMany(mappedBy = "bolao") 
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Convite> convites;
	
	@ManyToMany
	@JoinTable(
	  name = "bolao_participantes", 
	  joinColumns = @JoinColumn(name = "idbolao"), 
	  inverseJoinColumns = @JoinColumn(name = "idusuario"))
	private Set<Usuario> participantes;
}
