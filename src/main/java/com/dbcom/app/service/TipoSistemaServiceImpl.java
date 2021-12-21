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
	public List<TipoSistemaDto> readAll() {

        final List<TipoSistema> tiposSistemas = this.tipoSistemasRepository.findAll();
		
		final List<TipoSistemaDto> tiposSistemasDto = new ArrayList<>(tiposSistemas.size());		
		tiposSistemas.forEach(tipoSistema -> tiposSistemasDto.add(this.modelMapperUtils.map(tipoSistema, TipoSistemaDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return tiposSistemasDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaDto save(TipoSistemaDto tipoSistemaDto) {

		TipoSistema tipoSistema = this.modelMapperUtils.map(tipoSistemaDto, TipoSistema.class);
	    
		tipoSistema = this.tipoSistemasRepository.save(tipoSistema);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoSistema.getNombre());		
		
		return this.modelMapperUtils.map(tipoSistema, TipoSistemaDto.class);
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
		
		// Insertamos los tipos de subsistemas que tiene asociados este tipo de sistema
		final List<TipoSubsistema> tiposSubsistemasAsociados = this.tipoSubsistemasRepository.findByTipoSistema(tipoSistema);
		result.setTiposSubsistemas(this.modelMapperUtils.mapAll2List(tiposSubsistemasAsociados, TipoSubsistemaDto.class));
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaDto update(TipoSistemaDto tipoSistemaDto) {

		final TipoSistema tipoSistema = this.modelMapperUtils.map(tipoSistemaDto, TipoSistema.class);
		
		TipoSistema tipoSistemaBBDD = this.tipoSistemasRepository.findById(tipoSistema.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoSistemaBBDD.setNombre(tipoSistemaDto.getNombre());
		tipoSistemaBBDD.setDescripcion(tipoSistemaDto.getDescripcion());
		tipoSistemaBBDD.setColor(tipoSistemaDto.getColor());
		tipoSistemaBBDD.setColorTexto(tipoSistema.getColorTexto());
		tipoSistemaBBDD.setCodigoFuncionRed(tipoSistema.getCodigoFuncionRed());
		tipoSistemaBBDD.setTiposSubsistemas(tipoSistemaBBDD.getTiposSubsistemas());
		tipoSistemaBBDD = this.tipoSistemasRepository.save(tipoSistemaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoSistemaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoSistemaBBDD, TipoSistemaDto.class);
	}	

}
