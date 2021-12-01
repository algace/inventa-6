package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoTiradaRepository;
import com.dbcom.app.model.dto.TipoTiradaDto;
import com.dbcom.app.model.entity.TipoTirada;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de tirada
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoTiradaServiceImpl implements TipoTiradaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoTiradaRepository tipoTiradaRepository;
	
	@Autowired
	public TipoTiradaServiceImpl(ModelMapperUtils modelMapper,
								 TipoTiradaRepository tipoTiradaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoTiradaRepository = tipoTiradaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoTiradaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoTiradaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoTirada tipoTiradaBBDD = this.tipoTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoTiradaRepository.delete(tipoTiradaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoTiradaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoTirada> tiposTirada = this.tipoTiradaRepository.findAll();

		final List<TipoTiradaDto> tiposTiradaDto = new ArrayList<>(tiposTirada.size());		
		tiposTirada.forEach(tipoTirada -> tiposTiradaDto.add(this.modelMapperUtils.map(tipoTirada, TipoTiradaDto.class)));
		
		return tiposTiradaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoTiradaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoTirada tipoTirada = this.tipoTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoTirada, TipoTiradaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoTiradaDto save(final TipoTiradaDto tipoTiradaDto) {		
		
		TipoTirada tipoTirada = this.modelMapperUtils.map(tipoTiradaDto, TipoTirada.class);
	    
		tipoTirada = this.tipoTiradaRepository.save(tipoTirada);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoTirada.getNombre());		
		
		return this.modelMapperUtils.map(tipoTirada, TipoTiradaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoTiradaDto update(final TipoTiradaDto tipoTiradaDto) {		
		
		final TipoTirada tipoTirada = this.modelMapperUtils.map(tipoTiradaDto, TipoTirada.class);
		
		TipoTirada tipoTiradaBBDD = this.tipoTiradaRepository.findById(tipoTirada.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoTiradaBBDD.setNombre(tipoTiradaDto.getNombre());
		tipoTiradaBBDD = this.tipoTiradaRepository.save(tipoTiradaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoTiradaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoTiradaBBDD, TipoTiradaDto.class);
	}
	
}
