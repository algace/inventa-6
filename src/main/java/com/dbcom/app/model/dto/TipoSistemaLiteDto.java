package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para validación una tipo de sistema
 * 
 * @author neoris
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSistemaLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1236061390001197899L;

	@NotNull(message = "{validation.notNull}")
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private String color;
	
	private String colorTexto;
	
	@NotNull(message = "{validation.notNull}")
	@Min(0)
	@Max(250)
	private Float codigoFuncionRed;
}
