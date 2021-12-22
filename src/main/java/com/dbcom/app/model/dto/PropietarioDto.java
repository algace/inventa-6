package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropietarioDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Short id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String propietario;
	
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;
	

}
