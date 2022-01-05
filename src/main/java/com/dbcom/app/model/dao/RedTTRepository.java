package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.RedTT;
import com.dbcom.app.model.entity.TipoTopologia;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author neoris
 */
@Repository
public interface RedTTRepository extends JpaRepository<RedTT, Long> {

	public Long countByTipoTopologia(TipoTopologia tipoTopologia);
}
