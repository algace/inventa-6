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
	public VersionSWDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		final List<AplicacionSW> aplicacionesSW = this.aplicacionSWRepository.findAll();
		final List<AplicacionSWLiteDto> aplicacionesSWLiteDto = new ArrayList<>(aplicacionesSW.size());		
		aplicacionesSW.forEach(aplicacionSW -> aplicacionesSWLiteDto.add(this.modelMapperUtils.map(aplicacionSW, AplicacionSWLiteDto.class)));
		return VersionSWDto.builder()
	              .aplicacionesSWNoIncluidas(aplicacionesSWLiteDto)
	              .build();
		}

	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
		final VersionSW versionSWBBDD = this.versionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.versionSWRepository.delete(versionSWBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
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
	public VersionSWDto read(final Long id) {	
		
		final VersionSW versionSW = this.versionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);		

		return this.modelMapperUtils.map(versionSW, VersionSWDto.class); 

	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<VersionSWDto> readNotContains(final Long id) {	
		
		final AplicacionSW aplicacionSW = this.aplicacionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);		

		final List<VersionSW> versionesSW = this.versionSWRepository.findAll();
		versionesSW.removeAll(aplicacionSW.getVersionesSW());
		
		return this.modelMapperUtils.mapAll2List(versionesSW, VersionSWDto.class);

	}
		
	/**
	 * {@inheritDoc}
	 */
	public VersionSWDto save(final VersionSWDto versionSWDto) {		
		
		VersionSW versionSW = this.modelMapperUtils.map(versionSWDto, VersionSW.class);
	    
		versionSW = this.versionSWRepository.save(versionSW);	
		
		log.info(LoggerConstants.LOG_CREATE, versionSW.getNombre());		
		
		return this.modelMapperUtils.map(versionSW, VersionSWDto.class);
	}


	/**
	 * {@inheritDoc}
	 */
	public VersionSWDto update(final VersionSWDto versionSWDto) {		
		
		final VersionSW versionSW = this.modelMapperUtils.map(versionSWDto, VersionSW.class);
		
		VersionSW versionSWBBDD = this.versionSWRepository.findById(versionSW.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		versionSWBBDD.setNombre(versionSWDto.getNombre());
		versionSWBBDD.setDescripcion(versionSWDto.getDescripcion());
		versionSWBBDD = this.versionSWRepository.save(versionSWBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, versionSWBBDD.getId());
		
		return this.modelMapperUtils.map(versionSWBBDD, VersionSWDto.class);
	}
	
}
