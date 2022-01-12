package com.dbcom.app.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = {"tipoTopologia"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedTTLiteDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nombre;
	
	private String observaciones;
	
	private TipoTopologiaLiteDto tipoTopologia;

}
