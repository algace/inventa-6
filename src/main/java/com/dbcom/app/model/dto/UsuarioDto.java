package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.dbcom.app.model.entity.Rol;
import com.dbcom.app.model.entity.UsuarioPerfil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
	
	private UsuarioPerfil usuarioPerfil;
	
	private Rol rol;
	 
	@Size(min = 1, max = 120, message = "{validation.min1max120}")
	private String nombreApellidos;
	
}
