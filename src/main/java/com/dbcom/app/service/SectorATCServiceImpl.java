package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.SectorATCRepository;
import com.dbcom.app.model.dto.SectorATCDto;
import com.dbcom.app.model.dto.SectorATCDto;
import com.dbcom.app.model.entity.SectorATC;
import com.dbcom.app.model.entity.SectorATC;
import com.dbcom.app.model.entity.TipoChasis;
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
	
	@Autowired
	public SectorATCServiceImpl(ModelMapperUtils modelMapper,
									   SectorATCRepository sectorATCRepository,
									   TipoSectorATCService tipoSectorATCService,
									   TipoFuenteInformacionService tipoFuenteInformacionService) {
		this.modelMapperUtils = modelMapper;
		this.sectorATCRepository = sectorATCRepository;
		this.tipoSectorATCService = tipoSectorATCService;
		this.tipoFuenteInformacionService = tipoFuenteInformacionService;
	}

	@Override
	public SectorATCDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		return  SectorATCDto.builder()
				.tiposSectorATC(tipoSectorATCService.readAll())
				.tiposFuenteInformacion(tipoFuenteInformacionService.readAll())
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
		sectoresATC.forEach(sectorATC -> sectoresATCDto.add(this.modelMapperUtils.map(sectorATC, SectorATCDto.class)));
		
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

		return sector; 
	}

	@Override
	public SectorATCDto save(SectorATCDto sectorATCDto) {
		
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
		sectorATCBBDD.setTipoFuenteInformacion(this.modelMapperUtils.map(sectorATCDto.getTipoFuenteInformacion(), TipoFuenteInformacion.class));
		sectorATCBBDD.setFechaPublicacion(sectorATCDto.getFechaPublicacion());
		sectorATCBBDD.setTipoSectorATC(this.modelMapperUtils.map(sectorATCDto.getTipoSectorATC(), TipoSectorATC.class));
		sectorATCBBDD.setFlMax(sectorATCDto.getFlMax());
		sectorATCBBDD.setFlMin(sectorATCDto.getFlMin());
		sectorATCBBDD.setDescripcion(sectorATCDto.getDescripcion());
		
		sectorATCBBDD = this.sectorATCRepository.save(sectorATCBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, sectorATCBBDD.getId());
		
		return this.modelMapperUtils.map(sectorATCBBDD, SectorATCDto.class);
	}
	
}
