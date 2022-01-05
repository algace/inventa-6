package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.dbcom.app.model.entity.TipoSubsistema;

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

	@Max(999)
	private Short entradas;
	
	@Max(999)
	private Short salidas;

	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double ganancia;

	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double perdida;

	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoSistemaLiteDto tipoSistema;
	
	@NotEmpty(message = "{validation.notNull}")
	@Valid
	private TipoSubsistema tipoSubsistema;
}
