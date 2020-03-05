package br.com.bolao.entities;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include	
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idtimecasa", nullable = false, foreignKey= @ForeignKey(name = "fk_jogo_timecasa"))
	private Time casa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idtimevisitante", nullable = false, foreignKey= @ForeignKey(name = "fk_jogo_timevisitante"))
	private Time visitante;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idcampeonato", nullable = false, foreignKey= @ForeignKey(name = "fk_jogo_campeonato"))
	private Campeonato campeonato;
	
	@NotNull
	private LocalTime horario;
	
	@NotNull
	private Integer rodada;
}
