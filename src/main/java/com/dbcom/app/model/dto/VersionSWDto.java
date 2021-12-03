package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO con el que evitamos exponer la entidad en la vista
 * 
 * @author jose.vallve
 */
@Data
// Evitamos referencias circulares
@EqualsAndHashCode(exclude = "aplicacionesSW")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class VersionSWDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 500758903917083656L;

	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	 
	@Size(min = 1, max = 250, message = "{validation.min1max250}")
	private String descripcion;

	@Builder.Default
	private List<AplicacionSWDto> aplicacionesSW = new ArrayList<>();
	
	@Builder.Default
	private List<AplicacionSWDto> aplicacionesSWNoIncluidas = new ArrayList<>();
	
}
