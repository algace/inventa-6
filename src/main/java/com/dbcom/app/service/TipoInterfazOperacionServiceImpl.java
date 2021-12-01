package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoInterfazOperacionRepository;
import com.dbcom.app.model.dto.TipoInterfazOperacionDto;
import com.dbcom.app.model.entity.TipoInterfazOperacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de interfaz operación
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoInterfazOperacionServiceImpl implements TipoInterfazOperacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoInterfazOperacionRepository tipoInterfazOperacionRepository;
	
	@Autowired
	public TipoInterfazOperacionServiceImpl(ModelMapperUtils modelMapper,
									TipoInterfazOperacionRepository tipoInterfazOperacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoInterfazOperacionRepository = tipoInterfazOperacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoInterfazOperacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoInterfazOperacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoInterfazOperacion tipoInterfazOperacionBBDD = this.tipoInterfazOperacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoInterfazOperacionRepository.delete(tipoInterfazOperacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoInterfazOperacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoInterfazOperacion> tiposInterfazOperacion = this.tipoInterfazOperacionRepository.findAll();

		final List<TipoInterfazOperacionDto> tiposInterfazOperacionDto = new ArrayList<>(tiposInterfazOperacion.size());		
		tiposInterfazOperacion.forEach(tipoInterfazOperacion -> tiposInterfazOperacionDto.add(this.modelMapperUtils.map(tipoInterfazOperacion, TipoInterfazOperacionDto.class)));
		
		return tiposInterfazOperacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoInterfazOperacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoInterfazOperacion tipoInterfazOperacion = this.tipoInterfazOperacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoInterfazOperacion, TipoInterfazOperacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoInterfazOperacionDto save(final TipoInterfazOperacionDto tipoInterfazOperacionDto) {		
		
		TipoInterfazOperacion tipoInterfazOperacion = this.modelMapperUtils.map(tipoInterfazOperacionDto, TipoInterfazOperacion.class);
	    
		tipoInterfazOperacion = this.tipoInterfazOperacionRepository.save(tipoInterfazOperacion);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoInterfazOperacion.getNombre());		
		
		return this.modelMapperUtils.map(tipoInterfazOperacion, TipoInterfazOperacionDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoInterfazOperacionDto update(final TipoInterfazOperacionDto tipoInterfazOperacionDto) {		
		
		final TipoInterfazOperacion tipoInterfazOperacion = this.modelMapperUtils.map(tipoInterfazOperacionDto, TipoInterfazOperacion.class);
		
		TipoInterfazOperacion tipoInterfazOperacionBBDD = this.tipoInterfazOperacionRepository.findById(tipoInterfazOperacion.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoInterfazOperacionBBDD.setNombre(tipoInterfazOperacionDto.getNombre());
		tipoInterfazOperacionBBDD = this.tipoInterfazOperacionRepository.save(tipoInterfazOperacionBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoInterfazOperacionBBDD.getId());
		
		return this.modelMapperUtils.map(tipoInterfazOperacionBBDD, TipoInterfazOperacionDto.class);
	}
	
}
