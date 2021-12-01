package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

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
// Evitamos referencias circulares
@EqualsAndHashCode(exclude = "documentos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class TipoDocumentoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7024192183679217781L;

	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;
	
	@Builder.Default
	private List<DocumentoDto> documentos = new ArrayList<>();
	
}
