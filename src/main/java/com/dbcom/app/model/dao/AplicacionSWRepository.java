package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.AplicacionSW;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jose.vallve
 */
@Repository
public interface AplicacionSWRepository extends JpaRepository<AplicacionSW, Long> {
	
	@Query("select count(a) from AplicacionSW a join a.equipamientos e where e.id = :id")
	public Long countAplicacionesWithEquipamiento(@Param("id") long id);
}
