package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.SectorATCRepository;
import com.dbcom.app.model.dto.AirblockDto;
import com.dbcom.app.model.dto.RegionOperativaDto;
import com.dbcom.app.model.dto.RegionOperativaLiteDto;
import com.dbcom.app.model.dto.SectorATCDto;
import com.dbcom.app.model.dto.TipoFuenteInformacionDto;
import com.dbcom.app.model.dto.TipoFuenteInformacionLiteDto;
import com.dbcom.app.model.entity.Airblock;
import com.dbcom.app.model.entity.RegionOperativa;
import com.dbcom.app.model.entity.SectorATC;
import com.dbcom.app.model.entity.TipoFuenteInformacion;
import com.dbcom.app.model.entity.TipoSectorATC;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class SectorATCServiceImpl implements SectorATCService{

	private final ModelMapperUtils modelMapperUtils;
	private final SectorATCRepository sectorATCRepository;
	private final TipoSectorATCService tipoSectorATCService;
	private final TipoFuenteInformacionService tipoFuenteInformacionService;
	private final AirblockService airblockService;
	private final RegionOperativaService regionOperativaService;
	
	@Autowired
	public SectorATCServiceImpl(ModelMapperUtils modelMapper,
									   SectorATCRepository sectorATCRepository,
									   TipoSectorATCService tipoSectorATCService,
									   TipoFuenteInformacionService tipoFuenteInformacionService,
									   AirblockService airblockService,
									   RegionOperativaService regionOperativaService) {
		this.modelMapperUtils = modelMapper;
		this.sectorATCRepository = sectorATCRepository;
		this.tipoSectorATCService = tipoSectorATCService;
		this.tipoFuenteInformacionService = tipoFuenteInformacionService;
		this.airblockService = airblockService;
		this.regionOperativaService = regionOperativaService;
	}

	@Override
	public SectorATCDto create() {
		log.info(LoggerConstants.LOG_CREATE);
				
		List<RegionOperativaDto> listaRegionesDisponibles = regionOperativaService.getRegionesOperativasConValorPorDefecto();
		List<TipoFuenteInformacionDto> listaTiposFuenteInformacionDisponibles = tipoFuenteInformacionService.getTipoFuenteInformacionConValorPorDefecto();
		
		return  SectorATCDto.builder()
				.tiposSectorATC(tipoSectorATCService.readAll())
				.tipoFuenteInformacion(this.modelMapperUtils.map(listaTiposFuenteInformacionDisponibles.get(0),TipoFuenteInformacionLiteDto.class))
				.tiposFuenteInformacion(listaTiposFuenteInformacionDisponibles)
				.regionOperativa(this.modelMapperUtils.map(listaRegionesDisponibles.get(0), RegionOperativaLiteDto.class))
				.regionesOperativas(listaRegionesDisponibles)
				.airblocksNoIncluidos(this.modelMapperUtils.mapAll2List(airblockService.readAll(), AirblockDto.class))
				.build();
	}

	@Override
	public void delete(Short id) {
		
		final SectorATC sectorATCBBDD = this.sectorATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.sectorATCRepository.delete(sectorATCBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
		
	}

	@Override
	public List<SectorATCDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<SectorATC> sectoresATC = this.sectorATCRepository.findAll();

		final List<SectorATCDto> sectoresATCDto = new ArrayList<>(sectoresATC.size());		
		sectoresATC.forEach(sectorATC -> {
			SectorATCDto sectorATCDto = this.modelMapperUtils.map(sectorATC, SectorATCDto.class);
			String airblockList = "";
			for (int i=0; i < sectorATCDto.getAirblocks().size(); i++) {
				if ((sectorATCDto.getAirblocks().size()-1) == i) {
					airblockList = airblockList + sectorATCDto.getAirblocks().get(i).getNombre();
				}else {
					airblockList = airblockList + sectorATCDto.getAirblocks().get(i).getNombre() + ":";
				}
			}
			sectorATCDto.setAirblockList(airblockList);
			sectoresATCDto.add(sectorATCDto);
		});
		
		return sectoresATCDto;
	}

	@Override
	public SectorATCDto read(Short id) {
		
		log.info(LoggerConstants.LOG_READ);		

		final SectorATC sectorATC = this.sectorATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		SectorATCDto sector = this.modelMapperUtils.map(sectorATC, SectorATCDto.class);
		sector.setTiposSectorATC(tipoSectorATCService.readAll());
		sector.setTiposFuenteInformacion(tipoFuenteInformacionService.readAll());
		sector.setRegionesOperativas(regionOperativaService.readAll());
		sector.setAirblocksNoIncluidos(this.modelMapperUtils.mapAll2List(this.airblockService.readNotContains(id), AirblockDto.class));

		return sector; 
	}

	@Override
	public SectorATCDto save(SectorATCDto sectorATCDto) {
		
		sectorATCDto.setAirblocks(filterListAirblock(sectorATCDto.getAirblocks()));
		
		SectorATC sectorATC = this.modelMapperUtils.map(sectorATCDto, SectorATC.class);
	    
		sectorATC = this.sectorATCRepository.save(sectorATC);	
		
		log.info(LoggerConstants.LOG_CREATE, sectorATC.getNombre());		
		
		return this.modelMapperUtils.map(sectorATC, SectorATCDto.class);
	}

	@Override
	public SectorATCDto update(SectorATCDto sectorATCDto) {
		
		final SectorATC sectorATC = this.modelMapperUtils.map(sectorATCDto, SectorATC.class);
		
		SectorATC sectorATCBBDD = this.sectorATCRepository.findById(sectorATC.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		
		// Actualizamos el registro de bbdd
		sectorATCBBDD.setNombre(sectorATCDto.getNombre());
		sectorATCBBDD.setRegionOperativa(this.modelMapperUtils.map(sectorATCDto.getRegionOperativa(), RegionOperativa.class));
		sectorATCBBDD.setTipoFuenteInformacion(this.modelMapperUtils.map(sectorATCDto.getTipoFuenteInformacion(), TipoFuenteInformacion.class));
		sectorATCBBDD.setFechaPublicacion(sectorATCDto.getFechaPublicacion());
		sectorATCBBDD.setTipoSectorATC(this.modelMapperUtils.map(sectorATCDto.getTipoSectorATC(), TipoSectorATC.class));
		sectorATCBBDD.setFlMax(sectorATCDto.getFlMax());
		sectorATCBBDD.setFlMin(sectorATCDto.getFlMin());
		sectorATCBBDD.setDescripcion(sectorATCDto.getDescripcion());

		//se limpia la lista del objeto airblock vacío que viene por defecto
		sectorATCDto.setAirblocks(filterListAirblock(sectorATCDto.getAirblocks()));
		sectorATCBBDD.setAirblocks(this.modelMapperUtils.mapAll2Set(sectorATCDto.getAirblocks(), Airblock.class));
		
		sectorATCBBDD = this.sectorATCRepository.save(sectorATCBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, sectorATCBBDD.getId());
		
		return this.modelMapperUtils.map(sectorATCBBDD, SectorATCDto.class);
	}
	
	/**
	 * Procesa una lista de objetos Airblock proveniente del front y elimina de esta lista los 
	 * objetos que tienen un id null
     * @param lista de objetos AirblockDto provenientes del front 
     * @return lista de objetos AirblockDto filtrada 
	 */
	
	
	//ponerlo en el save y en el update
	private List<AirblockDto> filterListAirblock(List<AirblockDto> listAirblocks) {
	
		return listAirblocks.stream()
                            .filter(version -> !Objects.isNull(version.getId()))
                            .collect(Collectors.toList());
	}
	
}
