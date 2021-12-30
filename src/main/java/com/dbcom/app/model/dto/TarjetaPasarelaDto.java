package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class TarjetaPasarelaDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@Builder.Default
	private List<ChasisPasarelaLiteDto> chasisPasarelas = new ArrayList<>();
}
