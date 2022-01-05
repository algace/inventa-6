package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO con el que evitamos exponer la entidad en la vista
 * 
 * @author eduardo.tubilleja
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class FrecuenciaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374747257442577338L;

	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoUnidadFrecuenciaLiteDto tipoUnidadFrecuencia;
	
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##")
	private Double valor;

	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoBandaFrecuenciaLiteDto tipoBandaFrecuencia;
	
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPublicacion;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoFuenteInformacionLiteDto tipoFuenteInformacion;
	
	private String observaciones;
	
}
