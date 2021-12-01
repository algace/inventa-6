package com.dbcom.app.model.dao;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dbcom.app.model.entity.AplicacionSW;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Pruebas del modelo de Aplicación")
class AplicacionSWRepositoryTest {

	@Autowired
    private AplicacionSWRepository aplicacionSWRepository;
	
	@Test
	@DisplayName("Persistencia, se espera ok")
	void persistence_ok() {

		final AplicacionSW aplicacionSW = AplicacionSW.builder()
				.id(1L)
				.nombre("App1")
				.archivo("Archivo1")
				.fecha(LocalDate.now())
				.hora(LocalTime.now())
				.build();
		
		Assertions.assertEquals(aplicacionSWRepository.save(aplicacionSW).getId(), aplicacionSW.getId());
	}
	
}
