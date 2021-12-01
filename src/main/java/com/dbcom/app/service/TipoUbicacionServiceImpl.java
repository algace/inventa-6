package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoUbicacionRepository;
import com.dbcom.app.model.dto.TipoUbicacionDto;
import com.dbcom.app.model.entity.TipoUbicacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de ubicación
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoUbicacionServiceImpl implements TipoUbicacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoUbicacionRepository tipoUbicacionRepository;
	
	@Autowired
	public TipoUbicacionServiceImpl(ModelMapperUtils modelMapper,
						  		    TipoUbicacionRepository tipoUbicacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoUbicacionRepository = tipoUbicacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoUbicacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoUbicacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoUbicacion tipoUbicacionBBDD = this.tipoUbicacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoUbicacionRepository.delete(tipoUbicacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoUbicacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoUbicacion> tiposUbicacion = this.tipoUbicacionRepository.findAll();

		final List<TipoUbicacionDto> tiposUbicacionDto = new ArrayList<>(tiposUbicacion.size());		
		tiposUbicacion.forEach(tipoUbicacion -> tiposUbicacionDto.add(this.modelMapperUtils.map(tipoUbicacion, TipoUbicacionDto.class)));
		
		return tiposUbicacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoUbicacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoUbicacion tipoUbicacion = this.tipoUbicacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoUbicacion, TipoUbicacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoUbicacionDto save(final TipoUbicacionDto tipoUbicacionDto) {		
		
		TipoUbicacion tipoUbicacion = this.modelMapperUtils.map(tipoUbicacionDto, TipoUbicacion.class);
	    
		tipoUbicacion = this.tipoUbicacionRepository.save(tipoUbicacion);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoUbicacion.getNombre());		
		
		return this.modelMapperUtils.map(tipoUbicacion, TipoUbicacionDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoUbicacionDto update(final TipoUbicacionDto tipoUbicacionDto) {		
		
		final TipoUbicacion tipoUbicacion = this.modelMapperUtils.map(tipoUbicacionDto, TipoUbicacion.class);
		
		TipoUbicacion tipoUbicacionBBDD = this.tipoUbicacionRepository.findById(tipoUbicacion.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoUbicacionBBDD.setNombre(tipoUbicacionDto.getNombre());
		tipoUbicacionBBDD = this.tipoUbicacionRepository.save(tipoUbicacionBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoUbicacionBBDD.getId());
		
		return this.modelMapperUtils.map(tipoUbicacionBBDD, TipoUbicacionDto.class);
	}
	
}
