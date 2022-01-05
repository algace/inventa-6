package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoModulacionRepository;
import com.dbcom.app.model.dto.TipoModulacionDto;
import com.dbcom.app.model.entity.TipoModulacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de modulación
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoModulacionServiceImpl implements TipoModulacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoModulacionRepository tipoModulacionRepository;
	
	@Autowired
	public TipoModulacionServiceImpl(ModelMapperUtils modelMapper,
								 	 TipoModulacionRepository tipoModulacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoModulacionRepository = tipoModulacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoModulacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoModulacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoModulacion tipoModulacionBBDD = this.tipoModulacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoModulacionRepository.delete(tipoModulacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoModulacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoModulacion> tiposModulacion = this.tipoModulacionRepository.findAll();

		final List<TipoModulacionDto> tiposModulacionDto = new ArrayList<>(tiposModulacion.size());		
		tiposModulacion.forEach(tipoModulacion -> tiposModulacionDto.add(this.modelMapperUtils.map(tipoModulacion, TipoModulacionDto.class)));
		
		return tiposModulacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoModulacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoModulacion tipoModulacion = this.tipoModulacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoModulacion, TipoModulacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoModulacionDto saveUpdate(final TipoModulacionDto tipoModulacionDto) {		
		
		TipoModulacion tipoModulacion = this.modelMapperUtils.map(tipoModulacionDto, TipoModulacion.class);	
		
		return this.modelMapperUtils.map(this.tipoModulacionRepository.save(tipoModulacion), TipoModulacionDto.class);
	}
}
