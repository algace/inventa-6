package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AplicacionSWLiteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9015189174725412684L;

	/**
	 * 
	 */
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
}
