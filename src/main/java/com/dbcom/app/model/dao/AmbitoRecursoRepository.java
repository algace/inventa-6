package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.AmbitoRecurso;
import com.dbcom.app.model.entity.FuncionPasarela;

@Repository
public interface AmbitoRecursoRepository extends JpaRepository<AmbitoRecurso, Long>{
	
	public Long countByFuncionPasarela(FuncionPasarela funcionPasarela);

}
