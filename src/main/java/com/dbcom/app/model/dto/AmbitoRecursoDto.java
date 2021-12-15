package com.dbcom.app.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.dbcom.app.model.entity.FuncionPasarela;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "funcionPasarelas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmbitoRecursoDto {
	
private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	private FuncionPasarela funcionPasarela;
	
	@Builder.Default
	private List<FuncionPasarelaDto> funcionPasarelas = new ArrayList();;

}
