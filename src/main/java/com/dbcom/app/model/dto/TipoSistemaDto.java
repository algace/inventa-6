package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Dto para Sistemas
 * 
 * @author neoris
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
	@Size(min = 1, message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String color;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String colorTexto;
	
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##")
	private Double codigoFuncionRed;
	
	@Builder.Default
	private List<TipoSubsistemaDto> tiposSubsistemas = new ArrayList<>();
}
