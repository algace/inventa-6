package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoBandaFrecuenciaRepository;
import com.dbcom.app.model.dto.TipoBandaFrecuenciaDto;
import com.dbcom.app.model.entity.TipoBandaFrecuencia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipos de bandas de frecuencia
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoBandaFrecuenciaServiceImpl implements TipoBandaFrecuenciaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoBandaFrecuenciaRepository tipoBandaFrecuenciaRepository;
	
	@Autowired
	public TipoBandaFrecuenciaServiceImpl(ModelMapperUtils modelMapper, 
										  TipoBandaFrecuenciaRepository tipoBandaFrecuenciaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoBandaFrecuenciaRepository = tipoBandaFrecuenciaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoBandaFrecuenciaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoBandaFrecuenciaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoBandaFrecuencia tipoBandaFrecuenciaBBDD = this.tipoBandaFrecuenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoBandaFrecuenciaRepository.delete(tipoBandaFrecuenciaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoBandaFrecuenciaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoBandaFrecuencia> tiposBandaFrecuencia = this.tipoBandaFrecuenciaRepository.findAll();

		final List<TipoBandaFrecuenciaDto> tiposBandaFrecuenciaDto = new ArrayList<>(tiposBandaFrecuencia.size());		
		tiposBandaFrecuencia.forEach(tipoBandaFrecuencia -> tiposBandaFrecuenciaDto.add(this.modelMapperUtils.map(tipoBandaFrecuencia, TipoBandaFrecuenciaDto.class)));
		
		return tiposBandaFrecuenciaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoBandaFrecuenciaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoBandaFrecuencia tipoBandaFrecuencia = this.tipoBandaFrecuenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoBandaFrecuencia, TipoBandaFrecuenciaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoBandaFrecuenciaDto saveUpdate(final TipoBandaFrecuenciaDto tipoBandaFrecuenciaDto) {		
		
		TipoBandaFrecuencia tipoBandaFrecuencia = this.modelMapperUtils.map(tipoBandaFrecuenciaDto, TipoBandaFrecuencia.class);	
		
		return this.modelMapperUtils.map(this.tipoBandaFrecuenciaRepository.save(tipoBandaFrecuencia), TipoBandaFrecuenciaDto.class);
	}
}
