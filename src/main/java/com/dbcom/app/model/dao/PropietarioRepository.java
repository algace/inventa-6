package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.Propietario;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Short>{

	public Propietario findByPropietario(String propietario);
}
