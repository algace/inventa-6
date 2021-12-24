package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionPasarelaLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5515279126181661133L;

	@NotNull(message = "{validation.notNull}")
	@Min(value = 1, message = "{validation.notNull}")
	private Long id;
	
	private String nombre;
}

