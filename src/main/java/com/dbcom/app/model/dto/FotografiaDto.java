package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.persistence.Lob;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO con el que evitamos exponer la entidad Fotografia en la vista
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FotografiaDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 341727455835606248L;

	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	 
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;
	
	@Lob
	private byte[] contenido;
	
	private EquipamientoDto equipamiento;
}
