package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoSubsistemaRepository;
import com.dbcom.app.model.dto.TipoInterfazOperacionDto;
import com.dbcom.app.model.dto.TipoSistemaLiteDto;
import com.dbcom.app.model.dto.TipoSubsistemaDto;
import com.dbcom.app.model.entity.TipoInterfazOperacion;
import com.dbcom.app.model.entity.TipoSistema;
import com.dbcom.app.model.entity.TipoSubsistema;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TipoSubsistemaServiceImpl implements TipoSubsistemaService {

	private final TipoSubsistemaRepository tipoSubsistemasRepository;
	private final TipoSistemaService tiposSistemasService;
	private final TipoInterfazOperacionService tiposInterfazOperacionService;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public TipoSubsistemaServiceImpl(TipoSubsistemaRepository tipoSubsistemasRepository,
			TipoSistemaService tiposSistemasService,
			TipoInterfazOperacionService tiposInterfazOperacionService,
			ModelMapperUtils modelMapper) {
		this.tipoSubsistemasRepository = tipoSubsistemasRepository;
		this.tiposSistemasService = tiposSistemasService;
		this.tiposInterfazOperacionService = tiposInterfazOperacionService;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		return TipoSubsistemaDto.builder().tiposInterfazOperacion(this.modelMapperUtils.mapAll2List(tiposInterfazOperacionService.readAll(),TipoInterfazOperacionDto.class))
										  .tiposSistemasDisponibles(this.modelMapperUtils.mapAll2List(tiposSistemasService.readAll(),TipoSistemaLiteDto.class))
										  .build();
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
		result.setTiposInterfazOperacion(this.modelMapperUtils.mapAll2List(tiposInterfazOperacionService.readAll(),TipoInterfazOperacionDto.class));
		result.setTiposSistemasDisponibles(this.modelMapperUtils.mapAll2List(tiposSistemasService.readAll(),TipoSistemaLiteDto.class));
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSubsistemaDto save(TipoSubsistemaDto tipoSubsistemaDto) {

		TipoSubsistema tipoSubsistema = this.modelMapperUtils.map(tipoSubsistemaDto, TipoSubsistema.class);
		tipoSubsistema.setTipoInterfazOperacion(this.modelMapperUtils.map(tipoSubsistemaDto.getTipoInterfazOperacion(), TipoInterfazOperacion.class));
	    tipoSubsistema.setTipoSistema(this.modelMapperUtils.map(tipoSubsistemaDto.getTipoSistema(), TipoSistema.class));
		
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
		tipoSubsistemaBBDD.setTipoInterfazOperacion(this.modelMapperUtils.map(tipoSubsistemaDto.getTipoInterfazOperacion(),TipoInterfazOperacion.class));
		tipoSubsistemaBBDD.setTipoSistema(this.modelMapperUtils.map(tipoSubsistemaDto.getTipoSistema(),TipoSistema.class));
		tipoSubsistemaBBDD = this.tipoSubsistemasRepository.save(tipoSubsistemaBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoSubsistemaBBDD.getId());
		
		return this.modelMapperUtils.map(tipoSubsistemaBBDD, TipoSubsistemaDto.class);
	}
}
