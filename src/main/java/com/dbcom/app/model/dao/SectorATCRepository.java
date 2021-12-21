package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.SectorATC;

@Repository
public interface SectorATCRepository extends JpaRepository<SectorATC, Short>{

}
