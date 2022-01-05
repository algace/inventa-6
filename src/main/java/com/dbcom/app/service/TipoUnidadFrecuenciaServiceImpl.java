package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoUnidadFrecuenciaRepository;
import com.dbcom.app.model.dto.TipoUnidadFrecuenciaDto;
import com.dbcom.app.model.entity.TipoUnidadFrecuencia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TipoUnidadFrecuenciaServiceImpl implements TipoUnidadFrecuenciaService {

	private final ModelMapperUtils modelMapperUtils;
	private final TipoUnidadFrecuenciaRepository tipoUnidadFrecuenciaRepository;
	
	@Autowired
	public TipoUnidadFrecuenciaServiceImpl(ModelMapperUtils modelMapperUtils,
									   TipoUnidadFrecuenciaRepository tipoUnidadFrecuenciaRepository) {
		this.modelMapperUtils = modelMapperUtils;
		this.tipoUnidadFrecuenciaRepository = tipoUnidadFrecuenciaRepository;
	}
	
	@Override
	public TipoUnidadFrecuenciaDto create() {

		log.info(LoggerConstants.LOG_CREATE);
		return new TipoUnidadFrecuenciaDto();
	}

	@Override
	public void delete(Short id) {

		final TipoUnidadFrecuencia tipoUnidadFrecuenciaBBDD = this.tipoUnidadFrecuenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoUnidadFrecuenciaRepository.delete(tipoUnidadFrecuenciaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);

	}

	@Override
	public List<TipoUnidadFrecuenciaDto> readAll() {

		log.info(LoggerConstants.LOG_READALL);

		final List<TipoUnidadFrecuencia> tiposUnidadFrecuencia = this.tipoUnidadFrecuenciaRepository.findAll();

		final List<TipoUnidadFrecuenciaDto> tiposUnidadFrecuenciaDto = new ArrayList<>(tiposUnidadFrecuencia.size());		
		tiposUnidadFrecuencia.forEach(tipoUnidadFrecuencia -> tiposUnidadFrecuenciaDto.add(this.modelMapperUtils.map(tipoUnidadFrecuencia, TipoUnidadFrecuenciaDto.class)));
		
		return tiposUnidadFrecuenciaDto;
	}
	
	@Override
	public TipoUnidadFrecuenciaDto read(Short id) {

		log.info(LoggerConstants.LOG_READ);		

		final TipoUnidadFrecuencia tipoUnidadFrecuencia = this.tipoUnidadFrecuenciaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoUnidadFrecuencia, TipoUnidadFrecuenciaDto.class);
	}
	
	@Override
	public TipoUnidadFrecuenciaDto save(TipoUnidadFrecuenciaDto tipoUnidadFrecuenciaDto) {

		TipoUnidadFrecuencia tipoUnidadFrecuencia = this.modelMapperUtils.map(tipoUnidadFrecuenciaDto, TipoUnidadFrecuencia.class);
	    
		tipoUnidadFrecuencia = this.tipoUnidadFrecuenciaRepository.save(tipoUnidadFrecuencia);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoUnidadFrecuencia.getNombre());		
		
		return this.modelMapperUtils.map(tipoUnidadFrecuencia, TipoUnidadFrecuenciaDto.class);
	}

	@Override
	public TipoUnidadFrecuenciaDto update(TipoUnidadFrecuenciaDto tipoUnidadFrecuenciaDto) {

		final TipoUnidadFrecuencia tipoUnidadFrecuencia = this.modelMapperUtils.map(tipoUnidadFrecuenciaDto, TipoUnidadFrecuencia.class);
		
		TipoUnidadFrecuencia tipoUnidadFrecuenciaBBDD = this.tipoUnidadFrecuenciaRepository.findById(tipoUnidadFrecuencia.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoUnidadFrecuenciaBBDD.setNombre(tipoUnidadFrecuenciaDto.getNombre());
		tipoUnidadFrecuenciaBBDD = this.tipoUnidadFrecuenciaRepository.save(tipoUnidadFrecuenciaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoUnidadFrecuenciaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoUnidadFrecuenciaBBDD, TipoUnidadFrecuenciaDto.class);
	}

}
