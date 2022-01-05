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
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.dto.EquipamientoLiteDto;
import com.dbcom.app.model.dto.FotografiaDto;
import com.dbcom.app.model.entity.AplicacionSW;
import com.dbcom.app.model.entity.Equipamiento;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jose.vallve
 * 
 * Lógica para equipamientos
 */
@Service
@Slf4j
public class EquipamientoServiceImpl implements EquipamientoService {
	
	private final AplicacionSWRepository aplicacionSWRepository;
	private final EquipamientoRepository equipamientoRepository;
	private final ModelMapperUtils  modelMapperUtils;
	private final TipoDocumentoService tipoDocumentoService;
	private final TipoSistemaService tipoSistemaService;
	private final TipoSubsistemaService tipoSubsistemaService;

	@Autowired
	public EquipamientoServiceImpl(ModelMapperUtils modelMapper,
			AplicacionSWRepository aplicacionSWRepository,
			EquipamientoRepository equipamientoRepository,
			TipoDocumentoService tipoDocumentoService,
			TipoSistemaService tipoSistemaService,
			TipoSubsistemaService tipoSubsistemaService) {
		this.modelMapperUtils = modelMapper;
		this.aplicacionSWRepository = aplicacionSWRepository;
		this.equipamientoRepository = equipamientoRepository;
		this.tipoDocumentoService = tipoDocumentoService;
		this.tipoSistemaService = tipoSistemaService;
		this.tipoSubsistemaService = tipoSubsistemaService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EquipamientoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return EquipamientoDto.builder()
				.tiposDocumento(tipoDocumentoService.readAll())
				.tiposSistemasDisponibles(tipoSistemaService.readAll())
				.tiposSubsistemasDisponibles(tipoSubsistemaService.readAll())
				.build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long id) {			
		
		final Equipamiento equipamientoBBDD = this.equipamientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
			
		this.equipamientoRepository.delete(equipamientoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EquipamientoLiteDto> readAll() {
		
		final List<Equipamiento> equipamientos = this.equipamientoRepository.findAll();
		
		final List<EquipamientoLiteDto> equipamientosDto = new ArrayList<>(equipamientos.size());		
		equipamientos.forEach(equipamiento -> equipamientosDto.add(this.modelMapperUtils.map(equipamiento, EquipamientoLiteDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return equipamientosDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EquipamientoLiteDto> readAllLite() {
		
		final List<Equipamiento> equipamientos = this.equipamientoRepository.findAll();
		
		final List<EquipamientoLiteDto> equipamientosLiteDto = new ArrayList<>(equipamientos.size());		
		equipamientos.forEach(equipamiento -> equipamientosLiteDto.add(this.modelMapperUtils.map(equipamiento, EquipamientoLiteDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return equipamientosLiteDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EquipamientoDto read(final Long id) {		
		
		final Equipamiento equipamiento = this.equipamientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final EquipamientoDto result = this.modelMapperUtils.map(equipamiento, EquipamientoDto.class);
		result.setTiposDocumento(tipoDocumentoService.readAll());
		result.setTiposSistemasDisponibles(tipoSistemaService.readAll());
		result.setTiposSubsistemasDisponibles(tipoSubsistemaService.readAll());
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<EquipamientoDto> readNotContains(Long id) {

		final AplicacionSW aplicacionSW = this.aplicacionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);		

		final List<Equipamiento> equipamiento = this.equipamientoRepository.findAll();
		equipamiento.removeAll(aplicacionSW.getEquipamientos());
		
		return this.modelMapperUtils.mapAll2List(equipamiento, EquipamientoDto.class);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EquipamientoDto saveUpdate(final EquipamientoDto equipamientoDto) {	
		
		equipamientoDto.setDocumentos(filterListDocumentos(equipamientoDto.getDocumentos()));
		equipamientoDto.setFotografias(filterListFotografias(equipamientoDto.getFotografias()));
		
		Equipamiento equipamiento = this.modelMapperUtils.map(equipamientoDto, Equipamiento.class);
		
		return this.modelMapperUtils.map(this.equipamientoRepository.save(equipamiento), EquipamientoDto.class);
	}
	
	/**
	 * Procesa una lista de objetos DocumentosDto proveniente del front y elimina de esta lista los 
	 * objetos que tienen un id null
     * @param lista de objetos DocumentosDto provenientes del front 
     * @return lista de objetos DocumentosDto filtrada 
	 */
	private List<DocumentoDto> filterListDocumentos(List<DocumentoDto> listDocumentos) {
		
		return listDocumentos.stream()
                            .filter(documento -> !Objects.isNull(documento.getNombre()))
                            .collect(Collectors.toList());
	}
	
	/**
	 * Procesa una lista de objetos FotografiaDto proveniente del front y elimina de esta lista los 
	 * objetos que tienen un id null
     * @param lista de objetos FotografiaDto provenientes del front 
     * @return lista de objetos FotografiaDto filtrada 
	 */
	private List<FotografiaDto> filterListFotografias(List<FotografiaDto> listFotografias) {
		
		return listFotografias.stream()
                            .filter(fotografia -> !Objects.isNull(fotografia.getNombre()))
                            .collect(Collectors.toList());
	}

	@Override
	public EquipamientoDto setAllAttributesEquipamientoDto(EquipamientoDto equipamientoDto) {
		
		equipamientoDto.setDocumentos(filterListDocumentos(equipamientoDto.getDocumentos()));
		equipamientoDto.setFotografias(filterListFotografias(equipamientoDto.getFotografias()));
		equipamientoDto.setTiposDocumento(tipoDocumentoService.readAll());
		equipamientoDto.setTiposSistemasDisponibles(tipoSistemaService.readAll());
		equipamientoDto.setTiposSubsistemasDisponibles(tipoSubsistemaService.readAll());
		
		return equipamientoDto;
	}

}
 