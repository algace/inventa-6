package com.dbcom.app.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TipoDocumento;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jose.vallve
 */
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Short> {
	
	/**
	 * Paginación de registros cuyo nombre contenga parte del texto indicado
	 * @param texto Texto contenido
	 * @param pageable Información de la paginación
	 * @return Paginación
	 */
	Page<TipoDocumento> findByNombreContaining(final String texto, final Pageable pageable);
	 
	
	/**
	 * Paginación de registros cuya descripción contenga parte del texto indicado
	 * @param texto Texto contenido
	 * @param pageable Información de la paginación
	 * @return Paginación
	 */
	Page<TipoDocumento> findByDescripcionContaining(final String texto, final Pageable pageable);
	
	/**
	 * Paginación de registros cuyo nombre parte del texto indicado o cuya descripción contenga parte del texto indicado
	 * @param textoNombre Texto contenido en el nombre
	 * @param textoDescripcion Texto contenido en la descripcion
	 * @param pageable Información de la paginación
	 * @return Paginación
	 */
	Page<TipoDocumento> findByNombreContainingOrDescripcionContaining(final String textoNombre, final String textoDescripcion, final Pageable pageable);
	
}
