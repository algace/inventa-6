package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.Frecuencia;
import com.dbcom.app.model.entity.TipoBandaFrecuencia;
import com.dbcom.app.model.entity.TipoFuenteInformacion;
import com.dbcom.app.model.entity.TipoUnidadFrecuencia;


/**
 * Operaciones de persistencia contra la bbdd
 * 
 * @author eduardo.tubilleja
 */
@Repository
public interface FrecuenciaRepository extends JpaRepository<Frecuencia, Long> {
	
	public Long countByTipoUnidadFrecuencia(TipoUnidadFrecuencia tipoUnidadFrecuencia);
	
	public Long countByTipoBandaFrecuencia(TipoBandaFrecuencia tipoBandaFrecuencia);
	
	public Long countByTipoFuenteInformacion(TipoFuenteInformacion tipoFuenteInformacion);
}
