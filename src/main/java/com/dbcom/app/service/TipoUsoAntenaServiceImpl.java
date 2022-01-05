package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoUsoAntenaRepository;
import com.dbcom.app.model.dto.TipoUsoAntenaDto;
import com.dbcom.app.model.entity.TipoUsoAntena;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de uso antena
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoUsoAntenaServiceImpl implements TipoUsoAntenaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoUsoAntenaRepository tipoUsoAntenaRepository;
	
	@Autowired
	public TipoUsoAntenaServiceImpl(ModelMapperUtils modelMapper,
									TipoUsoAntenaRepository tipoUsoAntenaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoUsoAntenaRepository = tipoUsoAntenaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsoAntenaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoUsoAntenaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoUsoAntena tipoUsoAntenaBBDD = this.tipoUsoAntenaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoUsoAntenaRepository.delete(tipoUsoAntenaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoUsoAntenaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoUsoAntena> tiposUsoAntena = this.tipoUsoAntenaRepository.findAll();

		final List<TipoUsoAntenaDto> tiposUsoAntenaDto = new ArrayList<>(tiposUsoAntena.size());		
		tiposUsoAntena.forEach(tipoUsoAntena -> tiposUsoAntenaDto.add(this.modelMapperUtils.map(tipoUsoAntena, TipoUsoAntenaDto.class)));
		
		return tiposUsoAntenaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsoAntenaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoUsoAntena tipoUsoAntena = this.tipoUsoAntenaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoUsoAntena, TipoUsoAntenaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsoAntenaDto saveUpdate(final TipoUsoAntenaDto tipoUsoAntenaDto) {		
		
		TipoUsoAntena tipoUsoAntena = this.modelMapperUtils.map(tipoUsoAntenaDto, TipoUsoAntena.class);	
		
		return this.modelMapperUtils.map(this.tipoUsoAntenaRepository.save(tipoUsoAntena), TipoUsoAntenaDto.class);
	}
}
