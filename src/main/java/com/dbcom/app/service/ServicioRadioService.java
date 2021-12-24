package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.ServicioRadioDto;

public interface ServicioRadioService extends GenericService<ServicioRadioDto, Short> {
	
	
	/**
	 * Creamos un servicio de radio sin persistencia
	 * @return Nuevo servicio de radio
	 */
	ServicioRadioDto create();

	/**
	 * Borrado del servicio de radio con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los servicios de radio
	 * @return Listado
	 */
	List<ServicioRadioDto> readAll();
	
	/**
	 * Obtenemos el servicio de radio con el id facilitado
	 * @param id Identificador
	 * @return Servicio de radio
	 */
	ServicioRadioDto read(final Short id);
	
	/**
	 * Persistimos el servicio de radio pasado como parámetro
	 * @param servicioRadioDto Servicio de radio a persistir
	 * @return Servicio de radio persistido
	 */
	ServicioRadioDto save(final ServicioRadioDto servicioRadioDto);
		
	/**
	 * Actualizamos el servicio de radio pasado como parámetro
	 * @param servicioRadioDto Servicio de radio a actualizar
	 * @return Servicio de radio actualizado
	 */
	ServicioRadioDto update(final ServicioRadioDto servicioRadioDto);
}
