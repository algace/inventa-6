package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.Fotografia;

/**
 * Operaciones de persistencia contra la bbdd
 * 
 */
@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {

}
