package com.dbcom.app.service;

import java.util.ArrayList;
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
	private final TipoChasisService tipoChasisService;
	private final FuncionPasarelaService funcionPasarelaService;
	
	@Autowired
	public RecursoPasarelaServiceImpl(ModelMapperUtils modelMapper, RecursoPasarelaRepository recursoPasarelaRepository,
			TipoChasisService tipoChasisService, FuncionPasarelaService funcionPasarelaService) {
		this.modelMapperUtils = modelMapper;
		this.recursoPasarelaRepository = recursoPasarelaRepository;
		this.tipoChasisService = tipoChasisService;
		this.funcionPasarelaService = funcionPasarelaService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public RecursoPasarelaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return RecursoPasarelaDto.builder().tiposChasis(tipoChasisService.readAll())
										   .funcionPasarelas(funcionPasarelaService.readAll()).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final RecursoPasarela recursoPasarelaBBDD = this.recursoPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.recursoPasarelaRepository.delete(recursoPasarelaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RecursoPasarelaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<RecursoPasarela> recursoPasarelas = this.recursoPasarelaRepository.findAll();

		final List<RecursoPasarelaDto> recursoPasarelasDto = new ArrayList<>(recursoPasarelas.size());		
		recursoPasarelas.forEach(recursoPasarela -> recursoPasarelasDto.add(this.modelMapperUtils.map(recursoPasarela, RecursoPasarelaDto.class)));
		
		return recursoPasarelasDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public RecursoPasarelaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final RecursoPasarela recursoPasarela = this.recursoPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		RecursoPasarelaDto chasis = this.modelMapperUtils.map(recursoPasarela, RecursoPasarelaDto.class);
		chasis.setTiposChasis(tipoChasisService.readAll());
		chasis.setFuncionPasarelas(funcionPasarelaService.readAll());

		return chasis; 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public RecursoPasarelaDto save(final RecursoPasarelaDto recursoPasarelaDto) {		
		
		RecursoPasarela recursoPasarela = this.modelMapperUtils.map(recursoPasarelaDto, RecursoPasarela.class);
	    
		recursoPasarela = this.recursoPasarelaRepository.save(recursoPasarela);	
		
		log.info(LoggerConstants.LOG_CREATE, recursoPasarela.getNombre());		
		
		return this.modelMapperUtils.map(recursoPasarela, RecursoPasarelaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public RecursoPasarelaDto update(final RecursoPasarelaDto recursoPasarelaDto) {		
		
		final RecursoPasarela recursoPasarela = this.modelMapperUtils.map(recursoPasarelaDto, RecursoPasarela.class);
		
		RecursoPasarela recursoPasarelaBBDD = this.recursoPasarelaRepository.findById(recursoPasarela.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		recursoPasarelaBBDD.setNombre(recursoPasarelaDto.getNombre());
		recursoPasarelaBBDD = this.recursoPasarelaRepository.save(recursoPasarelaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, recursoPasarelaBBDD.getId());
		
		return this.modelMapperUtils.map(recursoPasarelaBBDD, RecursoPasarelaDto.class);
	}

}
