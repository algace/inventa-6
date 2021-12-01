package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AirblockRepository;
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dto.AirblockDto;
import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.entity.Airblock;
import com.dbcom.app.model.entity.Documento;
import com.dbcom.app.model.entity.Equipamiento;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * 
 * Lógica para airblocks
 */
@Service
@Slf4j
public final class AirblockServiceImpl implements AirblockService {
	
	private AirblockRepository airblockRepository;
	private final ModelMapperUtils  modelMapperUtils;

	@Autowired
	public AirblockServiceImpl(ModelMapperUtils modelMapper,
			AirblockRepository airblockRepository) {
		this.modelMapperUtils = modelMapper;
		this.airblockRepository = airblockRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AirblockDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new AirblockDto();
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
		final Airblock airblockBBDD = this.airblockRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.airblockRepository.delete(airblockBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<AirblockDto> readAll() {
		
		final List<Airblock> airblocks = this.airblockRepository.findAll();
		
		final List<AirblockDto> airblocksDto = new ArrayList<>(airblocks.size());		
		airblocks.forEach(airblock -> airblocksDto.add(this.modelMapperUtils.map(airblock, AirblockDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return airblocksDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AirblockDto read(final Long id) {		
		
		final Airblock airblock = this.airblockRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final AirblockDto result = this.modelMapperUtils.map(airblock, AirblockDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AirblockDto save(final AirblockDto airblocksDto) {		
		
		Airblock airblock = this.modelMapperUtils.map(airblocksDto, Airblock.class);
	    
		airblock = this.airblockRepository.save(airblock);	
		
		log.info(LoggerConstants.LOG_CREATE, airblock.getNombre());		
		
		return this.modelMapperUtils.map(airblock, AirblockDto.class);
	}


	/**
	 * {@inheritDoc}
	 */
	public AirblockDto update(final AirblockDto airblockDto) {		
		
		Airblock airblockBBDD = this.airblockRepository.findById(airblockDto.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		airblockBBDD.setNombre(airblockDto.getNombre());
		airblockBBDD.setFlMin(airblockDto.getFlMin());
		airblockBBDD.setFlMax(airblockDto.getFlMax());		
		airblockBBDD.setCoordenadas(airblockDto.getCoordenadas());
		airblockBBDD.setSectoresATC(airblockDto.getSectoresATC());
		airblockBBDD = this.airblockRepository.save(airblockBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, airblockBBDD.getId());
		
		return this.modelMapperUtils.map(airblockBBDD, AirblockDto.class);
	}
}
 