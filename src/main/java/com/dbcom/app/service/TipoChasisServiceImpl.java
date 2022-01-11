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
import com.dbcom.app.model.dto.TipoChasisLiteDto;
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
	@Override
	public TipoChasisDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoChasisDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoChasis tipoChasisBBDD = this.tipoChasisRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoChasisRepository.delete(tipoChasisBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoChasisLiteDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoChasis> tiposChasis = this.tipoChasisRepository.findAll();

		final List<TipoChasisLiteDto> tiposChasisDto = new ArrayList<>(tiposChasis.size());		
		tiposChasis.forEach(tipoChasis -> tiposChasisDto.add(this.modelMapperUtils.map(tipoChasis, TipoChasisLiteDto.class)));
		
		return tiposChasisDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoChasisDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoChasis tipoChasis = this.tipoChasisRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoChasis, TipoChasisDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoChasisDto saveUpdate(final TipoChasisDto tipoChasisDto) {		
		
		TipoChasis tipoChasis = this.modelMapperUtils.map(tipoChasisDto, TipoChasis.class);
		
		return this.modelMapperUtils.map(this.tipoChasisRepository.save(tipoChasis), TipoChasisDto.class);
	}
}
