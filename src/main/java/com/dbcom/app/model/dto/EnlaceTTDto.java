package com.dbcom.app.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Dto para Enlaces T/T
 * 
 * @author neoris
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = "tipoEnlaceTT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnlaceTTDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9099173472090127673L;

	private Long id;
	
	private String nombre;
	
	private String vano;
	
	private TipoEnlaceTTDto tipoEnlaceTT;
	
	private String capacidad;
	
}
