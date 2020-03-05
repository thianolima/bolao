package br.com.bolao.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"idjogo", "idusuario", "idbolao"}, name="uk_ranking"))
public class Ranking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include	
	private Integer id;
	
	@NotNull
	private Integer pontos;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idjogo", nullable = false, foreignKey= @ForeignKey(name = "fk_ranking_jogo"))
	private Jogo jogo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idusuario", nullable = false, foreignKey= @ForeignKey(name = "fk_ranking_usuario"))	
	private Usuario participante;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idbolao", nullable = false, foreignKey= @ForeignKey(name = "fk_ranking_bolao"))
	private Bolao bolao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idpalpite", nullable = false, foreignKey= @ForeignKey(name = "fk_ranking_palpite"))	
	private Palpite palpite;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idresultado", nullable = false, foreignKey= @ForeignKey(name = "fk_ranking_resultado"))	
	private Resultado resultado;
}
