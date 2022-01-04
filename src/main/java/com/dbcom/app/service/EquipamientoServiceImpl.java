package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.dto.EquipamientoLiteDto;
import com.dbcom.app.model.entity.AplicacionSW;
import com.dbcom.app.model.entity.Documento;
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
	private EquipamientoRepository equipamientoRepository;
	private final ModelMapperUtils  modelMapperUtils;
	private final TipoDocumentoService tipoDocumentoService;

	@Autowired
	public EquipamientoServiceImpl(ModelMapperUtils modelMapper,
			AplicacionSWRepository aplicacionSWRepository,
			EquipamientoRepository equipamientoRepository,
			TipoDocumentoService tipoDocumentoService) {
		this.modelMapperUtils = modelMapper;
		this.aplicacionSWRepository = aplicacionSWRepository;
		this.equipamientoRepository = equipamientoRepository;
		this.tipoDocumentoService = tipoDocumentoService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EquipamientoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return EquipamientoDto.builder().tiposDocumento(tipoDocumentoService.readAll()).build();
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
		final Equipamiento equipamientoBBDD = this.equipamientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
			
		this.equipamientoRepository.delete(equipamientoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<EquipamientoDto> readAll() {
		
		final List<Equipamiento> equipamientos = this.equipamientoRepository.findAll();
		
		final List<EquipamientoDto> equipamientosDto = new ArrayList<>(equipamientos.size());		
		equipamientos.forEach(equipamiento -> equipamientosDto.add(this.modelMapperUtils.map(equipamiento, EquipamientoDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return equipamientosDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
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
	public EquipamientoDto read(final Long id) {		
		
		final Equipamiento equipamiento = this.equipamientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final EquipamientoDto result = this.modelMapperUtils.map(equipamiento, EquipamientoDto.class);
		result.setTiposDocumento(tipoDocumentoService.readAll());
		
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
	@Transactional
	public EquipamientoDto saveUpdate(final EquipamientoDto equipamientoDto) {		
		
		Equipamiento equipamiento = this.modelMapperUtils.map(equipamientoDto, Equipamiento.class);
	    		
		return this.modelMapperUtils.map(this.equipamientoRepository.save(equipamiento), EquipamientoDto.class);
	}
	
	/**
	 * Obtenemos los documentos persistidos que se quieren eliminar desde la vista
	 * @param equipamientoBBDD Equipamiento almacenado
	 * @param equipamientoDto Equipamiendo de la vista
	 * @return Conjunto de documentos a eliminar
	 */
	private Set<Documento> getDocumentosEliminar(final Equipamiento equipamientoBBDD, final EquipamientoDto equipamientoDto) {
		
		// Documentos que no se han eliminado en la vista
		final List<DocumentoDto> documentosDtoConservar = equipamientoDto.getDocumentos()
																	.stream()
																	.filter(documento -> null != documento.getId())
																	.collect(Collectors.toList());
		
		final Set<Documento> documentosConservar = this.modelMapperUtils.mapAll2Set(documentosDtoConservar, Documento.class);
		
		// Documentos que se eliminaron en la vista
		return equipamientoBBDD.getDocumentos().stream()
										.filter(documento -> (documentosConservar.stream()
															.filter(documentoConservar -> documentoConservar
																	.getId().longValue() == documento.getId().longValue())
															.count()) < 1) 
										.collect(Collectors.toSet());
	}

}
 