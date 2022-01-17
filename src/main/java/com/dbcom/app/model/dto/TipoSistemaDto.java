package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para Sistemas
 * 
 * @author neoris
 */
@Data
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
	@Builder.Default
	private String color = "#ffffff";
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String colorTexto;
	
	@NotNull(message = "{validation.notNull}")
	@Min(0)
	@Max(250)
	private Float codigoFuncionRed;
	
}
