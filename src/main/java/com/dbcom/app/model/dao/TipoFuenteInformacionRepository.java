package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TipoFuenteInformacion;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jose.vallve
 */
@Repository
public interface TipoFuenteInformacionRepository extends JpaRepository<TipoFuenteInformacion, Short> {

	public TipoFuenteInformacion findByNombre(String nombre);
}
