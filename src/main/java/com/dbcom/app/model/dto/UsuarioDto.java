package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

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
public final class UsuarioDto implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 174068732641807793L;

	private Long id;
	
	private UsuarioPerfilDto usuarioPerfil;
	
	private RolDto rol;
	 
	@Size(min = 1, max = 120, message = "{validation.min1max120}")
	private String nombreApellidos;
	
}
