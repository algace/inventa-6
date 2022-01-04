package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoCanalizacionRepository;
import com.dbcom.app.model.dto.TipoCanalizacionDto;
import com.dbcom.app.model.entity.TipoCanalizacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de canalización
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoCanalizacionServiceImpl implements TipoCanalizacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoCanalizacionRepository tipoCanalizacionRepository;
	
	@Autowired
	public TipoCanalizacionServiceImpl(ModelMapperUtils modelMapper,
									   TipoCanalizacionRepository tipoCanalizacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoCanalizacionRepository = tipoCanalizacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoCanalizacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoCanalizacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoCanalizacion tipoCanalizacionBBDD = this.tipoCanalizacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoCanalizacionRepository.delete(tipoCanalizacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoCanalizacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoCanalizacion> tiposCanalizacion = this.tipoCanalizacionRepository.findAll();

		final List<TipoCanalizacionDto> tiposCanalizacionDto = new ArrayList<>(tiposCanalizacion.size());		
		tiposCanalizacion.forEach(tipoCanalizacion -> tiposCanalizacionDto.add(this.modelMapperUtils.map(tipoCanalizacion, TipoCanalizacionDto.class)));
		
		return tiposCanalizacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoCanalizacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoCanalizacion tipoCanalizacion = this.tipoCanalizacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoCanalizacion, TipoCanalizacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoCanalizacionDto saveUpdate(final TipoCanalizacionDto tipoCanalizacionDto) {		
		
		TipoCanalizacion tipoCanalizacion = this.modelMapperUtils.map(tipoCanalizacionDto, TipoCanalizacion.class);
		
		return this.modelMapperUtils.map(this.tipoCanalizacionRepository.save(tipoCanalizacion), TipoCanalizacionDto.class);
	}
}
