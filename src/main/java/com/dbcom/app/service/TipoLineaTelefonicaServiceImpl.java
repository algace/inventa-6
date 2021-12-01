package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoLineaTelefonicaRepository;
import com.dbcom.app.model.dto.TipoLineaTelefonicaDto;
import com.dbcom.app.model.entity.TipoLineaTelefonica;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de línea telefónica
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoLineaTelefonicaServiceImpl implements TipoLineaTelefonicaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoLineaTelefonicaRepository tipoLineaTelefonicaRepository;
	
	@Autowired
	public TipoLineaTelefonicaServiceImpl(ModelMapperUtils modelMapper,
									   TipoLineaTelefonicaRepository tipoLineaTelefonicaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoLineaTelefonicaRepository = tipoLineaTelefonicaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoLineaTelefonicaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoLineaTelefonicaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoLineaTelefonica tipoLineaTelefonicaBBDD = this.tipoLineaTelefonicaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoLineaTelefonicaRepository.delete(tipoLineaTelefonicaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoLineaTelefonicaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoLineaTelefonica> tiposLineaTelefonica = this.tipoLineaTelefonicaRepository.findAll();

		final List<TipoLineaTelefonicaDto> tiposLineaTelefonicaDto = new ArrayList<>(tiposLineaTelefonica.size());		
		tiposLineaTelefonica.forEach(tipoLineaTelefonica -> tiposLineaTelefonicaDto.add(this.modelMapperUtils.map(tipoLineaTelefonica, TipoLineaTelefonicaDto.class)));
		
		return tiposLineaTelefonicaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoLineaTelefonicaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoLineaTelefonica tipoLineaTelefonica = this.tipoLineaTelefonicaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoLineaTelefonica, TipoLineaTelefonicaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoLineaTelefonicaDto save(final TipoLineaTelefonicaDto tipoLineaTelefonicaDto) {		
		
		TipoLineaTelefonica tipoLineaTelefonica = this.modelMapperUtils.map(tipoLineaTelefonicaDto, TipoLineaTelefonica.class);
	    
		tipoLineaTelefonica = this.tipoLineaTelefonicaRepository.save(tipoLineaTelefonica);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoLineaTelefonica.getNombre());		
		
		return this.modelMapperUtils.map(tipoLineaTelefonica, TipoLineaTelefonicaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoLineaTelefonicaDto update(final TipoLineaTelefonicaDto tipoLineaTelefonicaDto) {		
		
		final TipoLineaTelefonica tipoLineaTelefonica = this.modelMapperUtils.map(tipoLineaTelefonicaDto, TipoLineaTelefonica.class);
		
		TipoLineaTelefonica tipoLineaTelefonicaBBDD = this.tipoLineaTelefonicaRepository.findById(tipoLineaTelefonica.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoLineaTelefonicaBBDD.setNombre(tipoLineaTelefonicaDto.getNombre());
		tipoLineaTelefonicaBBDD = this.tipoLineaTelefonicaRepository.save(tipoLineaTelefonicaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoLineaTelefonicaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoLineaTelefonicaBBDD, TipoLineaTelefonicaDto.class);
	}
	
}
