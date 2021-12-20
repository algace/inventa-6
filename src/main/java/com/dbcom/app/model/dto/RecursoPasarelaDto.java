package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RecursoPasarelaDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@NotNull(message = "{validation.notNull}")
	private TipoChasisDto tipoChasis;
	
	@NotNull(message = "{validation.notNull}")
	private FuncionPasarelaDto funcionPasarela;
	
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private Integer indiceCarga;
	
	private List<TipoChasisDto> tiposChasis;
	
	private List<FuncionPasarelaDto> funcionPasarelas;

}
