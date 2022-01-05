package com.dbcom.app.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dbcom.app.model.dto.AplicacionSWDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Pruebas de la lógica de Aplicación")
class AplicacionSWServiceImplTest {

	@Autowired
    private AplicacionSWService aplicacionSWService;
	
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
		
		Assertions.assertEquals(aplicacionSWService.saveUpdate(aplicacionSWDto).getId(), aplicacionSWDto.getId());
	}
	
}
