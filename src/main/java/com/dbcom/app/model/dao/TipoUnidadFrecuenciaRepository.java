package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TipoUnidadFrecuencia;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author neoris
 */
@Repository
public interface TipoUnidadFrecuenciaRepository extends JpaRepository<TipoUnidadFrecuencia, Short> {

	public TipoUnidadFrecuencia findByNombre(String nombre);
}
