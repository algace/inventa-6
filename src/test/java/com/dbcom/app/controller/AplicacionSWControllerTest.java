package com.dbcom.app.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.dbcom.app.constants.ControllerConstants;
import com.dbcom.app.model.dto.AplicacionSWDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Pruebas del controlador de Aplicación")
class AplicacionSWControllerTest {
	
	@MockBean
	private BindingResult bindingResult;

	@MockBean
	private Model model;

	@Autowired
    private AplicacionSWController aplicacionSWController;
	
	@Test
	@DisplayName("Persistencia, se espera ok")
	void persistence_ok() {

		final AplicacionSWDto aplicacionSWDto = AplicacionSWDto.builder()
				.id(1L)
				.nombre("App1")
				.archivo("Archivo1")
				.fecha(LocalDate.now())
				.hora(LocalTime.now())
				.build();
		
		Assertions.assertEquals(aplicacionSWController.save(aplicacionSWDto, bindingResult, model), 
				ControllerConstants.REDIRECT.concat(AplicacionSWController.MAP_READALL_APLICACIONES));
	}
	
}
