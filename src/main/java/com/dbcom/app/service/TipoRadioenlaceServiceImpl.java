package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoRadioenlaceRepository;
import com.dbcom.app.model.dto.TipoRadioenlaceDto;
import com.dbcom.app.model.entity.TipoRadioenlace;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de radioenlace
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoRadioenlaceServiceImpl implements TipoRadioenlaceService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoRadioenlaceRepository tipoRadioenlaceRepository;
	
	@Autowired
	public TipoRadioenlaceServiceImpl(ModelMapperUtils modelMapper,
									  TipoRadioenlaceRepository tipoRadioenlaceRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoRadioenlaceRepository = tipoRadioenlaceRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoRadioenlaceDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoRadioenlaceDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoRadioenlace tipoRadioenlaceBBDD = this.tipoRadioenlaceRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoRadioenlaceRepository.delete(tipoRadioenlaceBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoRadioenlaceDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoRadioenlace> tiposRadioenlace = this.tipoRadioenlaceRepository.findAll();

		final List<TipoRadioenlaceDto> tiposRadioenlaceDto = new ArrayList<>(tiposRadioenlace.size());		
		tiposRadioenlace.forEach(tipoRadioenlace -> tiposRadioenlaceDto.add(this.modelMapperUtils.map(tipoRadioenlace, TipoRadioenlaceDto.class)));
		
		return tiposRadioenlaceDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoRadioenlaceDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoRadioenlace tipoRadioenlace = this.tipoRadioenlaceRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoRadioenlace, TipoRadioenlaceDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoRadioenlaceDto saveUpdate(final TipoRadioenlaceDto tipoRadioenlaceDto) {		
		
		TipoRadioenlace tipoRadioenlace = this.modelMapperUtils.map(tipoRadioenlaceDto, TipoRadioenlace.class);
		
		return this.modelMapperUtils.map(this.tipoRadioenlaceRepository.save(tipoRadioenlace), TipoRadioenlaceDto.class);
	}
}
