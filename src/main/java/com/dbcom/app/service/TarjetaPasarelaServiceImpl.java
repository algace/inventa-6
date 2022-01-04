package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TarjetaPasarelaRepository;
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
	@Override
	public TarjetaPasarelaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return TarjetaPasarelaDto.builder()
				.chasisPasarelas(chasisPasarelaService.readAllLite())
				.build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TarjetaPasarela tarjetaPasarelaBBDD = this.tarjetaPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tarjetaPasarelaRepository.delete(tarjetaPasarelaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	@Override
	public TarjetaPasarelaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		
		final TarjetaPasarela tarjetaPasarela = this.tarjetaPasarelaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		List<ChasisPasarelaLiteDto> chasisPasarelaList = chasisPasarelaService.readAllLite().stream().map(chasisPasarela -> {
			
			chasisPasarela.setIsSeleccionado(Boolean.valueOf(tarjetaPasarela.getChasisPasarelas().stream().anyMatch(chasisPasarelaTarjeta -> chasisPasarela.getId() == chasisPasarelaTarjeta.getId())));
			
			return chasisPasarela;
			
		}).collect(Collectors.toList());
		
		TarjetaPasarelaDto tarjetaPasarelaDto = this.modelMapperUtils.map(tarjetaPasarela, TarjetaPasarelaDto.class);
		
		
		tarjetaPasarelaDto.setChasisPasarelas(chasisPasarelaList);
		
		return tarjetaPasarelaDto; 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TarjetaPasarelaDto saveUpdate(final TarjetaPasarelaDto tarjetaPasarelaDto) {		
		
		List<ChasisPasarelaLiteDto> chasisPasarelas = tarjetaPasarelaDto.getChasisPasarelas().stream().filter(chasisPasarela -> chasisPasarela.getIsSeleccionado()).toList();
		
		tarjetaPasarelaDto.setChasisPasarelas(chasisPasarelas);
		
		TarjetaPasarela tarjetaPasarela = this.modelMapperUtils.map(tarjetaPasarelaDto, TarjetaPasarela.class);
	    
		tarjetaPasarela = this.tarjetaPasarelaRepository.save(tarjetaPasarela);	
		
		return this.modelMapperUtils.map(tarjetaPasarela, TarjetaPasarelaDto.class);
		
	}

	@Override
	public TarjetaPasarelaDto setListChasisPasarelasInTarjetasPasarelasDTO(TarjetaPasarelaDto tarjetaPasarelaDto) {
		
		tarjetaPasarelaDto.setChasisPasarelas(chasisPasarelaService.readAllLite().stream().map(chasisPasarela -> {
			
			chasisPasarela.setIsSeleccionado(
					Boolean.valueOf(tarjetaPasarelaDto.getChasisPasarelas()
													  .stream()
													  .anyMatch(chasisPasarelaTarjeta -> (chasisPasarela.getId() == chasisPasarelaTarjeta.getId()) && 
															  				chasisPasarelaTarjeta.getIsSeleccionado())));
			
			return chasisPasarela;
			
		}).collect(Collectors.toList()));
		
		return tarjetaPasarelaDto;
	}

}
