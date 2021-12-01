package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoModulacionRepository;
import com.dbcom.app.model.dto.TipoModulacionDto;
import com.dbcom.app.model.entity.TipoModulacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de modulación
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoModulacionServiceImpl implements TipoModulacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoModulacionRepository tipoModulacionRepository;
	
	@Autowired
	public TipoModulacionServiceImpl(ModelMapperUtils modelMapper,
								 	 TipoModulacionRepository tipoModulacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoModulacionRepository = tipoModulacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoModulacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoModulacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoModulacion tipoModulacionBBDD = this.tipoModulacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoModulacionRepository.delete(tipoModulacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoModulacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoModulacion> tiposModulacion = this.tipoModulacionRepository.findAll();

		final List<TipoModulacionDto> tiposModulacionDto = new ArrayList<>(tiposModulacion.size());		
		tiposModulacion.forEach(tipoModulacion -> tiposModulacionDto.add(this.modelMapperUtils.map(tipoModulacion, TipoModulacionDto.class)));
		
		return tiposModulacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoModulacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoModulacion tipoModulacion = this.tipoModulacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoModulacion, TipoModulacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoModulacionDto save(final TipoModulacionDto tipoModulacionDto) {		
		
		TipoModulacion tipoModulacion = this.modelMapperUtils.map(tipoModulacionDto, TipoModulacion.class);
	    
		tipoModulacion = this.tipoModulacionRepository.save(tipoModulacion);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoModulacion.getNombre());		
		
		return this.modelMapperUtils.map(tipoModulacion, TipoModulacionDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoModulacionDto update(final TipoModulacionDto tipoModulacionDto) {		
		
		final TipoModulacion tipoModulacion = this.modelMapperUtils.map(tipoModulacionDto, TipoModulacion.class);
		
		TipoModulacion tipoModulacionBBDD = this.tipoModulacionRepository.findById(tipoModulacion.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoModulacionBBDD.setNombre(tipoModulacionDto.getNombre());
		tipoModulacionBBDD.setDescripcionCorta(tipoModulacionDto.getDescripcionCorta());
		tipoModulacionBBDD.setDescripcionLarga(tipoModulacionDto.getDescripcionLarga());
		tipoModulacionBBDD = this.tipoModulacionRepository.save(tipoModulacionBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoModulacionBBDD.getId());
		
		return this.modelMapperUtils.map(tipoModulacionBBDD, TipoModulacionDto.class);
	}
	
}
