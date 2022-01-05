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
	@Override
	public TipoEquipoConfiguracionRadioDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoEquipoConfiguracionRadioDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadioBBDD = this.tipoEquipoConfiguracionRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoEquipoConfiguracionRadioRepository.delete(tipoEquipoConfiguracionRadioBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	@Override
	public TipoEquipoConfiguracionRadioDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio = this.tipoEquipoConfiguracionRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoEquipoConfiguracionRadio, TipoEquipoConfiguracionRadioDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEquipoConfiguracionRadioDto saveUpdate(final TipoEquipoConfiguracionRadioDto tipoEquipoConfiguracionRadioDto) {		
		
		TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio = this.modelMapperUtils.map(tipoEquipoConfiguracionRadioDto, TipoEquipoConfiguracionRadio.class);
		
		return this.modelMapperUtils.map(this.tipoEquipoConfiguracionRadioRepository.save(tipoEquipoConfiguracionRadio), TipoEquipoConfiguracionRadioDto.class);
	}	
}
