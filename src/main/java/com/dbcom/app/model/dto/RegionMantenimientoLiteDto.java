package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para validación una región de manetnimiento
 * 
 * @author neoris
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionMantenimientoLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1247066495454874666L;

	@NotNull(message = "{validation.notNull}")
    private Long id;
	
	private String nombre;
	
	private String descripcion;
}
