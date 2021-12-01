package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO con el que evitamos exponer la entidad en la vista
 * 
 * @author jose.vallve
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class TipoModulacionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2490365952467966757L;

	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@Size(max = 100, message = "{validation.max100}")
	private String descripcionCorta;
	
	@Size(max = 500, message = "{validation.max500}")
	private String descripcionLarga;

}
