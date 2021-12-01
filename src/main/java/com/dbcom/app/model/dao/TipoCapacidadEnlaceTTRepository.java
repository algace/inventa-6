package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TipoCapacidadEnlaceTT;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jose.vallve
 */
@Repository
public interface TipoCapacidadEnlaceTTRepository extends JpaRepository<TipoCapacidadEnlaceTT, Short> {
	
}
