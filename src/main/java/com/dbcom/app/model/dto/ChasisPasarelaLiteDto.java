package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.dbcom.app.model.entity.TipoChasis;

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
	private TipoChasis tipoChasis;
	
	private Boolean isSeleccionado;
}
