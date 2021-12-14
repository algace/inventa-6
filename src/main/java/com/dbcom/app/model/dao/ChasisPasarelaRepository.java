package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.ChasisPasarela;

@Repository
public interface ChasisPasarelaRepository extends JpaRepository<ChasisPasarela, Short>{

}
