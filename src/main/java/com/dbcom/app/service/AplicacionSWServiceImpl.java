package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	@Override
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
		
		final List<AplicacionSW> aplicacionesSW = this.aplicacionSWRepository.findAll();
		
		final List<AplicacionSWLiteDto> aplicacionesSWLiteDto = new ArrayList<>(aplicacionesSW.size());		
		aplicacionesSW.forEach(aplicacionSW -> aplicacionesSWLiteDto.add(this.modelMapperUtils.map(aplicacionSW, AplicacionSWLiteDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return aplicacionesSWLiteDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
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
		
		//se recupera la lista de versiones tratando correctamente las que ya hayan sido seleccionadas
		List<VersionSWLiteDto> allVersiones = versionSWService.readAllLite();
		aplicacionDto.setVersionesSW(aplicacionSWDtoBd.getVersionesSW());
		aplicacionDto.setVersionesSWNoIncluidas(listVersionesNoSeleccionadas(allVersiones, aplicacionSWDtoBd.getVersionesSW()));
		
		//se recupera la lista de equipamientos tratando correctamente los que ya hayan sido seleccionados
		List<EquipamientoLiteDto> allEquipamientos = equipamientoService.readAllLite();
		aplicacionDto.setEquipamientos(aplicacionSWDtoBd.getEquipamientos());
		aplicacionDto.setEquipamientosNoIncluidos(listEquipamientosNoSeleccionados(allEquipamientos, aplicacionSWDtoBd.getEquipamientos()));
		
	}
	

	private List<VersionSWLiteDto> listVersionesNoSeleccionadas(List<VersionSWLiteDto> allVersiones,
			List<VersionSWLiteDto> versionesSeleccionadas) {
		
		versionesSeleccionadas.stream().forEach(version -> {
			List<VersionSWLiteDto> list = allVersiones.stream().filter(vSW -> version.getId() == vSW.getId()).collect(Collectors.toList());
			if(!list.isEmpty()){
				Optional<VersionSWLiteDto> optVersiones = list.stream().findFirst();
				if(optVersiones.isPresent()) {
					allVersiones.remove(optVersiones.get());
				}
			};
		});
		
		return allVersiones;
	}

	private List<EquipamientoLiteDto> listEquipamientosNoSeleccionados(List<EquipamientoLiteDto> allEquipamientos,
			List<EquipamientoLiteDto> equipamientosSeleccionados) {
		
		equipamientosSeleccionados.stream().forEach(equipamiento -> {
			List<EquipamientoLiteDto> list = allEquipamientos.stream().filter(eQ -> equipamiento.getId() == eQ.getId()).collect(Collectors.toList());
			if(!list.isEmpty()){
				Optional<EquipamientoLiteDto> optEquipamientos = list.stream().findFirst();
				if(optEquipamientos.isPresent()) {
					allEquipamientos.remove(optEquipamientos.get());
				}
			};
		});
		
		return allEquipamientos;
	}
	
}
