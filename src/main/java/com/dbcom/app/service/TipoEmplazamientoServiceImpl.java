package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoEmplazamientoRepository;
import com.dbcom.app.model.dto.TipoEmplazamientoDto;
import com.dbcom.app.model.entity.TipoEmplazamiento;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de emplazamiento
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoEmplazamientoServiceImpl implements TipoEmplazamientoService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoEmplazamientoRepository tipoEmplazamientoRepository;
	
	@Autowired
	public TipoEmplazamientoServiceImpl(ModelMapperUtils modelMapper,
										TipoEmplazamientoRepository tipoEmplazamientoRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoEmplazamientoRepository = tipoEmplazamientoRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoEmplazamientoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoEmplazamientoDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoEmplazamiento tipoEmplazamientoBBDD = this.tipoEmplazamientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoEmplazamientoRepository.delete(tipoEmplazamientoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoEmplazamientoDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoEmplazamiento> tiposEmplazamiento = this.tipoEmplazamientoRepository.findAll();

		final List<TipoEmplazamientoDto> tiposEmplazamientoDto = new ArrayList<>(tiposEmplazamiento.size());		
		tiposEmplazamiento.forEach(tipoEmplazamiento -> tiposEmplazamientoDto.add(this.modelMapperUtils.map(tipoEmplazamiento, TipoEmplazamientoDto.class)));
		
		return tiposEmplazamientoDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoEmplazamientoDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoEmplazamiento tipoEmplazamiento = this.tipoEmplazamientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoEmplazamiento, TipoEmplazamientoDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoEmplazamientoDto save(final TipoEmplazamientoDto tipoEmplazamientoDto) {		
		
		TipoEmplazamiento tipoEmplazamiento = this.modelMapperUtils.map(tipoEmplazamientoDto, TipoEmplazamiento.class);
	    
		tipoEmplazamiento = this.tipoEmplazamientoRepository.save(tipoEmplazamiento);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoEmplazamiento.getNombre());		
		
		return this.modelMapperUtils.map(tipoEmplazamiento, TipoEmplazamientoDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoEmplazamientoDto update(final TipoEmplazamientoDto tipoEmplazamientoDto) {		
		
		final TipoEmplazamiento tipoEmplazamiento = this.modelMapperUtils.map(tipoEmplazamientoDto, TipoEmplazamiento.class);
		
		TipoEmplazamiento tipoEmplazamientoBBDD = this.tipoEmplazamientoRepository.findById(tipoEmplazamiento.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoEmplazamientoBBDD.setNombre(tipoEmplazamientoDto.getNombre());
		tipoEmplazamientoBBDD = this.tipoEmplazamientoRepository.save(tipoEmplazamientoBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoEmplazamientoBBDD.getId());
		
		return this.modelMapperUtils.map(tipoEmplazamientoBBDD, TipoEmplazamientoDto.class);
	}
	
}
