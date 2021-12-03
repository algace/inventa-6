package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dto.AplicacionSWDto;
import com.dbcom.app.model.dto.AplicacionSWLiteDto;
import com.dbcom.app.model.dto.VersionSWDto;
import com.dbcom.app.model.entity.AplicacionSW;
import com.dbcom.app.model.entity.Equipamiento;
import com.dbcom.app.model.entity.VersionSW;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para aplicaciones
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class AplicacionSWServiceImpl implements AplicacionSWService {
	
	private final AplicacionSWRepository aplicacionSWRepository;
	private final EquipamientoService equipamientoService;
	private final VersionSWService versionSWService;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public AplicacionSWServiceImpl(AplicacionSWRepository aplicacionSWRepository, 
			EquipamientoService equipamientoService, VersionSWService versionSWService,
			ModelMapperUtils modelMapper) {
		this.aplicacionSWRepository = aplicacionSWRepository;
		this.equipamientoService = equipamientoService;
		this.versionSWService = versionSWService;
		this.modelMapperUtils = modelMapper;
	}

	/**
	 * {@inheritDoc}
	 */
	public AplicacionSWDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return AplicacionSWDto.builder()
				              .versionesSWNoIncluidas(versionSWService.readAll())
				              .equipamientosNoIncluidos(equipamientoService.readAll())
				              .build();
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
		final AplicacionSW aplicacionSWBBDD = this.aplicacionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Eliminamos la aplicación de las versiones que la contengan
		aplicacionSWBBDD.getVersionesSW().forEach(versionSW -> versionSW.getAplicacionesSW().remove(aplicacionSWBBDD));
		
		this.aplicacionSWRepository.deleteById(id);
		
		log.info(LoggerConstants.LOG_DELETE, id);
		
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AplicacionSWLiteDto> readAll() {
		
		final List<AplicacionSW> aplicacionesSW = this.aplicacionSWRepository.findAll();
		
		final List<AplicacionSWLiteDto> aplicacionesSWLiteDto = new ArrayList<>(aplicacionesSW.size());		
		aplicacionesSW.forEach(aplicacionSW -> aplicacionesSWLiteDto.add(this.modelMapperUtils.map(aplicacionSW, AplicacionSWLiteDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return aplicacionesSWLiteDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AplicacionSWDto read(final Long id) {	
		
		final AplicacionSW aplicacionSW = this.aplicacionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final AplicacionSWDto result = this.modelMapperUtils.map(aplicacionSW, AplicacionSWDto.class);
		
		// Insertamos los equipamientos y las versiones que no tiene por si las quiere añadir
		result.setVersionesSWNoIncluidas(this.versionSWService.readNotContains(id));
		result.setEquipamientosNoIncluidos(this.equipamientoService.readNotContains(id));
		log.info(LoggerConstants.LOG_READ);		

		return result; 

	}
		
	/**
	 * {@inheritDoc}
	 */
	public AplicacionSWDto save(final AplicacionSWDto aplicacionSWDto) {		
		
		AplicacionSW aplicacionSW = this.modelMapperUtils.map(aplicacionSWDto, AplicacionSW.class);
		aplicacionSW = this.aplicacionSWRepository.save(aplicacionSW);	
		
		log.info(LoggerConstants.LOG_CREATE, aplicacionSW.getNombre());		
		
		return this.modelMapperUtils.map(aplicacionSW, AplicacionSWDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public AplicacionSWDto update(final AplicacionSWDto aplicacionSWDto) {		

		final AplicacionSW aplicacionSW = this.modelMapperUtils.map(aplicacionSWDto, AplicacionSW.class);
		
		AplicacionSW aplicacionSWBBDD = this.aplicacionSWRepository.findById(aplicacionSW.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		aplicacionSWBBDD.setNombre(aplicacionSWDto.getNombre());
		aplicacionSWBBDD.setArchivo(aplicacionSWDto.getArchivo());
		aplicacionSWBBDD.setFecha(aplicacionSWDto.getFecha());
		aplicacionSWBBDD.setHora(aplicacionSWDto.getHora());
		aplicacionSWBBDD.setEquipamientos(this.modelMapperUtils.mapAll2Set(aplicacionSWDto.getEquipamientos(), Equipamiento.class));
		aplicacionSWBBDD.setVersionesSW(this.modelMapperUtils.mapAll2Set(aplicacionSWDto.getVersionesSW(), VersionSW.class));
		aplicacionSWBBDD = this.aplicacionSWRepository.save(aplicacionSWBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, aplicacionSWBBDD.getId());
		
		return this.modelMapperUtils.map(aplicacionSWBBDD, AplicacionSWDto.class);
	}
	
}
