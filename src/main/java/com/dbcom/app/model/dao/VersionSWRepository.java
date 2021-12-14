package com.dbcom.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.AplicacionSW;
import com.dbcom.app.model.entity.VersionSW;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jose.vallve
 */
@Repository
public interface VersionSWRepository extends JpaRepository<VersionSW, Long> {
	
	@Query("select a from AplicacionSW a join a.versionesSW v where v.id = :id")
	public List<AplicacionSW> findAplicacionesWithVersion(@Param("id") long id);
	
}
