package com.dbcom.app.model.dto;

import java.io.Serializable;
import javax.validation.constraints.Size;

import com.dbcom.app.model.entity.Ambito;
import com.dbcom.app.model.entity.Nivel;
import com.dbcom.app.model.entity.Permisos;
import com.dbcom.app.model.entity.Usuario;
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
public final class RolDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2756550680770463641L;

	private Long id;
	
	private Nivel nivel;
	
	private Ambito ambito;
	
	private Permisos permisos;
	 
	@Size(max = 50, message = "{validation.max50}")
	private Integer idLocalizacion;

	private Boolean inventario;
	
	private Boolean planVoIP;
	
    private Usuario usuario;
	
}
