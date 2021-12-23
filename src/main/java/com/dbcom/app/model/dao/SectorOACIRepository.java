package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.SectorOACI;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author neoris
 */
@Repository
public interface SectorOACIRepository extends JpaRepository<SectorOACI, Long> {

}
