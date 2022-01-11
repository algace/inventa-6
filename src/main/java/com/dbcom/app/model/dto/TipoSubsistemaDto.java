package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Dto para Subsistemas
 * 
 * @author neoris
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = {"tipoSistema"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSubsistemaDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9003153481472335966L;
	
    private Long id;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	private TipoInterfazOperacionDto tipoInterfazOperacion;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoSistemaLiteDto tipoSistema;
	
}
