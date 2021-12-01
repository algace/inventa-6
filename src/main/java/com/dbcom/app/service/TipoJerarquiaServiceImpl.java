package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoJerarquiaRepository;
import com.dbcom.app.model.dto.TipoJerarquiaDto;
import com.dbcom.app.model.entity.TipoJerarquia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de jerarquía
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoJerarquiaServiceImpl implements TipoJerarquiaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoJerarquiaRepository tipoJerarquiaRepository;
	
	@Autowired
	public TipoJerarquiaServiceImpl(ModelMapperUtils modelMapper,
									   TipoJerarquiaRepository tipoJerarquiaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoJerarquiaRepository = tipoJerarquiaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoJerarquiaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoJerarquiaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoJerarquia tipoJerarquiaBBDD = this.tipoJerarquiaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoJerarquiaRepository.delete(tipoJerarquiaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoJerarquiaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoJerarquia> tiposJerarquia = this.tipoJerarquiaRepository.findAll();

		final List<TipoJerarquiaDto> tiposJerarquiaDto = new ArrayList<>(tiposJerarquia.size());		
		tiposJerarquia.forEach(tipoJerarquia -> tiposJerarquiaDto.add(this.modelMapperUtils.map(tipoJerarquia, TipoJerarquiaDto.class)));
		
		return tiposJerarquiaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoJerarquiaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoJerarquia tipoJerarquia = this.tipoJerarquiaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoJerarquia, TipoJerarquiaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoJerarquiaDto save(final TipoJerarquiaDto tipoJerarquiaDto) {		
		
		TipoJerarquia tipoJerarquia = this.modelMapperUtils.map(tipoJerarquiaDto, TipoJerarquia.class);
	    
		tipoJerarquia = this.tipoJerarquiaRepository.save(tipoJerarquia);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoJerarquia.getNombre());		
		
		return this.modelMapperUtils.map(tipoJerarquia, TipoJerarquiaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoJerarquiaDto update(final TipoJerarquiaDto tipoJerarquiaDto) {		
		
		final TipoJerarquia tipoJerarquia = this.modelMapperUtils.map(tipoJerarquiaDto, TipoJerarquia.class);
		
		TipoJerarquia tipoJerarquiaBBDD = this.tipoJerarquiaRepository.findById(tipoJerarquia.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoJerarquiaBBDD.setNombre(tipoJerarquiaDto.getNombre());
		tipoJerarquiaBBDD = this.tipoJerarquiaRepository.save(tipoJerarquiaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoJerarquiaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoJerarquiaBBDD, TipoJerarquiaDto.class);
	}
	
}
