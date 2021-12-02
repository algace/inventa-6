package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO con el que evitamos exponer la entidad en la vista
 * 
 * @author jose.vallve
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = "equipamientos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class AplicacionSWDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4263045492135704248L;

	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	 
	@Size(min = 1, max = 250, message = "{validation.min1max250}")
	private String archivo;
	
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	 
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hora;
	
	@Builder.Default
	private List<VersionSWDto> versionesSW = new ArrayList<>();
	
	@Builder.Default	
	private List<EquipamientoDto> equipamientos = new ArrayList<>();
	
	/**
	 * Versiones que no posee la aplicación
	 */
	@Builder.Default	
	private List<VersionSWDto> versionesSWNoIncluidas = new ArrayList<>();
	
	@Builder.Default	
	private List<VersionSWDto> equipamientosNoIncluidos = new ArrayList<>();

}
