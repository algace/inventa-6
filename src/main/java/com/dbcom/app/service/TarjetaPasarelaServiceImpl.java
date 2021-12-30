package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.ChasisPasarelaRepository;
import com.dbcom.app.model.dao.TarjetaPasarelaRepository;
import com.dbcom.app.model.dto.ChasisPasarelaDto;
import com.dbcom.app.model.dto.ChasisPasarelaLiteDto;
import com.dbcom.app.model.dto.TarjetaPasarelaDto;
import com.dbcom.app.model.entity.TarjetaPasarela;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TarjetaPasarelaServiceImpl implements TarjetaPasarelaService{

	private final ModelMapperUtils modelMapperUtils;
	private final TarjetaPasarelaRepository tarjetaPasarelaRepository;
	private final ChasisPasarelaService chasisPasarelaService;
	
	@Autowired
	public TarjetaPasarelaServiceImpl(ModelMapperUtils modelMapper,
									   TarjetaPasarelaRepository tarjetaPasarelaRepository,
									   ChasisPasarelaService chasisPasarelaService) {
		this.modelMapperUtils = modelMapper;
		this.tarjetaPasarelaRepository = tarjetaPasarelaRepository;
		this.chasisPasarelaService = chasisPasarelaService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TarjetaPasarelaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return TarjetaPasarelaDto.builder()
				.chasisPasarelas(chasisPasarelaService.readAllLite())
				.build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TarjetaPasarela tarjetaPasarelaBBDD = this.tarjetaPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tarjetaPasarelaRepository.delete(tarjetaPasarelaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TarjetaPasarelaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TarjetaPasarela> tarjetasPasarela = this.tarjetaPasarelaRepository.findAll();

		final List<TarjetaPasarelaDto> tarjetasPasarelaDto = new ArrayList<>(tarjetasPasarela.size());		
		tarjetasPasarela.forEach(tarjetaPasarela -> tarjetasPasarelaDto.add(this.modelMapperUtils.map(tarjetaPasarela, TarjetaPasarelaDto.class)));
		
		return tarjetasPasarelaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TarjetaPasarelaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		
		final TarjetaPasarela tarjetaPasarela = this.tarjetaPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		List<ChasisPasarelaLiteDto> chasisPasarelaList = chasisPasarelaService.readAllLite().stream().map(chasisPasarela -> {
			
			chasisPasarela.setIsSeleccionado(Boolean.valueOf(tarjetaPasarela.getChasisPasarela().stream().anyMatch(chasisPasarelaTarjeta -> chasisPasarela.getId() == chasisPasarelaTarjeta.getId())));
			
			return chasisPasarela;
			
		}).collect(Collectors.toList());
		
		TarjetaPasarelaDto tarjetaPasarelaDto = this.modelMapperUtils.map(tarjetaPasarela, TarjetaPasarelaDto.class);
		
		
		tarjetaPasarelaDto.setChasisPasarelas(chasisPasarelaList);
		
		return tarjetaPasarelaDto; 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TarjetaPasarelaDto save(final TarjetaPasarelaDto tarjetaPasarelaDto) {		
		
		tarjetaPasarelaDto.setChasisPasarelas(tarjetaPasarelaDto.getChasisPasarelas());
		
		TarjetaPasarela tarjetaPasarela = this.modelMapperUtils.map(tarjetaPasarelaDto, TarjetaPasarela.class);
	    
		tarjetaPasarela = this.tarjetaPasarelaRepository.save(tarjetaPasarela);	
		
		log.info(LoggerConstants.LOG_CREATE, tarjetaPasarela.getNombre());		
		
		return this.modelMapperUtils.map(tarjetaPasarela, TarjetaPasarelaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TarjetaPasarelaDto update(final TarjetaPasarelaDto tarjetaPasarelaDto) {		
		
		final TarjetaPasarela tarjetaPasarela = this.modelMapperUtils.map(tarjetaPasarelaDto, TarjetaPasarela.class);
		
		TarjetaPasarela tarjetaPasarelaBBDD = this.tarjetaPasarelaRepository.findById(tarjetaPasarela.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tarjetaPasarelaBBDD.setNombre(tarjetaPasarelaDto.getNombre());
		tarjetaPasarelaBBDD = this.tarjetaPasarelaRepository.save(tarjetaPasarelaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tarjetaPasarelaBBDD.getId());
		
		return this.modelMapperUtils.map(tarjetaPasarelaBBDD, TarjetaPasarelaDto.class);
	}
}
