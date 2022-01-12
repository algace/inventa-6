package com.dbcom.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dto.AplicacionSWDto;
import com.dbcom.app.model.dto.AplicacionSWLiteDto;
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
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public AplicacionSWServiceImpl(AplicacionSWRepository aplicacionSWRepository, 
			ModelMapperUtils modelMapper) {
		this.aplicacionSWRepository = aplicacionSWRepository;
		this.modelMapperUtils = modelMapper;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AplicacionSWDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return AplicacionSWDto.builder().build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long id) {			
		
		this.aplicacionSWRepository.deleteById(id);
		
		log.info(LoggerConstants.LOG_DELETE, id);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AplicacionSWLiteDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		return this.modelMapperUtils.mapAll2List(this.aplicacionSWRepository.findAll(), AplicacionSWLiteDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AplicacionSWDto read(final Long id) {	
		
		final AplicacionSW aplicacionSW = this.aplicacionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final AplicacionSWDto result = this.modelMapperUtils.map(aplicacionSW, AplicacionSWDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 

	}
		
	/**
	 * {@inheritDoc}
	 */
	public AplicacionSWDto save(final AplicacionSWDto aplicacionSWDto) {
		
		AplicacionSW aplicacionSW = this.modelMapperUtils.map(aplicacionSWDto, AplicacionSW.class);
		
		return this.modelMapperUtils.map(this.aplicacionSWRepository.save(aplicacionSW), AplicacionSWDto.class);
	}
	
	@Override
	public AplicacionSWDto update(final AplicacionSWDto aplicacionSWDto) {
		
		AplicacionSW aplicacion = aplicacionSWRepository.findById(aplicacionSWDto.getId())
		.map(aplicacionBD -> {
			
			aplicacionBD.setArchivo(aplicacionSWDto.getArchivo());
			aplicacionBD.setFecha(aplicacionSWDto.getFecha());
			aplicacionBD.setHora(aplicacionSWDto.getHora());
			aplicacionBD.setNombre(aplicacionSWDto.getNombre());
			
			return aplicacionBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		
		return this.modelMapperUtils.map(this.aplicacionSWRepository.save(aplicacion), AplicacionSWDto.class);
	}
	
	@Override
	public Long insertVersionSW(Long idAplicacionSW, Long idVersionSW) {
		
		AplicacionSW aplicacion = aplicacionSWRepository.findById(idAplicacionSW)
		.map(aplicacionBD -> {
			
			aplicacionBD.getVersionesSW().add(VersionSW.builder().id(idVersionSW).build());
			
			return aplicacionBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.aplicacionSWRepository.save(aplicacion);
		
		return idVersionSW;
	}
	
	@Override
	public Long deleteVersionSW(Long idAplicacionSW, Long idVersionSW) {
		
		AplicacionSW aplicacion = aplicacionSWRepository.findById(idAplicacionSW)
		.map(aplicacionBD -> {
			
			aplicacionBD.setVersionesSW(aplicacionBD.getVersionesSW().stream().filter(versionSW -> versionSW.getId() != idVersionSW).collect(Collectors.toSet()));
			
			return aplicacionBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.aplicacionSWRepository.save(aplicacion);
		
		
		return idVersionSW;
	}
	
	@Override
	public Long insertEquipamiento(Long idAplicacionSW, Long idEquipamiento) {
		
		AplicacionSW aplicacion = aplicacionSWRepository.findById(idAplicacionSW)
		.map(aplicacionBD -> {
			
			aplicacionBD.getEquipamientos().add(Equipamiento.builder().id(idEquipamiento).build());
				
			return aplicacionBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.aplicacionSWRepository.save(aplicacion);
		
		return idEquipamiento;
	}
	
	
	@Override
	public Long deleteEquipamiento(Long idAplicacionSW, Long idEquipamiento) {
		
		AplicacionSW aplicacion = aplicacionSWRepository.findById(idAplicacionSW)
		.map(aplicacionBD -> {
			
			aplicacionBD.setEquipamientos(aplicacionBD.getEquipamientos().stream().filter(equipamiento -> equipamiento.getId() != idEquipamiento).collect(Collectors.toSet()));
			
			return aplicacionBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.aplicacionSWRepository.save(aplicacion);
		
		return idEquipamiento;
	}
	
	@Override
	public void setAllAttributesListEquipamientoDto(AplicacionSWDto aplicacionDto){
		
		AplicacionSWDto aplicacionSWDtoBd = read(aplicacionDto.getId());
		aplicacionDto.setVersionesSW(aplicacionSWDtoBd.getVersionesSW());
		aplicacionDto.setEquipamientos(aplicacionSWDtoBd.getEquipamientos());
		
	}
	
}
