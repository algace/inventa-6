package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TiposSubsistemasRepository;
import com.dbcom.app.model.dto.TipoSubsistemaDto;
import com.dbcom.app.model.entity.TipoSistema;
import com.dbcom.app.model.entity.TipoSubsistema;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TiposSubsistemasServiceImpl implements TiposSubsistemasService {

	private final TiposSubsistemasRepository tipoSubsistemasRepository;
	private final TiposSistemasService tiposSistemasService;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public TiposSubsistemasServiceImpl(TiposSubsistemasRepository tipoSubsistemasRepository,
			TiposSistemasService tiposSistemasService,
			ModelMapperUtils modelMapper) {
		this.tipoSubsistemasRepository = tipoSubsistemasRepository;
		this.tiposSistemasService = tiposSistemasService;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		return TipoSubsistemaDto.builder().tiposSistemasDisponibles(tiposSistemasService.readAll()).build();
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
	public List<TipoSubsistemaDto> readAll() {

		final List<TipoSubsistema> tiposSubsistemas = this.tipoSubsistemasRepository.findAll();
		
		final List<TipoSubsistemaDto> tiposSubsistemasDto = new ArrayList<>(tiposSubsistemas.size());
		tiposSubsistemas.forEach(tiposSubsistema -> tiposSubsistemasDto.add(this.modelMapperUtils.map(tiposSubsistema, TipoSubsistemaDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return tiposSubsistemasDto;
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
	public TipoSubsistemaDto save(TipoSubsistemaDto tipoSubsistemaDto) {

		TipoSubsistema tipoSubsistema = this.modelMapperUtils.map(tipoSubsistemaDto, TipoSubsistema.class);
	    
		//La siguiente sentencia se comenta y se sustituye por otra sentencia temporal hasta que
		//nos llegue desde el front el sistema al que está asociado el subsistema
		//tipoSubsistema.setTipoSistema(this.modelMapperUtils.map(tipoSubsistemaDto.getTipoSistema(), TipoSistema.class));
		tipoSubsistema.setTipoSistema(this.modelMapperUtils.map(tiposSistemasService.read((long)1),TipoSistema.class));
		
		tipoSubsistema = this.tipoSubsistemasRepository.save(tipoSubsistema);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoSubsistema.getNombre());		
		
		return this.modelMapperUtils.map(tipoSubsistema, TipoSubsistemaDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto update(TipoSubsistemaDto tipoSubsistemaDto) {

		final TipoSubsistema tipoSubsistema = this.modelMapperUtils.map(tipoSubsistemaDto, TipoSubsistema.class);
		
		TipoSubsistema tipoSubsistemaBBDD = this.tipoSubsistemasRepository.findById(tipoSubsistema.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoSubsistemaBBDD.setNombre(tipoSubsistemaDto.getNombre());
		tipoSubsistemaBBDD.setDescripcion(tipoSubsistemaDto.getDescripcion());
		tipoSubsistemaBBDD.setInterfazOperacion(tipoSubsistemaDto.getInterfazOperacion());
		//La siguiente sentencia se comenta y se sustituye por 2 sentencias temporales hasta que
		//nos llegue desde el front el sistema al que está asociado el subsistema
		//tipoSubsistemaBBDD.setTipoSistema(this.modelMapperUtils.map(tipoSubsistemaDto.getTipoSistema(),TipoSistema.class));
		TipoSistema tipoSistemaBBDD = this.modelMapperUtils.map(tiposSistemasService.read((long)1),TipoSistema.class);
		tipoSubsistemaBBDD.setTipoSistema(tipoSistemaBBDD);
		tipoSubsistemaBBDD = this.tipoSubsistemasRepository.save(tipoSubsistemaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoSubsistemaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoSubsistemaBBDD, TipoSubsistemaDto.class);
	}
}
