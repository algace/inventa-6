package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TipoCanalizacion;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jose.vallve
 */
@Repository
public interface TipoCanalizacionRepository extends JpaRepository<TipoCanalizacion, Short> {
	
}
