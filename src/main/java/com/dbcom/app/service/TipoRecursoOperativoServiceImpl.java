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
	@Override
	public TipoRecursoOperativoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoRecursoOperativoDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoRecursoOperativo tipoRecursoOperativoBBDD = this.tipoRecursoOperativoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoRecursoOperativoRepository.delete(tipoRecursoOperativoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	@Override
	public TipoRecursoOperativoDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoRecursoOperativo tipoRecursoOperativo = this.tipoRecursoOperativoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoRecursoOperativo, TipoRecursoOperativoDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoRecursoOperativoDto saveUpdate(final TipoRecursoOperativoDto tipoRecursoOperativoDto) {		
		
		TipoRecursoOperativo tipoRecursoOperativo = this.modelMapperUtils.map(tipoRecursoOperativoDto, TipoRecursoOperativo.class);
		
		return this.modelMapperUtils.map(this.tipoRecursoOperativoRepository.save(tipoRecursoOperativo), TipoRecursoOperativoDto.class);
	}
}
