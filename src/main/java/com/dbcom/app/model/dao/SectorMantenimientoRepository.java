package com.dbcom.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.RegionMantenimiento;
import com.dbcom.app.model.entity.SectorMantenimiento;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author neoris
 */
@Repository
public interface SectorMantenimientoRepository extends JpaRepository<SectorMantenimiento, Long> {
	
	List<SectorMantenimiento> findByRegionMantenimiento(RegionMantenimiento regionMantenimiento);

}
