package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoRecursoOperativoRepository;
import com.dbcom.app.model.dto.TipoRecursoOperativoDto;
import com.dbcom.app.model.entity.TipoRecursoOperativo;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de recurso operativo
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoRecursoOperativoServiceImpl implements TipoRecursoOperativoService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoRecursoOperativoRepository tipoRecursoOperativoRepository;
	
	@Autowired
	public TipoRecursoOperativoServiceImpl(ModelMapperUtils modelMapper,
									       TipoRecursoOperativoRepository tipoRecursoOperativoRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoRecursoOperativoRepository = tipoRecursoOperativoRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoOperativoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoRecursoOperativoDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoRecursoOperativo tipoRecursoOperativoBBDD = this.tipoRecursoOperativoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoRecursoOperativoRepository.delete(tipoRecursoOperativoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoRecursoOperativoDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoRecursoOperativo> tiposRecursoOperativo = this.tipoRecursoOperativoRepository.findAll();

		final List<TipoRecursoOperativoDto> tiposRecursoOperativoDto = new ArrayList<>(tiposRecursoOperativo.size());		
		tiposRecursoOperativo.forEach(tipoRecursoOperativo -> tiposRecursoOperativoDto.add(this.modelMapperUtils.map(tipoRecursoOperativo, TipoRecursoOperativoDto.class)));
		
		return tiposRecursoOperativoDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoOperativoDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoRecursoOperativo tipoRecursoOperativo = this.tipoRecursoOperativoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoRecursoOperativo, TipoRecursoOperativoDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoOperativoDto save(final TipoRecursoOperativoDto tipoRecursoOperativoDto) {		
		
		TipoRecursoOperativo tipoRecursoOperativo = this.modelMapperUtils.map(tipoRecursoOperativoDto, TipoRecursoOperativo.class);
	    
		tipoRecursoOperativo = this.tipoRecursoOperativoRepository.save(tipoRecursoOperativo);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoRecursoOperativo.getNombre());		
		
		return this.modelMapperUtils.map(tipoRecursoOperativo, TipoRecursoOperativoDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoRecursoOperativoDto update(final TipoRecursoOperativoDto tipoRecursoOperativoDto) {		
		
		final TipoRecursoOperativo tipoRecursoOperativo = this.modelMapperUtils.map(tipoRecursoOperativoDto, TipoRecursoOperativo.class);
		
		TipoRecursoOperativo tipoRecursoOperativoBBDD = this.tipoRecursoOperativoRepository.findById(tipoRecursoOperativo.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoRecursoOperativoBBDD.setNombre(tipoRecursoOperativoDto.getNombre());
		tipoRecursoOperativoBBDD = this.tipoRecursoOperativoRepository.save(tipoRecursoOperativoBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoRecursoOperativoBBDD.getId());
		
		return this.modelMapperUtils.map(tipoRecursoOperativoBBDD, TipoRecursoOperativoDto.class);
	}
	
}
