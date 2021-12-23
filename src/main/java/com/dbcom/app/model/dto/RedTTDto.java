package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Dto para Redes T/T
 * 
 * @author neoris
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = {"tipoTopologia"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedTTDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8160958487793738229L;

	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String nombre;
	
	private String observaciones;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoTopologiaLiteDto tipoTopologia;
	
	@Builder.Default
	private List<EnlaceTTDto> enlacesTT = new ArrayList<>();
	
	@Builder.Default
	private List<FotografiaDto> fotografias = new ArrayList<>();
	
	@Builder.Default
	private List<TipoTopologiaDto> tiposTopologiaDisponibles = new ArrayList<>();
	
	@Builder.Default
	private List<EnlaceTTDto> enlacesTTDisponibles = new ArrayList<>();
}
