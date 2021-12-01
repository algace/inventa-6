package com.dbcom.app.model.dto;

import java.io.Serializable;

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
public final class PermisosDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1087680513344405601L;

	
	private Long id;
	
	private Boolean soloLectura;

	private Boolean lecturaEscritura;
	
	private Rol rol;
	
}
