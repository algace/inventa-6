package com.dbcom.app.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmbitoRecurso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AMBITORECURSO_SEQ")
	@SequenceGenerator(sequenceName = "ambitorecurso_seq", initialValue = 1, allocationSize = 1 , name = "AMBITORECURSO_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="funcionPasarela_id")
	@NotNull(message = "{validation.notNull}")
	private FuncionPasarela funcionPasarela;

}
