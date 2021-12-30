package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.TarjetaPasarela;

@Repository
public interface TarjetaPasarelaRepository extends JpaRepository<TarjetaPasarela, Short>{

}
