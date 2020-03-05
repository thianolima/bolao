package br.com.bolao.entities;

import java.time.LocalDateTime;

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
public class Convite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include	
	private Integer id;
	
	@NotNull
	private String email;
	
	@NotNull
	private LocalDateTime dataExpiracao;
	
	@NotNull
	private Boolean cadastrado;
		
	private LocalDateTime dataAceite;
	
	@ManyToOne
	@JoinColumn(name = "idbolao", nullable = false, foreignKey = @ForeignKey(name = "fk_convite_bolao"))
	private Bolao bolao;
}
