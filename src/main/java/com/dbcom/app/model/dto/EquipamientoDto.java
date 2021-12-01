package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

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

	@Max(999)
	private Short entradas;
	
	@Max(999)
	private Short salidas;

	@Max(999)
	private Short numeroPuertos;
	
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double ganancia;

	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double perdida;

	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double apertura;

	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double diametro;
	
	@NotEmpty(message = "{validation.notNull}")
	private String sistema;
	
	@NotEmpty(message = "{validation.notNull}")
	private String subsistema;
	
	@Builder.Default
	private List<AplicacionSWDto> aplicaciones = new ArrayList<>();
	
	@Builder.Default
	private List<DocumentoDto> documentos = new ArrayList<>();
	
	@Builder.Default
	private List<TipoDocumentoDto> tiposDocumento = new ArrayList<>();
	
	@Builder.Default
	private List<FotografiaDto> fotografias = new ArrayList<>();
	
	// Atributos de los documentos
	private Long idTipoDocumento;
	private String descripcionDocumento;
	
}
