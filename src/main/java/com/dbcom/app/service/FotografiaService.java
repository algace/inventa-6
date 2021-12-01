package com.dbcom.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dbcom.app.model.dto.FotografiaDto;

/**
 * Lógica para fotografia
 * 
 */
public interface FotografiaService extends GenericService<FotografiaDto, Long> {

	/**
	 * Creamos una fotografia sin persistencia
	 * @return Nueva fotografia
	 */
	FotografiaDto create();

	/**
	 * Borrado de la fotografia con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de fotografias
	 * @return Listado
	 */
	List<FotografiaDto> readAll();
	
	/**
	 * Obtenemos la fotografia con el id facilitado
	 * @param id Identificador
	 * @return Fotografia
	 */
	FotografiaDto read(final Long id);
	
	/**
	 * Persistimos la fotografia pasada como parámetro
	 * @param fotografiaDto Fotografia a persistir
	 * @return Fotografia persistido
	 */
	FotografiaDto save(final FotografiaDto fotografiaDto);
		
	/**
	 * Actualizamos la fotografia pasada como parámetro
	 * @param fotografiaDto Fotografia a actualizar
	 * @return Fotografia actualizado
	 */
	FotografiaDto update(final FotografiaDto fotografiaDto);
	
	public void upload(final Long idEquipamiento, final String descripcionFotografia, 
			           final MultipartFile fichero);
}
