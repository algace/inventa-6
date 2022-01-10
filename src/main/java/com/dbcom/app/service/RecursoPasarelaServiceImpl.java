package com.dbcom.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.RecursoPasarelaRepository;
import com.dbcom.app.model.dto.RecursoPasarelaDto;
import com.dbcom.app.model.entity.RecursoPasarela;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class RecursoPasarelaServiceImpl implements RecursoPasarelaService{
	
	private final ModelMapperUtils modelMapperUtils;
	private final RecursoPasarelaRepository recursoPasarelaRepository;
	
	@Autowired
	public RecursoPasarelaServiceImpl(ModelMapperUtils modelMapper, 
			RecursoPasarelaRepository recursoPasarelaRepository) {
		this.modelMapperUtils = modelMapper;
		this.recursoPasarelaRepository = recursoPasarelaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecursoPasarelaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return RecursoPasarelaDto.builder().build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final RecursoPasarela recursoPasarelaBBDD = this.recursoPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.recursoPasarelaRepository.delete(recursoPasarelaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RecursoPasarelaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		return this.modelMapperUtils.mapAll2List(this.recursoPasarelaRepository.findAll(), RecursoPasarelaDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecursoPasarelaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final RecursoPasarela recursoPasarela = this.recursoPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		return this.modelMapperUtils.map(recursoPasarela, RecursoPasarelaDto.class);
		
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecursoPasarelaDto saveUpdate(final RecursoPasarelaDto recursoPasarelaDto) {		
		
		RecursoPasarela recursoPasarela = this.modelMapperUtils.map(recursoPasarelaDto, RecursoPasarela.class);
		
		return this.modelMapperUtils.map(this.recursoPasarelaRepository.save(recursoPasarela), RecursoPasarelaDto.class);
	}

}
