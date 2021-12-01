package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoEquipoConfiguracionRadioRepository;
import com.dbcom.app.model.dto.TipoEquipoConfiguracionRadioDto;
import com.dbcom.app.model.entity.TipoEquipoConfiguracionRadio;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de equipo/configuración de radio
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoEquipoConfiguracionRadioServiceImpl implements TipoEquipoConfiguracionRadioService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoEquipoConfiguracionRadioRepository tipoEquipoConfiguracionRadioRepository;
	
	@Autowired
	public TipoEquipoConfiguracionRadioServiceImpl(ModelMapperUtils modelMapper,
									TipoEquipoConfiguracionRadioRepository tipoEquipoConfiguracionRadioRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoEquipoConfiguracionRadioRepository = tipoEquipoConfiguracionRadioRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoEquipoConfiguracionRadioDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoEquipoConfiguracionRadioDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadioBBDD = this.tipoEquipoConfiguracionRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoEquipoConfiguracionRadioRepository.delete(tipoEquipoConfiguracionRadioBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoEquipoConfiguracionRadioDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoEquipoConfiguracionRadio> tiposEquipoConfiguracionRadio = this.tipoEquipoConfiguracionRadioRepository.findAll();

		final List<TipoEquipoConfiguracionRadioDto> tiposEquipoConfiguracionRadioDto = new ArrayList<>(tiposEquipoConfiguracionRadio.size());		
		tiposEquipoConfiguracionRadio.forEach(tipoEquipoConfiguracionRadio -> tiposEquipoConfiguracionRadioDto.add(this.modelMapperUtils.map(tipoEquipoConfiguracionRadio, TipoEquipoConfiguracionRadioDto.class)));
		
		return tiposEquipoConfiguracionRadioDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoEquipoConfiguracionRadioDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio = this.tipoEquipoConfiguracionRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoEquipoConfiguracionRadio, TipoEquipoConfiguracionRadioDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoEquipoConfiguracionRadioDto save(final TipoEquipoConfiguracionRadioDto tipoEquipoConfiguracionRadioDto) {		
		
		TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio = this.modelMapperUtils.map(tipoEquipoConfiguracionRadioDto, TipoEquipoConfiguracionRadio.class);
	    
		tipoEquipoConfiguracionRadio = this.tipoEquipoConfiguracionRadioRepository.save(tipoEquipoConfiguracionRadio);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoEquipoConfiguracionRadio.getNombre());		
		
		return this.modelMapperUtils.map(tipoEquipoConfiguracionRadio, TipoEquipoConfiguracionRadioDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoEquipoConfiguracionRadioDto update(final TipoEquipoConfiguracionRadioDto tipoEquipoConfiguracionRadioDto) {		
		
		final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio = this.modelMapperUtils.map(tipoEquipoConfiguracionRadioDto, TipoEquipoConfiguracionRadio.class);
		
		TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadioBBDD = this.tipoEquipoConfiguracionRadioRepository.findById(tipoEquipoConfiguracionRadio.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoEquipoConfiguracionRadioBBDD.setNombre(tipoEquipoConfiguracionRadioDto.getNombre());
		tipoEquipoConfiguracionRadioBBDD.setIte(tipoEquipoConfiguracionRadioDto.getIte());
		tipoEquipoConfiguracionRadioBBDD = this.tipoEquipoConfiguracionRadioRepository.save(tipoEquipoConfiguracionRadioBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoEquipoConfiguracionRadioBBDD.getId());
		
		return this.modelMapperUtils.map(tipoEquipoConfiguracionRadioBBDD, TipoEquipoConfiguracionRadioDto.class);
	}
	
}
