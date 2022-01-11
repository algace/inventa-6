package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public final class FrecuenciaATCDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 95067248604111107L;

	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String dependencia;

	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;

	@NotNull(message = "{validation.notNull}")
	@Valid
	private ServicioRadioLiteDto tipoServicio;
	
	@NotNull(message = "{validation.notNull}")
	private String frecuenciaBackup;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private PropietarioLiteDto titular;
	
	//@Size(max = 250, message = "{validation.max250}")
	//private String docOACI;
	
	private String docOACITipo;
	
	private String docOACIRadio;
	
	@Max(999)
	private Integer docOACIFL;
	
	private String docOACILatitud;

	private String docOACILongitud;
	
	@Size(max = 250, message = "{validation.max250}")
	private String observaciones;
	
	
	private List<PropietarioLiteDto> titularesDisponibles;
	
	private List<ServicioRadioLiteDto> tiposServicioDisponibles;
	
}
