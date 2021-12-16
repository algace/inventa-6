package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Dto para Sistemas
 * 
 * @author jgm
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = "tiposSubsistemas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSistemaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7876728990148613745L;

	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	@NotNull(message = "{validation.notNull}")
	private String color;
	
	@NotNull(message = "{validation.notNull}")
	private String colorTexto;
	
	private String ejemplo;
	
	@NotNull(message = "{validation.notNull}")
	private String codigoFuncionRed;
	
	@Builder.Default
	private List<TipoSubsistemaDto> tiposSubsistemas = new ArrayList<>();
}
