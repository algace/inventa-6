package com.dbcom.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbcom.app.model.entity.Airblock;
import com.dbcom.app.model.entity.RegionOperativa;
import com.dbcom.app.model.entity.SectorATC;
import com.dbcom.app.model.entity.TipoFuenteInformacion;
import com.dbcom.app.model.entity.TipoSectorATC;

@Repository
public interface SectorATCRepository extends JpaRepository<SectorATC, Short>{
	
	public Long countByTipoSectorATC(TipoSectorATC tipoSectorATC);
	
	public Long countByTipoFuenteInformacion(TipoFuenteInformacion tipoFuenteInformacion);
	
	public Long countByRegionOperativa(RegionOperativa regionOperativa);
	
	public Long countByAirblocks(Airblock airblock);

}
