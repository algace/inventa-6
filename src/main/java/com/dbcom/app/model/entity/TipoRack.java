package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para tipos de rack
 * 
 * @author jose.vallve
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoRack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4828278052750907315L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPORACK_SEQ")
	@SequenceGenerator(sequenceName = "tiporack_seq", initialValue = 1, allocationSize = 1 , name = "TIPORACK_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
}
