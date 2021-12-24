package com.dbcom.app.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSectorATCLiteDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{validation.notNull}")
	private Short id;
	
	private String nombre;

}
