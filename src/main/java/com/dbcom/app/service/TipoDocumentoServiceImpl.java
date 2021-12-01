package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoDocumentoRepository;
import com.dbcom.app.model.dto.TipoDocumentoDto;
import com.dbcom.app.model.entity.TipoDocumento;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de documento
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoDocumentoServiceImpl implements TipoDocumentoService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoDocumentoRepository tipoDocumentoRepository;
	
	@Autowired
	public TipoDocumentoServiceImpl(ModelMapperUtils modelMapper,
									TipoDocumentoRepository tipoDocumentoRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoDocumentoRepository = tipoDocumentoRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoDocumentoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoDocumentoDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Short id) {			
		
		final TipoDocumento tipoDocumentoBBDD = this.tipoDocumentoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoDocumentoRepository.delete(tipoDocumentoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoDocumentoDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoDocumento> tiposDocumento = this.tipoDocumentoRepository.findAll();

		final List<TipoDocumentoDto> tiposDocumentoDto = new ArrayList<>(tiposDocumento.size());		
		tiposDocumento.forEach(tipoDocumento -> tiposDocumentoDto.add(this.modelMapperUtils.map(tipoDocumento, TipoDocumentoDto.class)));
		
		return tiposDocumentoDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public TipoDocumentoDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoDocumento tipoDocumento = this.tipoDocumentoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoDocumento, TipoDocumentoDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public TipoDocumentoDto save(final TipoDocumentoDto tipoDocumentoDto) {		
		
		TipoDocumento tipoDocumento = this.modelMapperUtils.map(tipoDocumentoDto, TipoDocumento.class);
	    
		tipoDocumento = this.tipoDocumentoRepository.save(tipoDocumento);	
		
		log.info(LoggerConstants.LOG_CREATE, tipoDocumento.getNombre());		
		
		return this.modelMapperUtils.map(tipoDocumento, TipoDocumentoDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoDocumentoDto update(final TipoDocumentoDto tipoDocumentoDto) {		
		
		final TipoDocumento tipoDocumento = this.modelMapperUtils.map(tipoDocumentoDto, TipoDocumento.class);
		
		TipoDocumento tipoDocumentoBBDD = this.tipoDocumentoRepository.findById(tipoDocumento.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		tipoDocumentoBBDD.setNombre(tipoDocumentoDto.getNombre());
		tipoDocumentoBBDD.setDescripcion(tipoDocumentoDto.getDescripcion());
		tipoDocumentoBBDD = this.tipoDocumentoRepository.save(tipoDocumentoBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, tipoDocumentoBBDD.getId());
		
		return this.modelMapperUtils.map(tipoDocumentoBBDD, TipoDocumentoDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Page<TipoDocumentoDto> readAll(Pageable pageable) {
	      final Page<TipoDocumento> tiposDocumento = this.tipoDocumentoRepository.findAll(pageable);	      
	      return this.modelMapperUtils.mapAll2Page(tiposDocumento, pageable, 
	    		  tiposDocumento.getTotalElements(), TipoDocumentoDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Page<TipoDocumentoDto> readContainingNombre(final String texto, final Pageable pageable) {
	      final Page<TipoDocumento> tiposDocumento = this.tipoDocumentoRepository.findByNombreContaining(texto, pageable);	
	      return this.modelMapperUtils.mapAll2Page(tiposDocumento, pageable, 
	    		  tiposDocumento.getTotalElements(), TipoDocumentoDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Page<TipoDocumentoDto> readContainingDescripcion(final String texto, final Pageable pageable) {
		final Page<TipoDocumento> tiposDocumento = this.tipoDocumentoRepository.findByDescripcionContaining(texto, pageable);	
		return this.modelMapperUtils.mapAll2Page(tiposDocumento, pageable, 
				tiposDocumento.getTotalElements(), TipoDocumentoDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public Page<TipoDocumentoDto> readContainings(final String texto, final Pageable pageable) {
		final Page<TipoDocumento> tiposDocumento = this.tipoDocumentoRepository.findByNombreContainingOrDescripcionContaining(texto, texto, pageable);	
		return this.modelMapperUtils.mapAll2Page(tiposDocumento, pageable, 
				tiposDocumento.getTotalElements(), TipoDocumentoDto.class);
	}

}
