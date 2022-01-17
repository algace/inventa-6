package com.dbcom.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dao.DocumentoRepository;
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dao.FotografiaRepository;
import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.dto.EquipamientoLiteDto;
import com.dbcom.app.model.dto.FotografiaDto;
import com.dbcom.app.model.entity.AplicacionSW;
import com.dbcom.app.model.entity.Documento;
import com.dbcom.app.model.entity.Equipamiento;
import com.dbcom.app.model.entity.Fotografia;
import com.dbcom.app.model.entity.TipoSistema;
import com.dbcom.app.model.entity.TipoSubsistema;
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
	private final FotografiaRepository fotografiaRepository;
	private final DocumentoRepository documentoRepository;

	@Autowired
	public EquipamientoServiceImpl(ModelMapperUtils modelMapper,
			AplicacionSWRepository aplicacionSWRepository,
			FotografiaRepository fotografiaRepository,
			DocumentoRepository documentoRepository,
			EquipamientoRepository equipamientoRepository) {
		this.modelMapperUtils = modelMapper;
		this.aplicacionSWRepository = aplicacionSWRepository;
		this.equipamientoRepository = equipamientoRepository;
		this.fotografiaRepository = fotografiaRepository;
		this.documentoRepository = documentoRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EquipamientoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return EquipamientoDto.builder().build();
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
		
		log.info(LoggerConstants.LOG_READALL);
		
		return this.modelMapperUtils.mapAll2List(this.equipamientoRepository.findAll(), EquipamientoLiteDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EquipamientoDto read(final Long id) {		
		
		final Equipamiento equipamiento = this.equipamientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final EquipamientoDto result = this.modelMapperUtils.map(equipamiento, EquipamientoDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<EquipamientoLiteDto> readNotContains(Long id) {

		final AplicacionSW aplicacionSW = this.aplicacionSWRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);		

		final List<Equipamiento> equipamiento = this.equipamientoRepository.findAll();
		equipamiento.removeAll(aplicacionSW.getEquipamientos());
		
		return this.modelMapperUtils.mapAll2List(equipamiento, EquipamientoLiteDto.class);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EquipamientoDto save(final EquipamientoDto equipamientoDto) {	
		
		Equipamiento equipamiento = this.modelMapperUtils.map(equipamientoDto, Equipamiento.class);
		
		return this.modelMapperUtils.map(this.equipamientoRepository.save(equipamiento), EquipamientoDto.class);
	}
	
	@Override
	public EquipamientoDto update(final EquipamientoDto equipamientoDto) {	
		
		
		Equipamiento equipamiento = equipamientoRepository.findById(equipamientoDto.getId())
				.map(equipamientoBD -> {
					
					equipamientoBD.setNombre(equipamientoDto.getNombre());
					equipamientoBD.setApertura(equipamientoDto.getApertura());
					equipamientoBD.setCaracteristicas(equipamientoDto.getCaracteristicas());
					equipamientoBD.setDescripcion(equipamientoDto.getDescripcion());
					equipamientoBD.setDiametro(equipamientoDto.getDiametro());
					equipamientoBD.setEntradas(equipamientoDto.getEntradas());
					equipamientoBD.setGanancia(equipamientoDto.getGanancia());
					equipamientoBD.setMarca(equipamientoDto.getMarca());
					equipamientoBD.setModelo(equipamientoDto.getModelo());
					equipamientoBD.setNumeroPuertos(equipamientoDto.getNumeroPuertos());
					equipamientoBD.setPerdida(equipamientoDto.getPerdida());
					equipamientoBD.setSalidas(equipamientoDto.getSalidas());
					equipamientoBD.setTipoSistema(this.modelMapperUtils.map(equipamientoDto.getTipoSistema(), TipoSistema.class));
					equipamientoBD.setTipoSubsistema(this.modelMapperUtils.map(equipamientoDto.getTipoSubsistema(), TipoSubsistema.class));
					
					return equipamientoBD;
					
				}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		return this.modelMapperUtils.map(this.equipamientoRepository.save(equipamiento), EquipamientoDto.class);
	}
	
	@Override
	public Optional<DocumentoDto> getDocumento(Long idDocumento){
		return this.documentoRepository.findById(idDocumento).map(documento -> this.modelMapperUtils.map(documento, DocumentoDto.class));
	}
	
	@Override
	public Optional<Long> insertDocumento(Long idEquipamiento, DocumentoDto documentoDto) {
		
		Equipamiento equipamiento = equipamientoRepository.findById(idEquipamiento)
		.map(equipamientoBD -> {
			
			equipamientoBD.getDocumentos().add(this.modelMapperUtils.map(documentoDto, Documento.class));
			
			return equipamientoBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		equipamiento = this.equipamientoRepository.save(equipamiento);
		
		return equipamiento.getDocumentos()
				   .stream()
				   .filter(documento -> documento.getNombre().equals(documentoDto.getNombre()) && 
						   					documento.getDescripcion().equals(documentoDto.getDescripcion()) && 
						   						documento.getTipoDocumento().getId() == documentoDto.getTipoDocumento().getId())
				   .findFirst()
				   .map(documento -> documento.getId());
	}

	@Override
	public Long deleteDocumento(Long idDocumento) {
		
		this.documentoRepository.deleteById(idDocumento);
		
		return idDocumento;
	}
	
	@Override
	public Optional<FotografiaDto> getFotografia(Long idFotografia){
		return this.fotografiaRepository.findById(idFotografia).map(fotografia -> this.modelMapperUtils.map(fotografia, FotografiaDto.class));
	}

	@Override
	public Optional<Long> insertFotografia(Long idEquipamiento, FotografiaDto fotografiaDto) {
		
		Equipamiento equipamiento = equipamientoRepository.findById(idEquipamiento)
		.map(equipamientoBD -> {
			
			equipamientoBD.getFotografias().add(this.modelMapperUtils.map(fotografiaDto, Fotografia.class));
			
			return equipamientoBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		equipamiento = this.equipamientoRepository.save(equipamiento);
		
		return equipamiento.getFotografias()
						   .stream()
						   .filter(fotografia -> fotografia.getNombre().equals(fotografiaDto.getNombre()) && fotografia.getDescripcion().equals(fotografiaDto.getDescripcion()))
						   .findFirst()
						   .map(fotografia -> fotografia.getId());
	}

	@Override
	public Long deleteFotografia(Long idFotografia) {

		this.fotografiaRepository.deleteById(idFotografia);
		
		return idFotografia;
	}

	@Override
	public void setAllAttributesEquipamientoDto(EquipamientoDto equipamientoDto) {
		
		EquipamientoDto equipamientoDtoBd = read(equipamientoDto.getId());
		equipamientoDto.setDocumentos(equipamientoDtoBd.getDocumentos());
		equipamientoDto.setFotografias(equipamientoDtoBd.getFotografias());
		
	}

}
 