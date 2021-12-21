package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Dto para Sector de mantenimiento
 * 
 * @author jgm
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = {"regionMantenimiento", "regionesMantenimientoDisponibles"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorMantenimientoDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6587304688580657739L;

	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private RegionMantenimientoLiteDto regionMantenimiento;
	
	@Builder.Default
	private List<RegionMantenimientoDto> regionesMantenimientoDisponibles = new ArrayList<>();

}
