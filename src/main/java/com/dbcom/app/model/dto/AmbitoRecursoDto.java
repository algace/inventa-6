package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dbcom.app.model.entity.FuncionPasarela;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class AmbitoRecursoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@NotNull(message = "{validation.notNull}")
	private FuncionPasarela funcionPasarela;
	
	private List<FuncionPasarelaDto> funcionPasarelas;

}
