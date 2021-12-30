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
public final class FuncionPasarelaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
}
