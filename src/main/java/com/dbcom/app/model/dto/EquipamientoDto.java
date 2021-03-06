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
 * DTO con el que evitamos exponer la entidad en la vista
 * 
 * @author jose.vallve
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = "documentos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class EquipamientoDto implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 174068732641807793L;

	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String marca;

	@Size(max = 50, message = "{validation.min1max50}")
	private String modelo;

	@Size(max = 250, message = "{validation.max250}")
	private String caracteristicas;
	
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;

	private Integer entradas;
	
	private Integer salidas;

	private Integer numeroPuertos;
	
	@NotNull(message = "{validation.notNull}")
	private Double ganancia;

	@NotNull(message = "{validation.notNull}")
	private Double perdida;

	@NotNull(message = "{validation.notNull}")
	private Double apertura;

	@NotNull(message = "{validation.notNull}")
	private Double diametro;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoSistemaLiteDto tipoSistema;
	
	@NotNull(message = "{validation.notNull}")
	@Valid
	private TipoSubsistemaLiteDto tipoSubsistema;
	
	@Builder.Default
	private List<DocumentoLiteDto> documentos = new ArrayList<>();
	
	@Builder.Default
	private List<FotografiaLiteDto> fotografias = new ArrayList<>();
	
}
