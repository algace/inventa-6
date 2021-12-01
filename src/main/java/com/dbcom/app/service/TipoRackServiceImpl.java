package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoRackRepository;
import com.dbcom.app.model.dto.TipoRackDto;
import com.dbcom.app.model.entity.TipoRack;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de rack
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoRackServiceImpl implements TipoRackService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoRackRepository tipoRackRepository;
	
	@Autowired
	public TipoRackServiceImpl(ModelMapperUtils modelMapper,
							   TipoRackRepository tipoRackRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoRackRepository = tipoRackRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoRackDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoRackDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoRack tipoRackBBDD = this.tipoRackRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoRackRepository.delete(tipoRackBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoRackDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoRack> tiposRack = this.tipoRackRepository.findAll();

		final List<TipoRackDto> tiposRackDto = new ArrayList<>(tiposRack.size());		
		tiposRack.forEach(tipoRack -> tiposRackDto.add(this.modelMapperUtils.map(tipoRack, TipoRackDto.class)));
		
		return tiposRackDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoRackDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoRack tipoRack = this.tipoRackRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoRack, TipoRackDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoRackDto save(final TipoRackDto tipoRackDto) {		
		
		TipoRack tipoRack = this.modelMapperUtils.map(tipoRackDto, TipoRack.class);
	    
		tipoRack = this.tipoRackRepository.save(tipoRack);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoRack.getNombre());		
		
		return this.modelMapperUtils.map(tipoRack, TipoRackDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoRackDto update(final TipoRackDto tipoRackDto) {		
		
		final TipoRack tipoRack = this.modelMapperUtils.map(tipoRackDto, TipoRack.class);
		
		TipoRack tipoRackBBDD = this.tipoRackRepository.findById(tipoRack.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoRackBBDD.setNombre(tipoRackDto.getNombre());
		tipoRackBBDD = this.tipoRackRepository.save(tipoRackBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoRackBBDD.getId());
		
		return this.modelMapperUtils.map(tipoRackBBDD, TipoRackDto.class);
	}
	
}
