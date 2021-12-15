package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dto.AplicacionSWDto;
import com.dbcom.app.model.dto.AplicacionSWLiteDto;
import com.dbcom.app.model.dto.EquipamientoLiteDto;
import com.dbcom.app.model.dto.VersionSWLiteDto;
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
				              .versionesSWNoIncluidas(this.modelMapperUtils.mapAll2List(versionSWService.readAll(),VersionSWLiteDto.class))
				              .equipamientosNoIncluidos(this.modelMapperUtils.mapAll2List(equipamientoService.readAll(),EquipamientoLiteDto.class))
				              .build();
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
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
		result.setVersionesSWNoIncluidas(this.modelMapperUtils.mapAll2List(this.versionSWService.readNotContains(id),VersionSWLiteDto.class));
		result.setEquipamientosNoIncluidos(this.modelMapperUtils.mapAll2List(this.equipamientoService.readNotContains(id),EquipamientoLiteDto.class));
		log.info(LoggerConstants.LOG_READ);		

		return result; 

	}
		
	/**
	 * {@inheritDoc}
	 */
	public AplicacionSWDto save(final AplicacionSWDto aplicacionSWDto) {		
		
		aplicacionSWDto.setEquipamientos(filterListEquipamientos(aplicacionSWDto.getEquipamientos()));
		aplicacionSWDto.setVersionesSW(filterListVersionesSW(aplicacionSWDto.getVersionesSW()));
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
		
		aplicacionSWDto.setEquipamientos(filterListEquipamientos(aplicacionSWDto.getEquipamientos()));
		aplicacionSWDto.setVersionesSW(filterListVersionesSW(aplicacionSWDto.getVersionesSW()));
		aplicacionSWBBDD.setEquipamientos(this.modelMapperUtils.mapAll2Set(aplicacionSWDto.getEquipamientos(), Equipamiento.class));
		aplicacionSWBBDD.setVersionesSW(this.modelMapperUtils.mapAll2Set(aplicacionSWDto.getVersionesSW(), VersionSW.class));

		aplicacionSWBBDD = this.aplicacionSWRepository.save(aplicacionSWBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, aplicacionSWBBDD.getId());
		
		return this.modelMapperUtils.map(aplicacionSWBBDD, AplicacionSWDto.class);
	}
	
	/**
	 * Procesa una lista de objetos VersionSWLiteDto proveniente del front y elimina de esta lista los 
	 * objetos que tienen un id null
     * @param lista de objetos VersionSWLiteDto provenientes del front 
     * @return lista de objetos VersionSWLiteDto filtrada 
	 */
	private List<VersionSWLiteDto> filterListVersionesSW(List<VersionSWLiteDto> listVersiones) {
	
		return listVersiones.stream()
                            .filter(version -> !Objects.isNull(version.getId()))
                            .collect(Collectors.toList());
	}
	
	/**
	 * Procesa una lista de objetos EquipamientoLiteDto proveniente del front y elimina de esta lista los 
	 * objetos que tienen un id null
     * @param lista de objetos EquipamientoLiteDto provenientes del front 
     * @return lista de objetos EquipamientoLiteDto filtrada 
	 */
	private List<EquipamientoLiteDto> filterListEquipamientos(List<EquipamientoLiteDto> listEquipamientos) {
		
		return listEquipamientos.stream()
                            .filter(equipamiento -> !Objects.isNull(equipamiento.getId()))
                            .collect(Collectors.toList());
	}
}
