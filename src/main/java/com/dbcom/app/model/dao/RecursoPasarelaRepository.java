package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.RecursoPasarela;

@Repository
public interface RecursoPasarelaRepository extends JpaRepository<RecursoPasarela, Short>{

}
