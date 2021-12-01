package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoUsoTiradaRepository;
import com.dbcom.app.model.dto.TipoUsoTiradaDto;
import com.dbcom.app.model.entity.TipoUsoTirada;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de uso tirada
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoUsoTiradaServiceImpl implements TipoUsoTiradaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoUsoTiradaRepository tipoUsoTiradaRepository;
	
	@Autowired
	public TipoUsoTiradaServiceImpl(ModelMapperUtils modelMapper,
									TipoUsoTiradaRepository tipoUsoTiradaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoUsoTiradaRepository = tipoUsoTiradaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoUsoTiradaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoUsoTiradaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoUsoTirada tipoUsoTiradaBBDD = this.tipoUsoTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoUsoTiradaRepository.delete(tipoUsoTiradaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoUsoTiradaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoUsoTirada> tiposUsoTirada = this.tipoUsoTiradaRepository.findAll();

		final List<TipoUsoTiradaDto> tiposUsoTiradaDto = new ArrayList<>(tiposUsoTirada.size());		
		tiposUsoTirada.forEach(tipoUsoTirada -> tiposUsoTiradaDto.add(this.modelMapperUtils.map(tipoUsoTirada, TipoUsoTiradaDto.class)));
		
		return tiposUsoTiradaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoUsoTiradaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoUsoTirada tipoUsoTirada = this.tipoUsoTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoUsoTirada, TipoUsoTiradaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoUsoTiradaDto save(final TipoUsoTiradaDto tipoUsoTiradaDto) {		
		
		TipoUsoTirada tipoUsoTirada = this.modelMapperUtils.map(tipoUsoTiradaDto, TipoUsoTirada.class);
	    
		tipoUsoTirada = this.tipoUsoTiradaRepository.save(tipoUsoTirada);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoUsoTirada.getNombre());		
		
		return this.modelMapperUtils.map(tipoUsoTirada, TipoUsoTiradaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoUsoTiradaDto update(final TipoUsoTiradaDto tipoUsoTiradaDto) {		
		
		final TipoUsoTirada tipoUsoTirada = this.modelMapperUtils.map(tipoUsoTiradaDto, TipoUsoTirada.class);
		
		TipoUsoTirada tipoUsoTiradaBBDD = this.tipoUsoTiradaRepository.findById(tipoUsoTirada.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoUsoTiradaBBDD.setNombre(tipoUsoTiradaDto.getNombre());
		tipoUsoTiradaBBDD = this.tipoUsoTiradaRepository.save(tipoUsoTiradaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoUsoTiradaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoUsoTiradaBBDD, TipoUsoTiradaDto.class);
	}
	
}
