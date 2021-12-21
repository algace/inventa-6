package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para Sistemas
 * 
 * @author jgm
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionMantenimientoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7441571973884824233L;

	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
}
