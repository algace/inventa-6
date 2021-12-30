package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para que se valide correctamente el tipo de topologia asociado a una red T/T
 * 
 * @author jose.vallve
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoTopologiaLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5097503327625899783L;

	@NotNull(message = "{validation.notNull}")
	private Short id;
	
	private String nombre;
}
