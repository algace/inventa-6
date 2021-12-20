package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

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
	private String nombre;
	
	private String descripcion;
	
	private String airBlocks;
	
	@NotNull(message = "{validation.notNull}")
	private RegionMantenimientoDto regionMantenimiento;
	
	@Builder.Default
	private List<RegionMantenimientoDto> regionesMantenimientoDisponibles = new ArrayList<>();

}
