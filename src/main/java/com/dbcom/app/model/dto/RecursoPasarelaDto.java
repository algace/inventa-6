package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = {"tipoChasis", "funcionPasarela"})
@AllArgsConstructor
@NoArgsConstructor
public final class RecursoPasarelaDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoChasisLiteDto tipoChasis;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private FuncionPasarelaLiteDto funcionPasarela;
	
	@NotNull(message = "{validation.notNull}")
	@Min(0)
	@Max(128)
	private Short indiceCarga;

}
