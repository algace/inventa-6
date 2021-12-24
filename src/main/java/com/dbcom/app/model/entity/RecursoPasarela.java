package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
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
public class RecursoPasarela implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECURSOPASARELA_SEQ")
	@SequenceGenerator(sequenceName = "recursopasarela_seq", initialValue = 1, allocationSize = 1 , name = "RECURSOPASARELA_SEQ")
	private Short id;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipoChasis_id")
	@NotNull(message = "{validation.notNull}")
	private TipoChasis tipoChasis;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="funcionPasarela_id")
	@NotNull(message = "{validation.notNull}")
	private FuncionPasarela funcionPasarela;
	
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private Integer indiceCarga;
}
