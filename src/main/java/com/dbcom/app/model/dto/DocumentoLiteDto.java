package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoLiteDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 341727455835606248L;

	private Long id;
	
	@Size(min = 1, max = 250, message = "{validation.min1max250}")
	private String nombre;
	 
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;
	
	@NotEmpty
	private String tipo;
	
	@Builder.Default
	private TipoDocumentoDto tipoDocumento = new TipoDocumentoDto();

}
