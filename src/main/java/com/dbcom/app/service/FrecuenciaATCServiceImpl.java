package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.FrecuenciaATCRepository;
import com.dbcom.app.model.dto.FrecuenciaATCDto;
import com.dbcom.app.model.entity.FrecuenciaATC;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * 
 * Lógica para frecuenciasATC
 */
@Service
@Slf4j
public final class FrecuenciaATCServiceImpl implements FrecuenciaATCService {
	
	private FrecuenciaATCRepository frecuenciaATCRepository;
	private final ModelMapperUtils  modelMapperUtils;

	@Autowired
	public FrecuenciaATCServiceImpl(ModelMapperUtils modelMapper,
			FrecuenciaATCRepository frecuenciaATCRepository) {
		this.modelMapperUtils = modelMapper;
		this.frecuenciaATCRepository = frecuenciaATCRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FrecuenciaATCDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new FrecuenciaATCDto();
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
		final FrecuenciaATC frecuenciaATCBBDD = this.frecuenciaATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.frecuenciaATCRepository.delete(frecuenciaATCBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<FrecuenciaATCDto> readAll() {
		
		final List<FrecuenciaATC> frecuenciasATC = this.frecuenciaATCRepository.findAll();
		
		final List<FrecuenciaATCDto> frecuenciasATCDto = new ArrayList<>(frecuenciasATC.size());		
		frecuenciasATC.forEach(frecuenciaATC -> frecuenciasATCDto.add(this.modelMapperUtils.map(frecuenciaATC, FrecuenciaATCDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return frecuenciasATCDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FrecuenciaATCDto read(final Long id) {		
		
		final FrecuenciaATC frecuenciaATC = this.frecuenciaATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final FrecuenciaATCDto result = this.modelMapperUtils.map(frecuenciaATC, FrecuenciaATCDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FrecuenciaATCDto save(final FrecuenciaATCDto frecuenciaATCDto) {		
		
		FrecuenciaATC frecuenciaATC = this.modelMapperUtils.map(frecuenciaATCDto, FrecuenciaATC.class);
	    
		frecuenciaATC = this.frecuenciaATCRepository.save(frecuenciaATC);	
		
		log.info(LoggerConstants.LOG_CREATE, frecuenciaATC.getNombre());		
		
		return this.modelMapperUtils.map(frecuenciaATC, FrecuenciaATCDto.class);
	}


	/**
	 * {@inheritDoc}
	 */
	public FrecuenciaATCDto update(final FrecuenciaATCDto frecuenciaATCDto) {		
		
		FrecuenciaATC frecuenciaATCBBDD = this.frecuenciaATCRepository.findById(frecuenciaATCDto.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		frecuenciaATCBBDD.setNombre(frecuenciaATCDto.getNombre());
		frecuenciaATCBBDD.setDependencia(frecuenciaATCDto.getDependencia());
		frecuenciaATCBBDD.setDescripcion(frecuenciaATCDto.getDescripcion());		
		frecuenciaATCBBDD.setTipoServicio(frecuenciaATCDto.getTipoServicio());
		frecuenciaATCBBDD.setFrecuenciaBackup(frecuenciaATCDto.getFrecuenciaBackup());
		frecuenciaATCBBDD.setTitular(frecuenciaATCDto.getTitular());
		frecuenciaATCBBDD.setDocOACI(frecuenciaATCDto.getDocOACI());
		frecuenciaATCBBDD.setObservaciones(frecuenciaATCDto.getObservaciones());
		frecuenciaATCBBDD = this.frecuenciaATCRepository.save(frecuenciaATCBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, frecuenciaATCBBDD.getId());
		
		return this.modelMapperUtils.map(frecuenciaATCBBDD, FrecuenciaATCDto.class);
	}
}
 