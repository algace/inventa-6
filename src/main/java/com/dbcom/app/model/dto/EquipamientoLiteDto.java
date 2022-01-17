package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
public class EquipamientoLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4330477132643931233L;

	/**
	 * 
	 */
	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String marca;

	@Size(max = 50, message = "{validation.min1max50}")
	private String modelo;

	private Integer entradas;
	
	private Integer salidas;

	@NotNull(message = "{validation.notNull}")
	private Double ganancia;

	@NotNull(message = "{validation.notNull}")
	private Double perdida;

	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoSistemaLiteDto tipoSistema;
	
	@NotEmpty(message = "{validation.notNull}")
	@Valid
	private TipoSubsistemaLiteDto tipoSubsistema;
}
