package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = {"regionOperativa"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorOACIDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3915457942455625564L;
	
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	@Size(min = 1, message = "{validation.notNull}")
	private String nombre;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private RegionOperativaLiteDto regionOperativa;
	
	@Max(999)
	private Integer flMin;

	@Max(999)
	private Integer flMax;

	private String descripcion;
	
	private String coordenadas;
	
}
