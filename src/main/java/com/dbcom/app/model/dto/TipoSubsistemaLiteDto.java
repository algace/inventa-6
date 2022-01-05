package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
public class TipoSubsistemaLiteDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9003153481472335966L;
	
	@NotNull(message = "{validation.notNull}")
    private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private String interfazOperacion;
	
	private TipoSistemaLiteDto tipoSistema;

}
