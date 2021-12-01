package com.dbcom.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dbcom.app.model.dto.TipoDocumentoDto;

/**
 * Lógica para tipos de documento
 * 
 * @author jose.vallve
 */
public interface TipoDocumentoService extends GenericService<TipoDocumentoDto, Short> {

	/**
	 * Creamos un tipo de documento sin persistencia
	 * @return Nuevo tipo de documento
	 */
	TipoDocumentoDto create();

	/**
	 * Borrado del tipo de documento con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de documento
	 * @return Listado
	 */
	List<TipoDocumentoDto> readAll();
	
	/**
	 * Obtenemos el tipo de documento con el id facilitado
	 * @param id Identificador
	 * @return Tipo de documento
	 */
	TipoDocumentoDto read(final Short id);
	
	/**
	 * Persistimos el tipo de documento pasado como parámetro
	 * @param tipoDocumentoDto Tipo de documento a persistir
	 * @return Tipo de documento persistido
	 */
	TipoDocumentoDto save(final TipoDocumentoDto tipoDocumentoDto);
		
	/**
	 * Actualizamos el tipo de documento pasado como parámetro
	 * @param tipoDocumentoDto Tipo de documento a actualizar
	 * @return Tipo de documento actualizado
	 */
	TipoDocumentoDto update(final TipoDocumentoDto tipoDocumentoDto);
	
	/**
	 * Paginación de los tipos de documento
	 * @param pageable Información de la paginación
	 * @return Paginación
	 */
	Page<TipoDocumentoDto> readAll(final Pageable pageable);
	
	/**
	 * Paginación de registros cuyo nombre contenga parte del texto indicado
	 * @param texto Texto contenido
	 * @param pageable Información de la paginación
	 * @return Paginación
	 */
	Page<TipoDocumentoDto> readContainingNombre(final String texto, final Pageable pageable);
	
	/**
	 * Paginación de registros cuya descripción contenga parte del texto indicado
	 * @param texto Texto contenido
	 * @param pageable Información de la paginación
	 * @return Paginación
	 */
	Page<TipoDocumentoDto> readContainingDescripcion(final String texto, final Pageable pageable);
	
	/**
	 * Paginación de registros cuyos campos contengan parte del texto indicado
	 * @param texto Texto contenido
	 * @param pageable Información de la paginación
	 * @return Paginación
	 */
	Page<TipoDocumentoDto> readContainings(final String texto, final Pageable pageable);
}
