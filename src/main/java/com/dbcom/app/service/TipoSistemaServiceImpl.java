package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoSistemaRepository;
import com.dbcom.app.model.dao.TipoSubsistemaRepository;
import com.dbcom.app.model.dto.TipoSistemaDto;
import com.dbcom.app.model.dto.TipoSistemaLiteDto;
import com.dbcom.app.model.dto.TipoSubsistemaDto;
import com.dbcom.app.model.entity.TipoSistema;
import com.dbcom.app.model.entity.TipoSubsistema;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TipoSistemaServiceImpl implements TipoSistemaService {

	private final TipoSistemaRepository tipoSistemasRepository;
	private final TipoSubsistemaRepository tipoSubsistemasRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public TipoSistemaServiceImpl(TipoSistemaRepository tipoSistemasRepository,
			TipoSubsistemaRepository tipoSubsistemasRepository,
			ModelMapperUtils modelMapper) {
		this.tipoSistemasRepository = tipoSistemasRepository;
		this.tipoSubsistemasRepository = tipoSubsistemasRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		return TipoSistemaDto.builder().build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		final TipoSistema tipoSistemaBBDD = this.tipoSistemasRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoSistemasRepository.delete(tipoSistemaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoSistemaLiteDto> readAll() {

        final List<TipoSistema> tiposSistemas = this.tipoSistemasRepository.findAll();
		
		final List<TipoSistemaLiteDto> tiposSistemasDto = new ArrayList<>(tiposSistemas.size());		
		tiposSistemas.forEach(tipoSistema -> tiposSistemasDto.add(this.modelMapperUtils.map(tipoSistema, TipoSistemaLiteDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return tiposSistemasDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaDto saveUpdate(TipoSistemaDto tipoSistemaDto) {

		TipoSistema tipoSistema = this.modelMapperUtils.map(tipoSistemaDto, TipoSistema.class);	
		
		return this.modelMapperUtils.map(this.tipoSistemasRepository.save(tipoSistema), TipoSistemaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaDto read(Long id) {

		final TipoSistema tipoSistema = this.tipoSistemasRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final TipoSistemaDto result = this.modelMapperUtils.map(tipoSistema, TipoSistemaDto.class);
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoSubsistemaDto> obtenerSubsistemasAsociados(Long id) {
		
		final TipoSistema tipoSistema = this.tipoSistemasRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final List<TipoSubsistema> tiposSubsistemasAsociados = this.tipoSubsistemasRepository.findByTipoSistema(tipoSistema);
		return this.modelMapperUtils.mapAll2List(tiposSubsistemasAsociados, TipoSubsistemaDto.class);
	}
}
