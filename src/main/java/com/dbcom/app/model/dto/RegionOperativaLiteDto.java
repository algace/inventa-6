package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para validación una región operativa
 * 
 * @author neoris
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionOperativaLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6710770299966165281L;

	@NotNull(message = "{validation.notNull}")
	private Long id;
	
	private String nombre;
	
	private String descripcion;
}
