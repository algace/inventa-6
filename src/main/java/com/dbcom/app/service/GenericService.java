package com.dbcom.app.service;

import java.util.List;

/**
 * Clase genérica para los servicios
 * 
 * @author jose.vallve
 * @param <T> Tipo de DTO
 * @param <ID> Tipo de identificador 
 */
public interface GenericService<T, ID> {
	
	/**
	 * Creamos un objeto DTO sin persistencia
	 * @return Nuevo objeto DTO
	 */
	T create();

	/**
	 * Borrado del objeto persistido con el id facilitado
	 * @param id Identificador 
	 */
	void delete(final ID id);

	/**
 	 * Listado de DTOs
	 * @return Listado
	 */
	List<T> readAll();
	
	/**
	 * Objeto DTO con el id facilitado
	 * @param id Identificador
	 * @return DTO
	 */
	T read(final ID id);
	
	/**
	 * Persistencia del objeto facilitado
	 * @param t DTO
	 * @return DTO
	 */
	T save(final T t);
		
	/**
	 * Actualización del objeto facilitado
	 * @param t DTO
	 * @return DTO
	 */
	T update(final T t);
}
