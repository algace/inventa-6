package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoConfiguracionRadioenlaceRepository;
import com.dbcom.app.model.dto.TipoConfiguracionRadioenlaceDto;
import com.dbcom.app.model.entity.TipoConfiguracionRadioenlace;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de configuración radioenlace
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoConfiguracionRadioenlaceServiceImpl implements TipoConfiguracionRadioenlaceService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoConfiguracionRadioenlaceRepository tipoConfiguracionRadioenlaceRepository;
	
	@Autowired
	public TipoConfiguracionRadioenlaceServiceImpl(ModelMapperUtils modelMapper,
									   TipoConfiguracionRadioenlaceRepository tipoConfiguracionRadioenlaceRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoConfiguracionRadioenlaceRepository = tipoConfiguracionRadioenlaceRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoConfiguracionRadioenlaceDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoConfiguracionRadioenlaceDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoConfiguracionRadioenlace tipoConfiguracionRadioenlaceBBDD = this.tipoConfiguracionRadioenlaceRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoConfiguracionRadioenlaceRepository.delete(tipoConfiguracionRadioenlaceBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoConfiguracionRadioenlaceDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoConfiguracionRadioenlace> tiposConfiguracionRadioenlace = this.tipoConfiguracionRadioenlaceRepository.findAll();

		final List<TipoConfiguracionRadioenlaceDto> tiposConfiguracionRadioenlaceDto = new ArrayList<>(tiposConfiguracionRadioenlace.size());		
		tiposConfiguracionRadioenlace.forEach(tipoConfiguracionRadioenlace -> tiposConfiguracionRadioenlaceDto.add(this.modelMapperUtils.map(tipoConfiguracionRadioenlace, TipoConfiguracionRadioenlaceDto.class)));
		
		return tiposConfiguracionRadioenlaceDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoConfiguracionRadioenlaceDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoConfiguracionRadioenlace tipoConfiguracionRadioenlace = this.tipoConfiguracionRadioenlaceRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoConfiguracionRadioenlace, TipoConfiguracionRadioenlaceDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoConfiguracionRadioenlaceDto save(final TipoConfiguracionRadioenlaceDto tipoConfiguracionRadioenlaceDto) {		
		
		TipoConfiguracionRadioenlace tipoConfiguracionRadioenlace = this.modelMapperUtils.map(tipoConfiguracionRadioenlaceDto, TipoConfiguracionRadioenlace.class);
	    
		tipoConfiguracionRadioenlace = this.tipoConfiguracionRadioenlaceRepository.save(tipoConfiguracionRadioenlace);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoConfiguracionRadioenlace.getNombre());		
		
		return this.modelMapperUtils.map(tipoConfiguracionRadioenlace, TipoConfiguracionRadioenlaceDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoConfiguracionRadioenlaceDto update(final TipoConfiguracionRadioenlaceDto tipoConfiguracionRadioenlaceDto) {		
		
		final TipoConfiguracionRadioenlace tipoConfiguracionRadioenlace = this.modelMapperUtils.map(tipoConfiguracionRadioenlaceDto, TipoConfiguracionRadioenlace.class);
		
		TipoConfiguracionRadioenlace tipoConfiguracionRadioenlaceBBDD = this.tipoConfiguracionRadioenlaceRepository.findById(tipoConfiguracionRadioenlace.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoConfiguracionRadioenlaceBBDD.setNombre(tipoConfiguracionRadioenlaceDto.getNombre());
		tipoConfiguracionRadioenlaceBBDD = this.tipoConfiguracionRadioenlaceRepository.save(tipoConfiguracionRadioenlaceBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoConfiguracionRadioenlaceBBDD.getId());
		
		return this.modelMapperUtils.map(tipoConfiguracionRadioenlaceBBDD, TipoConfiguracionRadioenlaceDto.class);
	}
	
}
