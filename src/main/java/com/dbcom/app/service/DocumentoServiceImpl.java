package com.dbcom.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.DocumentoRepository;
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dao.TipoDocumentoRepository;
import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.entity.Documento;
import com.dbcom.app.model.entity.Equipamiento;
import com.dbcom.app.model.entity.TipoDocumento;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para documento
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class DocumentoServiceImpl implements DocumentoService {
	
	private final DocumentoRepository documentoRepository;
	private final EquipamientoRepository equipamientoRepository;
	private final TipoDocumentoRepository tipoDocumentoRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public DocumentoServiceImpl(DocumentoRepository documentoRepository,
			EquipamientoRepository equipamientoRepository,
			TipoDocumentoRepository tipoDocumentoRepository,
			ModelMapperUtils modelMapper) {
		this.modelMapperUtils = modelMapper;
		this.documentoRepository = documentoRepository;
		this.tipoDocumentoRepository = tipoDocumentoRepository;
		this.equipamientoRepository = equipamientoRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public DocumentoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new DocumentoDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
		final Documento documentoBBDD = this.documentoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.documentoRepository.delete(documentoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DocumentoDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<Documento> documentos = this.documentoRepository.findAll();

		final List<DocumentoDto> documentosDto = new ArrayList<>(documentos.size());		
		documentos.forEach(documento -> documentosDto.add(this.modelMapperUtils.map(documento, DocumentoDto.class)));
		
		return documentosDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public DocumentoDto read(final Long id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final Documento documento = this.documentoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(documento, DocumentoDto.class); 

	}
		
	/**
	 * {@inheritDoc}
	 */
	public DocumentoDto save(final DocumentoDto documentoDto) {		
		
		Documento documento = this.modelMapperUtils.map(documentoDto, Documento.class);
	    
		documento = this.documentoRepository.save(documento);	
		
		log.info(LoggerConstants.LOG_CREATE, documento.getNombre());		
		
		return this.modelMapperUtils.map(documento, DocumentoDto.class);
	}


	/**
	 * {@inheritDoc}
	 */
	public DocumentoDto update(final DocumentoDto documentoDto) {		
		
		final Documento documento = this.modelMapperUtils.map(documentoDto, Documento.class);
		
		Documento documentoBBDD = this.documentoRepository.findById(documento.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		documentoBBDD.setNombre(documentoDto.getNombre());
		documentoBBDD = this.documentoRepository.save(documentoBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, documentoBBDD.getId());
		
		return this.modelMapperUtils.map(documentoBBDD, DocumentoDto.class);
	}
	
	public void upload(final Long idEquipamiento, final Short idTipoDocumento, 
					   final String descripcionDocumento, final MultipartFile fichero) {
		
		final Equipamiento equipamiento = this.equipamientoRepository.findById(idEquipamiento)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		final TipoDocumento tipoDocumento = this.tipoDocumentoRepository.findById(idTipoDocumento)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		if (!fichero.getOriginalFilename().isEmpty()) {
			try {
				final Documento documento = Documento.builder()
												.nombre(fichero.getOriginalFilename())
												.descripcion(descripcionDocumento)
												.contenido(fichero.getBytes())
												.tipo(fichero.getContentType())
												.tipoDocumento(tipoDocumento)
												.equipamiento(equipamiento)
												.build();
				this.documentoRepository.save(documento);
	
			} catch (IOException e) {
				log.error(ExceptionConstants.DAO_EXCEPTION, fichero.getOriginalFilename());
			}
			
		}
	}
	
}
