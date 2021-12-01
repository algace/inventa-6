package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.FrecuenciaATC;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author eduardo.tubilleja
 */
@Repository
public interface FrecuenciaATCRepository extends JpaRepository<FrecuenciaATC, Long> {
	
}
