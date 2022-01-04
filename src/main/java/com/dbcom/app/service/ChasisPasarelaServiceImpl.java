package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.ChasisPasarelaRepository;
import com.dbcom.app.model.dto.ChasisPasarelaDto;
import com.dbcom.app.model.dto.ChasisPasarelaLiteDto;
import com.dbcom.app.model.entity.ChasisPasarela;
import com.dbcom.app.model.entity.TipoChasis;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class ChasisPasarelaServiceImpl implements ChasisPasarelaService{
	
	private final ModelMapperUtils modelMapperUtils;
	private final ChasisPasarelaRepository chasisPasarelaRepository;
	private final TipoChasisService tipoChasisService;
	
	@Autowired
	public ChasisPasarelaServiceImpl(ModelMapperUtils modelMapper, ChasisPasarelaRepository chasisPasarelaRepository,
			TipoChasisService tipoChasisService) {
		this.modelMapperUtils = modelMapper;
		this.chasisPasarelaRepository = chasisPasarelaRepository;
		this.tipoChasisService = tipoChasisService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ChasisPasarelaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return ChasisPasarelaDto.builder().tiposChasis(tipoChasisService.readAll()).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final ChasisPasarela chasisPasarelaBBDD = this.chasisPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.chasisPasarelaRepository.delete(chasisPasarelaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ChasisPasarelaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<ChasisPasarela> chasisPasarelas = this.chasisPasarelaRepository.findAll();

		final List<ChasisPasarelaDto> chasisPasarelasDto = new ArrayList<>(chasisPasarelas.size());		
		chasisPasarelas.forEach(chasisPasarela -> chasisPasarelasDto.add(this.modelMapperUtils.map(chasisPasarela, ChasisPasarelaDto.class)));
		
		return chasisPasarelasDto;
	}
	
	public List<ChasisPasarelaLiteDto> readAllLite() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<ChasisPasarela> chasisPasarelas = this.chasisPasarelaRepository.findAll();

		final List<ChasisPasarelaLiteDto> chasisPasarelasDto = new ArrayList<>(chasisPasarelas.size());		
		chasisPasarelas.forEach(chasisPasarela -> chasisPasarelasDto.add(this.modelMapperUtils.map(chasisPasarela, ChasisPasarelaLiteDto.class)));
		
		return chasisPasarelasDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ChasisPasarelaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final ChasisPasarela chasisPasarela = this.chasisPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		ChasisPasarelaDto chasis = this.modelMapperUtils.map(chasisPasarela, ChasisPasarelaDto.class);
		chasis.setTiposChasis(tipoChasisService.readAll());

		return chasis; 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public ChasisPasarelaDto saveUpdate(final ChasisPasarelaDto chasisPasarelaDto) {		
		
		ChasisPasarela chasisPasarela = this.modelMapperUtils.map(chasisPasarelaDto, ChasisPasarela.class);
	    
		return this.modelMapperUtils.map(this.chasisPasarelaRepository.save(chasisPasarela), ChasisPasarelaDto.class);
	}
}
