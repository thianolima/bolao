package br.com.bolao.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"nome"}, name="uk_campeonato"))
public class Campeonato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include	
	private Integer id;
	
	@NotNull
	@Column(length = 50, nullable = false)
	private String nome;
	
	@NotNull
    private LocalDate dataInicio = LocalDate.now();
	
	@NotNull
	private Integer qtdTimes;
	
	@ManyToMany
	@JoinTable(
	  name = "campeonato_times", 
	  joinColumns = @JoinColumn(name = "idcampeonato"), 
	  inverseJoinColumns = @JoinColumn(name = "idtime"))
	private Set<Time> times;
}
