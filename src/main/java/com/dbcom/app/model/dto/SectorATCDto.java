package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	//FALTA REGION OPERATIVA!!!!!!!!!
	@NotNull(message = "{validation.notNull}")
	//private TipoSectorATCDto tipoSectorATC;
	private TipoSectorATC tipoSectorATC;
	
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPublicacion;
	
	@NotNull(message = "{validation.notNull}")
	//private TipoFuenteInformacionDto tipoFuenteInformacion;
	private TipoFuenteInformacion tipoFuenteInformacion;
	
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String flMin;
	
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String flMax;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String descripcion;
	
	private List<TipoSectorATCDto> tiposSectorATC;
	
	private List<TipoFuenteInformacionDto> tiposFuenteInformacion;
	
	@Builder.Default	
	private List<AirblockDto> airblock = new ArrayList<>(); 
	
	@Builder.Default	
	private List<AirblockDto> airblockNoIncluidos = new ArrayList<>(); 
}
