package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoPolarizacionRepository;
import com.dbcom.app.model.dto.TipoPolarizacionDto;
import com.dbcom.app.model.entity.TipoPolarizacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de polarización
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoPolarizacionServiceImpl implements TipoPolarizacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoPolarizacionRepository tipoPolarizacionRepository;
	
	@Autowired
	public TipoPolarizacionServiceImpl(ModelMapperUtils modelMapper,
									   TipoPolarizacionRepository tipoPolarizacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoPolarizacionRepository = tipoPolarizacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoPolarizacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoPolarizacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoPolarizacion tipoPolarizacionBBDD = this.tipoPolarizacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoPolarizacionRepository.delete(tipoPolarizacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoPolarizacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoPolarizacion> tiposPolarizacion = this.tipoPolarizacionRepository.findAll();

		final List<TipoPolarizacionDto> tiposPolarizacionDto = new ArrayList<>(tiposPolarizacion.size());		
		tiposPolarizacion.forEach(tipoPolarizacion -> tiposPolarizacionDto.add(this.modelMapperUtils.map(tipoPolarizacion, TipoPolarizacionDto.class)));
		
		return tiposPolarizacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoPolarizacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoPolarizacion tipoPolarizacion = this.tipoPolarizacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoPolarizacion, TipoPolarizacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoPolarizacionDto saveUpdate(final TipoPolarizacionDto tipoPolarizacionDto) {		
		
		TipoPolarizacion tipoPolarizacion = this.modelMapperUtils.map(tipoPolarizacionDto, TipoPolarizacion.class);
		
		return this.modelMapperUtils.map(this.tipoPolarizacionRepository.save(tipoPolarizacion), TipoPolarizacionDto.class);
	}
}
