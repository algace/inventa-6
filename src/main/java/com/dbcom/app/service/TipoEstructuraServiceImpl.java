package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoEstructuraRepository;
import com.dbcom.app.model.dto.TipoEstructuraDto;
import com.dbcom.app.model.entity.TipoEstructura;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de estructura
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoEstructuraServiceImpl implements TipoEstructuraService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoEstructuraRepository tipoEstructuraRepository;
	
	@Autowired
	public TipoEstructuraServiceImpl(ModelMapperUtils modelMapper,
									 TipoEstructuraRepository tipoEstructuraRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoEstructuraRepository = tipoEstructuraRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEstructuraDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoEstructuraDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoEstructura tipoEstructuraBBDD = this.tipoEstructuraRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoEstructuraRepository.delete(tipoEstructuraBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoEstructuraDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoEstructura> tiposEstructura = this.tipoEstructuraRepository.findAll();

		final List<TipoEstructuraDto> tiposEstructuraDto = new ArrayList<>(tiposEstructura.size());		
		tiposEstructura.forEach(tipoEstructura -> tiposEstructuraDto.add(this.modelMapperUtils.map(tipoEstructura, TipoEstructuraDto.class)));
		
		return tiposEstructuraDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEstructuraDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoEstructura tipoEstructura = this.tipoEstructuraRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoEstructura, TipoEstructuraDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEstructuraDto saveUpdate(final TipoEstructuraDto tipoEstructuraDto) {		
		
		TipoEstructura tipoEstructura = this.modelMapperUtils.map(tipoEstructuraDto, TipoEstructura.class);
		
		return this.modelMapperUtils.map(this.tipoEstructuraRepository.save(tipoEstructura), TipoEstructuraDto.class);
	}
}
