package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
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
public final class TipoTopologiaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7646452395955002487L;

	private Short id;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;

}
