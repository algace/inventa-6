package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoRecursoRadioRepository;
import com.dbcom.app.model.dto.TipoRecursoRadioDto;
import com.dbcom.app.model.entity.TipoRecursoRadio;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de recurso radio
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoRecursoRadioServiceImpl implements TipoRecursoRadioService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoRecursoRadioRepository tipoRecursoRadioRepository;
	
	@Autowired
	public TipoRecursoRadioServiceImpl(ModelMapperUtils modelMapper,
									   TipoRecursoRadioRepository tipoRecursoRadioRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoRecursoRadioRepository = tipoRecursoRadioRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoRadioDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoRecursoRadioDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoRecursoRadio tipoRecursoRadioBBDD = this.tipoRecursoRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoRecursoRadioRepository.delete(tipoRecursoRadioBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoRecursoRadioDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoRecursoRadio> tiposRecursoRadio = this.tipoRecursoRadioRepository.findAll();

		final List<TipoRecursoRadioDto> tiposRecursoRadioDto = new ArrayList<>(tiposRecursoRadio.size());		
		tiposRecursoRadio.forEach(tipoRecursoRadio -> tiposRecursoRadioDto.add(this.modelMapperUtils.map(tipoRecursoRadio, TipoRecursoRadioDto.class)));
		
		return tiposRecursoRadioDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoRadioDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoRecursoRadio tipoRecursoRadio = this.tipoRecursoRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoRecursoRadio, TipoRecursoRadioDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoRadioDto save(final TipoRecursoRadioDto tipoRecursoRadioDto) {		
		
		TipoRecursoRadio tipoRecursoRadio = this.modelMapperUtils.map(tipoRecursoRadioDto, TipoRecursoRadio.class);
	    
		tipoRecursoRadio = this.tipoRecursoRadioRepository.save(tipoRecursoRadio);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoRecursoRadio.getNombre());		
		
		return this.modelMapperUtils.map(tipoRecursoRadio, TipoRecursoRadioDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoRadioDto update(final TipoRecursoRadioDto tipoRecursoRadioDto) {		
		
		final TipoRecursoRadio tipoRecursoRadio = this.modelMapperUtils.map(tipoRecursoRadioDto, TipoRecursoRadio.class);
		
		TipoRecursoRadio tipoRecursoRadioBBDD = this.tipoRecursoRadioRepository.findById(tipoRecursoRadio.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoRecursoRadioBBDD.setNombre(tipoRecursoRadioDto.getNombre());
		tipoRecursoRadioBBDD = this.tipoRecursoRadioRepository.save(tipoRecursoRadioBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoRecursoRadioBBDD.getId());
		
		return this.modelMapperUtils.map(tipoRecursoRadioBBDD, TipoRecursoRadioDto.class);
	}
	
}
