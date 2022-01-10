package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dao.VersionSWRepository;
import com.dbcom.app.model.dto.AplicacionSWLiteDto;
import com.dbcom.app.model.dto.VersionSWDto;
import com.dbcom.app.model.dto.VersionSWLiteDto;
import com.dbcom.app.model.entity.AplicacionSW;
import com.dbcom.app.model.entity.VersionSW;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para versiones
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class VersionSWServiceImpl implements VersionSWService {
	
	private final AplicacionSWRepository aplicacionSWRepository;
	private final VersionSWRepository versionSWRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public VersionSWServiceImpl(AplicacionSWRepository aplicacionSWRepository,
			VersionSWRepository versionSWRepository, ModelMapperUtils modelMapper) {
		this.aplicacionSWRepository = aplicacionSWRepository;
		this.versionSWRepository = versionSWRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public VersionSWDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		return new VersionSWDto();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long id) {			
		
		final VersionSW versionSWBBDD = this.versionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.versionSWRepository.delete(versionSWBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VersionSWDto> readAll() {
		
		final List<VersionSW> versionesSW = this.versionSWRepository.findAll();
		
		final List<VersionSWDto> versionesSWDto = new ArrayList<>(versionesSW.size());		
		versionesSW.forEach(versionSW -> versionesSWDto.add(this.modelMapperUtils.map(versionSW, VersionSWDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return versionesSWDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VersionSWLiteDto> readAllLite() {
		
		final List<VersionSW> versionesSW = this.versionSWRepository.findAll();
		
		final List<VersionSWLiteDto> versionesSWDto = new ArrayList<>(versionesSW.size());		
		versionesSW.forEach(versionSW -> versionesSWDto.add(this.modelMapperUtils.map(versionSW, VersionSWLiteDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return versionesSWDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public VersionSWDto read(final Long id) {	
		
		final VersionSW versionSW = this.versionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final VersionSWDto result = this.modelMapperUtils.map(versionSW, VersionSWDto.class);
		
		// Insertamos las aplicaciones que tienen asociada esta versión
		final List<AplicacionSW> aplicacionesSWAsociadas = this.versionSWRepository.findAplicacionesWithVersion(id);
		result.setAplicacionesSW(this.modelMapperUtils.mapAll2List(aplicacionesSWAsociadas, AplicacionSWLiteDto.class));
		
		return result;

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VersionSWLiteDto> readNotContains(final Long id) {	
		
		final AplicacionSW aplicacionSW = this.aplicacionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);		

		final List<VersionSW> versionesSW = this.versionSWRepository.findAll();
		versionesSW.removeAll(aplicacionSW.getVersionesSW());
		
		return this.modelMapperUtils.mapAll2List(versionesSW, VersionSWLiteDto.class);

	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public VersionSWDto saveUpdate(final VersionSWDto versionSWDto) {		
		
		VersionSW versionSW = this.modelMapperUtils.map(versionSWDto, VersionSW.class);	
		
		return this.modelMapperUtils.map(this.versionSWRepository.save(versionSW), VersionSWDto.class);
	}
}
