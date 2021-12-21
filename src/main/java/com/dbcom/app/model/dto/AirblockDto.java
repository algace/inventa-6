package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public final class AirblockDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4794739194611998910L;

	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private String flMin;
	
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private String flMax;

	@Size(max = 250, message = "{validation.max250}")
	private String coordenadas;
	
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;
	
	@Size(max = 250, message = "{validation.max250}")
	private String sectoresATC;
	
	@Builder.Default
	private List<SectorATCDto> sectorATCList = new ArrayList<>();
	
}
