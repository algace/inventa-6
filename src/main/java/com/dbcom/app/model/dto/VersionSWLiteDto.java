package com.dbcom.app.model.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VersionSWLiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 500758903917083656L;

	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	 
	@Size(min = 1, max = 250, message = "{validation.min1max250}")
	private String descripcion;
}
