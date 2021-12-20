package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
//Evitamos referencias circulares
//@EqualsAndHashCode(exclude = "equipamientos")
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
	private String nombre;
	
	private String descripcion;
}
