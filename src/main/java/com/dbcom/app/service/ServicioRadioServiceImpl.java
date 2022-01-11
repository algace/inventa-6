package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.ServicioRadioRepository;
import com.dbcom.app.model.dto.ServicioRadioDto;
import com.dbcom.app.model.dto.ServicioRadioLiteDto;
import com.dbcom.app.model.entity.ServicioRadio;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServicioRadioServiceImpl implements ServicioRadioService{

	private final ModelMapperUtils modelMapperUtils;
	private final ServicioRadioRepository servicioRadioRepository;
	
	@Autowired
	public ServicioRadioServiceImpl(ModelMapperUtils modelMapper,
									   ServicioRadioRepository servicioRadioRepository) {
		this.modelMapperUtils = modelMapper;
		this.servicioRadioRepository = servicioRadioRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServicioRadioDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new ServicioRadioDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final ServicioRadio servicioRadioBBDD = this.servicioRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.servicioRadioRepository.delete(servicioRadioBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ServicioRadioLiteDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<ServicioRadio> serviciosRadio = this.servicioRadioRepository.findAll();

		final List<ServicioRadioLiteDto> serviciosRadioDto = new ArrayList<>(serviciosRadio.size());		
		serviciosRadio.forEach(servicioRadio -> serviciosRadioDto.add(this.modelMapperUtils.map(servicioRadio, ServicioRadioLiteDto.class)));
		
		return serviciosRadioDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServicioRadioDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final ServicioRadio servicioRadio = this.servicioRadioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(servicioRadio, ServicioRadioDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServicioRadioDto saveUpdate(final ServicioRadioDto servicioRadioDto) {		
		
		ServicioRadio servicioRadio = this.modelMapperUtils.map(servicioRadioDto, ServicioRadio.class);	
		
		return this.modelMapperUtils.map(this.servicioRadioRepository.save(servicioRadio), ServicioRadioDto.class);
	}

}
