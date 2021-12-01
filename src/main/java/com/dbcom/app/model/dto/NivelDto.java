package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.dbcom.app.model.entity.Rol;
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
public final class NivelDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3513741012864110545L;

	private Long id;
	
	@Size(max = 50, message = "{validation.max50}")
	private String nombre;
	
	private Rol rol;
	
}
