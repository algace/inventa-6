package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChasisPasarelaLiteDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short id;
	
	@NotNull(message = "{validation.notNull}")
	private TipoChasisLiteDto tipoChasis;
	
	@Builder.Default
	private Boolean isSeleccionado = false;
}
