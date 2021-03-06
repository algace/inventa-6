package com.dbcom.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoSubsistemaRepository;
import com.dbcom.app.model.dto.TipoSubsistemaDto;
import com.dbcom.app.model.dto.TipoSubsistemaLiteDto;
import com.dbcom.app.model.entity.TipoSubsistema;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TipoSubsistemaServiceImpl implements TipoSubsistemaService {

	private final TipoSubsistemaRepository tipoSubsistemasRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public TipoSubsistemaServiceImpl(TipoSubsistemaRepository tipoSubsistemasRepository,
			ModelMapperUtils modelMapper) {
		this.tipoSubsistemasRepository = tipoSubsistemasRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto create() {
		log.info(LoggerConstants.LOG_CREATE);

		return TipoSubsistemaDto.builder().build();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		final TipoSubsistema tipoSubsistemaBBDD = this.tipoSubsistemasRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoSubsistemasRepository.delete(tipoSubsistemaBBDD);		
		
		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoSubsistemaLiteDto> readAll() {

		log.info(LoggerConstants.LOG_READALL);
		
		return this.modelMapperUtils.mapAll2List(this.tipoSubsistemasRepository.findAll(), TipoSubsistemaLiteDto.class);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto read(Long id) {

		final TipoSubsistema tipoSubsistema = this.tipoSubsistemasRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final TipoSubsistemaDto result = this.modelMapperUtils.map(tipoSubsistema, TipoSubsistemaDto.class);
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto saveUpdate(TipoSubsistemaDto tipoSubsistemaDto) {

		TipoSubsistema tipoSubsistema = this.modelMapperUtils.map(tipoSubsistemaDto, TipoSubsistema.class);
		
		return this.modelMapperUtils.map(this.tipoSubsistemasRepository.save(tipoSubsistema), TipoSubsistemaDto.class);
	}
}
