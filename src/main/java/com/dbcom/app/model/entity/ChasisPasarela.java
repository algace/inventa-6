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
import javax.validation.constraints.Min;
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
public class ChasisPasarela implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHASISPASARELA_SEQ")
	@SequenceGenerator(sequenceName = "chasispasarela_seq", initialValue = 1, allocationSize = 1 , name = "CHASISPASARELA_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipoChasis_id")
	@NotNull(message = "{validation.notNull}")
	private TipoChasis tipoChasis;
	
	@NotNull(message = "{validation.notNull}")
	@Min(0)
	@Max(128)
	private Short indiceCargaLimite;

}
