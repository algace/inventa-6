package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.VersionSW;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jose.vallve
 */
@Repository
public interface VersionSWRepository extends JpaRepository<VersionSW, Long> {
	
}
