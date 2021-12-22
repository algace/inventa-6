package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.RegionOperativa;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jgm
 */
@Repository
public interface RegionOperativaRepository extends JpaRepository<RegionOperativa, Long> {
	
	public RegionOperativa findByNombre(String nombre);

}
