package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TiposSubsistemasRepository;
import com.dbcom.app.model.dto.TipoSistemaDto;
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
		//Cada subsistema se pasa a su correspondiente Dto
		//A su vez, cada sistema contenido en un subsistema se pasa a su correspondiente Dto y 
		//este Dto de tipo sistema se le debe asignar al Dto de tipo subsistema
		for (TipoSubsistema tipoSubsistema : tiposSubsistemas) {
			TipoSistemaDto tipoSistemaDto = this.modelMapperUtils.map(tipoSubsistema.getTipoSistema(), TipoSistemaDto.class);
			TipoSubsistemaDto tipoSubsistemaDto = this.modelMapperUtils.map(tipoSubsistema, TipoSubsistemaDto.class);
			tipoSubsistemaDto.setTipoSistemaDto(tipoSistemaDto);
			tiposSubsistemasDto.add(tipoSubsistemaDto);
		}
		
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
		
		//Se obtiene el Dto para el tipo de sistema asociado al tipo de subsistema 
		//y se asocia al dto del tipo de subsistema
		TipoSistema tipoSistema = tipoSubsistema.getTipoSistema();
		result.setTipoSistemaDto(this.modelMapperUtils.map(tipoSistema, TipoSistemaDto.class));
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto save(TipoSubsistemaDto tipoSubsistemaDto) {

		TipoSubsistema tipoSubsistema = this.modelMapperUtils.map(tipoSubsistemaDto, TipoSubsistema.class);
	    
		//Esta sentencia es temporal hasta que se implemente la asociación de un subsistema a un sistema en el front
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
		//Las siguientes lineas se comentan y se sustituye por otra sentencia temporal hasta que
		//nos llegue desde el front el subsistema asociado a un sistema
		//final TipoSistema tipoSistema = this.modelMapperUtils.map(tipoSubsistemaDto.getTipoSistemaDto(), TipoSistema.class);
		//TipoSistema tipoSistemaBBDD = this.modelMapperUtils.map(this.tiposSistemasService.read(tipoSistema.getId()), TipoSistema.class);
		TipoSistema tipoSistemaBBDD = this.modelMapperUtils.map(tiposSistemasService.read((long)1),TipoSistema.class);
		tipoSubsistemaBBDD.setTipoSistema(tipoSistemaBBDD);
		tipoSubsistemaBBDD = this.tipoSubsistemasRepository.save(tipoSubsistemaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoSubsistemaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoSubsistemaBBDD, TipoSubsistemaDto.class);
	}
}
