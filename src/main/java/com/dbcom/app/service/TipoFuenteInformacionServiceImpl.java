package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoFuenteInformacionRepository;
import com.dbcom.app.model.dto.TipoFuenteInformacionDto;
import com.dbcom.app.model.entity.TipoFuenteInformacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de fuente de información
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoFuenteInformacionServiceImpl implements TipoFuenteInformacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoFuenteInformacionRepository tipoFuenteInformacionRepository;
	
	@Autowired
	public TipoFuenteInformacionServiceImpl(ModelMapperUtils modelMapper,
									TipoFuenteInformacionRepository tipoFuenteInformacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoFuenteInformacionRepository = tipoFuenteInformacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoFuenteInformacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoFuenteInformacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoFuenteInformacion tipoFuenteInformacionBBDD = this.tipoFuenteInformacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoFuenteInformacionRepository.delete(tipoFuenteInformacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoFuenteInformacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoFuenteInformacion> tiposFuenteInformacion = this.tipoFuenteInformacionRepository.findAll();

		final List<TipoFuenteInformacionDto> tiposFuenteInformacionDto = new ArrayList<>(tiposFuenteInformacion.size());		
		tiposFuenteInformacion.forEach(tipoFuenteInformacion -> tiposFuenteInformacionDto.add(this.modelMapperUtils.map(tipoFuenteInformacion, TipoFuenteInformacionDto.class)));
		
		return tiposFuenteInformacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoFuenteInformacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoFuenteInformacion tipoFuenteInformacion = this.tipoFuenteInformacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoFuenteInformacion, TipoFuenteInformacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoFuenteInformacionDto save(final TipoFuenteInformacionDto tipoFuenteInformacionDto) {		
		
		TipoFuenteInformacion tipoFuenteInformacion = this.modelMapperUtils.map(tipoFuenteInformacionDto, TipoFuenteInformacion.class);
	    
		tipoFuenteInformacion = this.tipoFuenteInformacionRepository.save(tipoFuenteInformacion);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoFuenteInformacion.getNombre());		
		
		return this.modelMapperUtils.map(tipoFuenteInformacion, TipoFuenteInformacionDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoFuenteInformacionDto update(final TipoFuenteInformacionDto tipoFuenteInformacionDto) {		
		
		final TipoFuenteInformacion tipoFuenteInformacion = this.modelMapperUtils.map(tipoFuenteInformacionDto, TipoFuenteInformacion.class);
		
		TipoFuenteInformacion tipoFuenteInformacionBBDD = this.tipoFuenteInformacionRepository.findById(tipoFuenteInformacion.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoFuenteInformacionBBDD.setNombre(tipoFuenteInformacionDto.getNombre());
		tipoFuenteInformacionBBDD = this.tipoFuenteInformacionRepository.save(tipoFuenteInformacionBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoFuenteInformacionBBDD.getId());
		
		return this.modelMapperUtils.map(tipoFuenteInformacionBBDD, TipoFuenteInformacionDto.class);
	}
	
}
