package com.dbcom.app.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dbcom.app.model.entity.TipoChasis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "tiposChasis")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChasisPasarelaDto {
	
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@NotNull(message = "{validation.notNull}")
	private TipoChasis tipoChasis;
	
	@Max(999)
	private Integer indiceCargaLimite;
	
	@Builder.Default
	private List<TipoChasisDto> tiposChasis = new ArrayList();

}
