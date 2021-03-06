package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.FrecuenciaATCRepository;
import com.dbcom.app.model.dto.FrecuenciaATCDto;
import com.dbcom.app.model.dto.FrecuenciaATCLiteDto;
import com.dbcom.app.model.entity.FrecuenciaATC;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * 
 * Lógica para frecuenciasATC
 */
@Service
@Slf4j
public final class FrecuenciaATCServiceImpl implements FrecuenciaATCService {
	
	private FrecuenciaATCRepository frecuenciaATCRepository;
	private final ModelMapperUtils  modelMapperUtils;

	@Autowired
	public FrecuenciaATCServiceImpl(ModelMapperUtils modelMapper,
			FrecuenciaATCRepository frecuenciaATCRepository) {
		this.modelMapperUtils = modelMapper;
		this.frecuenciaATCRepository = frecuenciaATCRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FrecuenciaATCDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		
		return FrecuenciaATCDto.builder().build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long id) {			
		
		final FrecuenciaATC frecuenciaATCBBDD = this.frecuenciaATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.frecuenciaATCRepository.delete(frecuenciaATCBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FrecuenciaATCLiteDto> readAll() {
		
		final List<FrecuenciaATC> frecuenciasATC = this.frecuenciaATCRepository.findAll();
		
		final List<FrecuenciaATCLiteDto> frecuenciasATCDto = new ArrayList<>(frecuenciasATC.size());		
		frecuenciasATC.forEach(frecuenciaATC -> frecuenciasATCDto.add(this.modelMapperUtils.map(frecuenciaATC, FrecuenciaATCLiteDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return frecuenciasATCDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FrecuenciaATCDto read(final Long id) {		
		
		final FrecuenciaATC frecuenciaATC = this.frecuenciaATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final FrecuenciaATCDto result = this.modelMapperUtils.map(frecuenciaATC, FrecuenciaATCDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FrecuenciaATCDto saveUpdate(final FrecuenciaATCDto frecuenciaATCDto) {		
		
		FrecuenciaATC frecuenciaATC = this.modelMapperUtils.map(frecuenciaATCDto, FrecuenciaATC.class);
		
		return this.modelMapperUtils.map(this.frecuenciaATCRepository.save(frecuenciaATC), FrecuenciaATCDto.class);
	}
}
 