package com.dbcom.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TipoSistema;
import com.dbcom.app.model.entity.TipoSubsistema;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author jgm
 */
@Repository
public interface TiposSubsistemasRepository extends JpaRepository<TipoSubsistema, Long> {
	
	List<TipoSubsistema> findByTipoSistema(TipoSistema tipoSistema);

}
