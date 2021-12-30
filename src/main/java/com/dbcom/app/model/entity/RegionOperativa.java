package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para sistemas
 * 
 * @author neoris
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionOperativa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3465100713296853846L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGIONES_OPERATIVAS_SEQ")
	@SequenceGenerator(sequenceName = "regiones_operativas_seq", initialValue = 1, allocationSize = 1 , name = "REGIONES_OPERATIVAS_SEQ")
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;

}
