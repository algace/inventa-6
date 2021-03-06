package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TipoSistema;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author neoris
 */
@Repository
public interface TipoSistemaRepository extends JpaRepository<TipoSistema, Long> {

}
