package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.FuncionPasarela;
import com.dbcom.app.model.entity.RecursoPasarela;
import com.dbcom.app.model.entity.TipoChasis;

@Repository
public interface RecursoPasarelaRepository extends JpaRepository<RecursoPasarela, Short>{
	
	public Long countByTipoChasis(TipoChasis tipoChasis);
	
	public Long countByFuncionPasarela(FuncionPasarela funcionPasarela);
}
