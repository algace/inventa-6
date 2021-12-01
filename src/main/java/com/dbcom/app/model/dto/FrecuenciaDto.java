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
 * @author eduardo.tubilleja
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class FrecuenciaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374747257442577338L;

	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String valor;

	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String unidad;

	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String banda;
	
	@Size(max = 250, message = "{validation.max250}")
	private String fechaPublicacion;
	
	@Size(max = 250, message = "{validation.max250}")
	private String fuente;
	
	@Size(max = 250, message = "{validation.max250}")
	private String observaciones;
	
}
