package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoEstructuraRepository;
import com.dbcom.app.model.dto.TipoEstructuraDto;
import com.dbcom.app.model.entity.TipoEstructura;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de estructura
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoEstructuraServiceImpl implements TipoEstructuraService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoEstructuraRepository tipoEstructuraRepository;
	
	@Autowired
	public TipoEstructuraServiceImpl(ModelMapperUtils modelMapper,
									 TipoEstructuraRepository tipoEstructuraRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoEstructuraRepository = tipoEstructuraRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoEstructuraDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoEstructuraDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoEstructura tipoEstructuraBBDD = this.tipoEstructuraRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoEstructuraRepository.delete(tipoEstructuraBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoEstructuraDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoEstructura> tiposEstructura = this.tipoEstructuraRepository.findAll();

		final List<TipoEstructuraDto> tiposEstructuraDto = new ArrayList<>(tiposEstructura.size());		
		tiposEstructura.forEach(tipoEstructura -> tiposEstructuraDto.add(this.modelMapperUtils.map(tipoEstructura, TipoEstructuraDto.class)));
		
		return tiposEstructuraDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoEstructuraDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoEstructura tipoEstructura = this.tipoEstructuraRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoEstructura, TipoEstructuraDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoEstructuraDto save(final TipoEstructuraDto tipoEstructuraDto) {		
		
		TipoEstructura tipoEstructura = this.modelMapperUtils.map(tipoEstructuraDto, TipoEstructura.class);
	    
		tipoEstructura = this.tipoEstructuraRepository.save(tipoEstructura);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoEstructura.getNombre());		
		
		return this.modelMapperUtils.map(tipoEstructura, TipoEstructuraDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoEstructuraDto update(final TipoEstructuraDto tipoEstructuraDto) {		
		
		final TipoEstructura tipoEstructura = this.modelMapperUtils.map(tipoEstructuraDto, TipoEstructura.class);
		
		TipoEstructura tipoEstructuraBBDD = this.tipoEstructuraRepository.findById(tipoEstructura.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoEstructuraBBDD.setNombre(tipoEstructuraDto.getNombre());
		tipoEstructuraBBDD = this.tipoEstructuraRepository.save(tipoEstructuraBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoEstructuraBBDD.getId());
		
		return this.modelMapperUtils.map(tipoEstructuraBBDD, TipoEstructuraDto.class);
	}
	
}
