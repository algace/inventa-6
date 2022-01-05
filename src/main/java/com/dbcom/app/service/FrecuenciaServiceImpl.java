package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.FrecuenciaRepository;
import com.dbcom.app.model.dto.FrecuenciaDto;
import com.dbcom.app.model.entity.Frecuencia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * 
 * Lógica para frecuencias
 */
@Service
@Slf4j
public final class FrecuenciaServiceImpl implements FrecuenciaService {
	
	private FrecuenciaRepository frecuenciaRepository;
	private final ModelMapperUtils  modelMapperUtils;

	@Autowired
	public FrecuenciaServiceImpl(ModelMapperUtils modelMapper,
			FrecuenciaRepository frecuenciaRepository) {
		this.modelMapperUtils = modelMapper;
		this.frecuenciaRepository = frecuenciaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FrecuenciaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new FrecuenciaDto();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long id) {			
		
		final Frecuencia frecuenciaBBDD = this.frecuenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.frecuenciaRepository.delete(frecuenciaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FrecuenciaDto> readAll() {
		
		final List<Frecuencia> frecuencias = this.frecuenciaRepository.findAll();
		
		final List<FrecuenciaDto> frecuenciasDto = new ArrayList<>(frecuencias.size());		
		frecuencias.forEach(frecuencia -> frecuenciasDto.add(this.modelMapperUtils.map(frecuencia, FrecuenciaDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return frecuenciasDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FrecuenciaDto read(final Long id) {		
		
		final Frecuencia frecuencia = this.frecuenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final FrecuenciaDto result = this.modelMapperUtils.map(frecuencia, FrecuenciaDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FrecuenciaDto saveUpdate(final FrecuenciaDto frecuenciaDto) {		
		
		Frecuencia frecuencia = this.modelMapperUtils.map(frecuenciaDto, Frecuencia.class);		
		
		return this.modelMapperUtils.map(this.frecuenciaRepository.save(frecuencia), FrecuenciaDto.class);
	}
}
 