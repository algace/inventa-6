package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para validación una tipo de sistema
 * 
 * @author neoris
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSistemaLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1236061390001197899L;

	@NotNull(message = "{validation.notNull}")
	private Long id;
	
	private String nombre;
	
	private String descripcion;
}
