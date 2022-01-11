package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorATCLiteDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "{validation.notNull}")
	@Min(value = 1, message = "{validation.notNull}")
	private Short id;
	
	private String nombre;
	
	private TipoSectorATCLiteDto tipoSectorATC;
	
	private LocalDate fechaPublicacion;
	
	private TipoFuenteInformacionLiteDto tipoFuenteInformacion;
	
	private Integer flMin;
	
	private Integer flMax;
	
	private String descripcion;

}
