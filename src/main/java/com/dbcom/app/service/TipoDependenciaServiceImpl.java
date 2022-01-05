package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoDependenciaRepository;
import com.dbcom.app.model.dto.TipoDependenciaDto;
import com.dbcom.app.model.entity.TipoDependencia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de dependencia
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoDependenciaServiceImpl implements TipoDependenciaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoDependenciaRepository tipoDependenciaRepository;
	
	@Autowired
	public TipoDependenciaServiceImpl(ModelMapperUtils modelMapper,
								  	  TipoDependenciaRepository tipoDependenciaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoDependenciaRepository = tipoDependenciaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoDependenciaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoDependenciaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoDependencia tipoDependenciaBBDD = this.tipoDependenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoDependenciaRepository.delete(tipoDependenciaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoDependenciaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoDependencia> tiposDependencia = this.tipoDependenciaRepository.findAll();

		final List<TipoDependenciaDto> tiposDependenciaDto = new ArrayList<>(tiposDependencia.size());		
		tiposDependencia.forEach(tipoDependencia -> tiposDependenciaDto.add(this.modelMapperUtils.map(tipoDependencia, TipoDependenciaDto.class)));
		
		return tiposDependenciaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoDependenciaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoDependencia tipoDependencia = this.tipoDependenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoDependencia, TipoDependenciaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoDependenciaDto saveUpdate(final TipoDependenciaDto tipoDependenciaDto) {		
		
		TipoDependencia tipoDependencia = this.modelMapperUtils.map(tipoDependenciaDto, TipoDependencia.class);
		
		return this.modelMapperUtils.map(this.tipoDependenciaRepository.save(tipoDependencia), TipoDependenciaDto.class);
	}
}
