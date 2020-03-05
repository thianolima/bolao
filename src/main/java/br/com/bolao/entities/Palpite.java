package br.com.bolao.entities;

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

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Palpite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include	
	private Integer id;

	@NotNull
	private Integer golsCasa;
	
	@NotNull
	private Integer golsVisitante;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idjogo", nullable = false, foreignKey= @ForeignKey(name = "fk_palpite_jogo"))
	private Jogo jogo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idusuario", nullable = false, foreignKey= @ForeignKey(name = "fk_palpite_usuario"))
	private Usuario participante;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idbolao", nullable = false, foreignKey= @ForeignKey(name = "fk_palpite_bolao"))
	private Bolao bolao;
}
