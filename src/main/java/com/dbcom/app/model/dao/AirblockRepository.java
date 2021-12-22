package com.dbcom.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.Airblock;
import com.dbcom.app.model.entity.SectorATC;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author eduardo.tubilleja
 */
@Repository
public interface AirblockRepository extends JpaRepository<Airblock, Long> {
	
	@Query("select a from SectorATC a join a.airblocks v where v.id = :id")
	public List<SectorATC> findSectoresATCWithAirbloks(@Param("id") long id);
	
}
