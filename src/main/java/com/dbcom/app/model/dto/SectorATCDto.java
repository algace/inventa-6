package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.dbcom.app.model.entity.RegionOperativa;
import com.dbcom.app.model.entity.TipoFuenteInformacion;
import com.dbcom.app.model.entity.TipoSectorATC;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class SectorATCDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@NotNull(message = "{validation.notNull}")
	//private RegionOperativato regionOperativa;
	private RegionOperativa regionOperativa;
	
	@NotNull(message = "{validation.notNull}")
	//private TipoSectorATCDto tipoSectorATC;
	private TipoSectorATC tipoSectorATC;
	
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPublicacion;
	
	@NotNull(message = "{validation.notNull}")
	//private TipoFuenteInformacionDto tipoFuenteInformacion;
	private TipoFuenteInformacion tipoFuenteInformacion;
	
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private Integer flMin;
	
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private Integer flMax;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String descripcion;
	
	private String airblockList;
	
	private List<TipoSectorATCDto> tiposSectorATC;
	
	private List<TipoFuenteInformacionDto> tiposFuenteInformacion;
	
	private List<RegionOperativaDto> regionesOperativas;
	
	@Builder.Default	
	private List<AirblockDto> airblocks = new ArrayList<>(); 
	
	@Builder.Default	
	private List<AirblockDto> airblocksNoIncluidos = new ArrayList<>(); 
}
