package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.FuncionPasarelaRepository;
import com.dbcom.app.model.dto.FuncionPasarelaDto;
import com.dbcom.app.model.entity.FuncionPasarela;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FuncionPasarelaServiceImpl implements FuncionPasarelaService{

	private final ModelMapperUtils modelMapperUtils;
	private final FuncionPasarelaRepository funcionPasarelaRepository;
	
	@Autowired
	public FuncionPasarelaServiceImpl(ModelMapperUtils modelMapper,
									   FuncionPasarelaRepository funcionPasarelaRepository) {
		this.modelMapperUtils = modelMapper;
		this.funcionPasarelaRepository = funcionPasarelaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FuncionPasarelaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new FuncionPasarelaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final FuncionPasarela FuncionPasarelaBBDD = this.funcionPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.funcionPasarelaRepository.delete(FuncionPasarelaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FuncionPasarelaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<FuncionPasarela> funcionPasarelas = this.funcionPasarelaRepository.findAll();

		final List<FuncionPasarelaDto> funcionPasarelasDto = new ArrayList<>(funcionPasarelas.size());		
		funcionPasarelas.forEach(funcionPasarela -> funcionPasarelasDto.add(this.modelMapperUtils.map(funcionPasarela, FuncionPasarelaDto.class)));
		
		return funcionPasarelasDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public FuncionPasarelaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final FuncionPasarela funcionPasarela = this.funcionPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(funcionPasarela, FuncionPasarelaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public FuncionPasarelaDto save(final FuncionPasarelaDto funcionPasarelaDto) {		
		
		FuncionPasarela funcionPasarela = this.modelMapperUtils.map(funcionPasarelaDto, FuncionPasarela.class);
	    
		funcionPasarela = this.funcionPasarelaRepository.save(funcionPasarela);	
		
		log.info(LoggerConstants.LOG_CREATE, funcionPasarela.getNombre());		
		
		return this.modelMapperUtils.map(funcionPasarela, FuncionPasarelaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public FuncionPasarelaDto update(final FuncionPasarelaDto funcionPasarelaDto) {		
		
		final FuncionPasarela funcionPasarela = this.modelMapperUtils.map(funcionPasarelaDto, FuncionPasarela.class);
		
		FuncionPasarela FuncionPasarelaBBDD = this.funcionPasarelaRepository.findById(funcionPasarela.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		FuncionPasarelaBBDD.setNombre(funcionPasarelaDto.getNombre());
		FuncionPasarelaBBDD = this.funcionPasarelaRepository.save(FuncionPasarelaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, FuncionPasarelaBBDD.getId());
		
		return this.modelMapperUtils.map(FuncionPasarelaBBDD, FuncionPasarelaDto.class);
	}
}
