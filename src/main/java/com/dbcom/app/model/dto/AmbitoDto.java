package com.dbcom.app.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO con el que evitamos exponer la entidad en la vista
 * 
 * @author eduardo.tubilleja
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class AmbitoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1206363712811095988L;

	
	private Long id;
	
	private String nombre;
	
	private RolDto rol;
	
}
