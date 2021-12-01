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
public final class FrecuenciaATCDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 95067248604111107L;

	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String dependencia;

	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;

	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String tipoServicio;
	
	private Boolean frecuenciaBackup;
	
	@Size(max = 250, message = "{validation.max250}")
	private String titular;
	
	@Size(max = 250, message = "{validation.max250}")
	private String docOACI;
	
	@Size(max = 250, message = "{validation.max250}")
	private String observaciones;
	
}
