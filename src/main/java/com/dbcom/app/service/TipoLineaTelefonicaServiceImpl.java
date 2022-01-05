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
	@Override
	public TipoLineaTelefonicaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoLineaTelefonicaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoLineaTelefonica tipoLineaTelefonicaBBDD = this.tipoLineaTelefonicaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoLineaTelefonicaRepository.delete(tipoLineaTelefonicaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	@Override
	public TipoLineaTelefonicaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoLineaTelefonica tipoLineaTelefonica = this.tipoLineaTelefonicaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoLineaTelefonica, TipoLineaTelefonicaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoLineaTelefonicaDto saveUpdate(final TipoLineaTelefonicaDto tipoLineaTelefonicaDto) {		
		
		TipoLineaTelefonica tipoLineaTelefonica = this.modelMapperUtils.map(tipoLineaTelefonicaDto, TipoLineaTelefonica.class);
		
		return this.modelMapperUtils.map(this.tipoLineaTelefonicaRepository.save(tipoLineaTelefonica), TipoLineaTelefonicaDto.class);
	}
}
