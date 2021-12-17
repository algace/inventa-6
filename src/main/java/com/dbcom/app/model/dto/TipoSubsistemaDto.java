package com.dbcom.app.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Dto para Subsistemas
 * 
 * @author jgm
 */
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = {"tipoSistema", "tiposSistemasDisponibles"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSubsistemaDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9003153481472335966L;
	
    private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	private String interfazOperacion;
	
	@NotNull(message = "{validation.notNull}")
	private TipoSistemaDto tipoSistema;
	
	@Builder.Default
	private List<TipoSistemaDto> tiposSistemasDisponibles = new ArrayList<>();

}
