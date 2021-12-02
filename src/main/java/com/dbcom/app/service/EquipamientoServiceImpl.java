package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.dto.EquipamientoDto;
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
public final class EquipamientoServiceImpl implements EquipamientoService {
	
	private EquipamientoRepository equipamientoRepository;
	private final ModelMapperUtils  modelMapperUtils;
	private final TipoDocumentoService tipoDocumentoService;

	@Autowired
	public EquipamientoServiceImpl(ModelMapperUtils modelMapper,
			EquipamientoRepository equipamientoRepository,
			TipoDocumentoService tipoDocumentoService) {
		this.modelMapperUtils = modelMapper;
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
		
		// Eliminamos el equipamiento de las aplicaciones que lo contengan
		equipamientoBBDD.getAplicacionesSW().forEach(aplicacion -> aplicacion.getEquipamientos().remove(equipamientoBBDD));
		
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
	public EquipamientoDto save(final EquipamientoDto equipamientoDto) {		
		
		Equipamiento equipamiento = this.modelMapperUtils.map(equipamientoDto, Equipamiento.class);
	    
		equipamiento = this.equipamientoRepository.save(equipamiento);	
		
		log.info(LoggerConstants.LOG_CREATE, equipamiento.getNombre());		
		
		return this.modelMapperUtils.map(equipamiento, EquipamientoDto.class);
	}


	/**
	 * {@inheritDoc}
	 */
	public EquipamientoDto update(final EquipamientoDto equipamientoDto) {		
		
		Equipamiento equipamientoBBDD = this.equipamientoRepository.findById(equipamientoDto.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		equipamientoBBDD.setNombre(equipamientoDto.getNombre());
		equipamientoBBDD.setSistema(equipamientoDto.getSistema());
		equipamientoBBDD.setSubsistema(equipamientoDto.getSubsistema());		
		equipamientoBBDD.setMarca(equipamientoDto.getMarca());
		equipamientoBBDD.setModelo(equipamientoDto.getModelo());
		equipamientoBBDD.setCaracteristicas(equipamientoDto.getCaracteristicas());
		equipamientoBBDD.setEntradas(equipamientoDto.getEntradas());
		equipamientoBBDD.setSalidas(equipamientoDto.getSalidas());
		equipamientoBBDD.setGanancia(equipamientoDto.getGanancia());
		equipamientoBBDD.setPerdida(equipamientoDto.getPerdida());
		equipamientoBBDD.setNumeroPuertos(equipamientoDto.getNumeroPuertos());
		equipamientoBBDD.setApertura(equipamientoDto.getApertura());
		equipamientoBBDD.setDiametro(equipamientoDto.getDiametro());
		equipamientoBBDD.setDescripcion(equipamientoDto.getDescripcion());
		equipamientoBBDD.getDocumentos().removeAll(this.getDocumentosEliminar(equipamientoBBDD, equipamientoDto));
		equipamientoBBDD = this.equipamientoRepository.save(equipamientoBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, equipamientoBBDD.getId());
		
		return this.modelMapperUtils.map(equipamientoBBDD, EquipamientoDto.class);
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
 