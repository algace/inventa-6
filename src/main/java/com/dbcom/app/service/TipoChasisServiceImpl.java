package com.dbcom.app.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoChasisRepository;
import com.dbcom.app.model.dto.TipoChasisDto;
import com.dbcom.app.model.entity.TipoChasis;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public final class TipoChasisServiceImpl implements TipoChasisService {

	private final ModelMapperUtils modelMapperUtils;
	private final TipoChasisRepository tipoChasisRepository;
	
	@Autowired
	public TipoChasisServiceImpl(ModelMapperUtils modelMapper,
									   TipoChasisRepository tipoChasisRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoChasisRepository = tipoChasisRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoChasisDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoChasisDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoChasis tipoChasisBBDD = this.tipoChasisRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoChasisRepository.delete(tipoChasisBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoChasisDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoChasis> tiposChasis = this.tipoChasisRepository.findAll();

		final List<TipoChasisDto> tiposChasisDto = new ArrayList<>(tiposChasis.size());		
		tiposChasis.forEach(tipoChasis -> tiposChasisDto.add(this.modelMapperUtils.map(tipoChasis, TipoChasisDto.class)));
		
		return tiposChasisDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoChasisDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoChasis tipoChasis = this.tipoChasisRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoChasis, TipoChasisDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoChasisDto save(final TipoChasisDto tipoChasisDto) {		
		
		TipoChasis tipoChasis = this.modelMapperUtils.map(tipoChasisDto, TipoChasis.class);
	    
		tipoChasis = this.tipoChasisRepository.save(tipoChasis);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoChasis.getNombre());		
		
		return this.modelMapperUtils.map(tipoChasis, TipoChasisDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoChasisDto update(final TipoChasisDto tipoChasisDto) {		
		
		final TipoChasis tipoChasis = this.modelMapperUtils.map(tipoChasisDto, TipoChasis.class);
		
		TipoChasis tipoChasisBBDD = this.tipoChasisRepository.findById(tipoChasis.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoChasisBBDD.setNombre(tipoChasisDto.getNombre());
		tipoChasisBBDD = this.tipoChasisRepository.save(tipoChasisBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoChasisBBDD.getId());
		
		return this.modelMapperUtils.map(tipoChasisBBDD, TipoChasisDto.class);
	}

}
