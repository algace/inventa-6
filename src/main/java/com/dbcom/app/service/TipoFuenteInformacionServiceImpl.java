package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoFuenteInformacionRepository;
import com.dbcom.app.model.dto.TipoFuenteInformacionDto;
import com.dbcom.app.model.dto.TipoFuenteInformacionLiteDto;
import com.dbcom.app.model.entity.TipoFuenteInformacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de fuente de información
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoFuenteInformacionServiceImpl implements TipoFuenteInformacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoFuenteInformacionRepository tipoFuenteInformacionRepository;
	
	@Autowired
	public TipoFuenteInformacionServiceImpl(ModelMapperUtils modelMapper,
									TipoFuenteInformacionRepository tipoFuenteInformacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoFuenteInformacionRepository = tipoFuenteInformacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoFuenteInformacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoFuenteInformacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoFuenteInformacion tipoFuenteInformacionBBDD = this.tipoFuenteInformacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoFuenteInformacionRepository.delete(tipoFuenteInformacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoFuenteInformacionLiteDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoFuenteInformacion> tiposFuenteInformacion = this.tipoFuenteInformacionRepository.findAll();

		final List<TipoFuenteInformacionLiteDto> tiposFuenteInformacionDto = new ArrayList<>(tiposFuenteInformacion.size());		
		tiposFuenteInformacion.forEach(tipoFuenteInformacion -> tiposFuenteInformacionDto.add(this.modelMapperUtils.map(tipoFuenteInformacion, TipoFuenteInformacionLiteDto.class)));
		
		return tiposFuenteInformacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoFuenteInformacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoFuenteInformacion tipoFuenteInformacion = this.tipoFuenteInformacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoFuenteInformacion, TipoFuenteInformacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoFuenteInformacionDto saveUpdate(final TipoFuenteInformacionDto tipoFuenteInformacionDto) {		
		
		TipoFuenteInformacion tipoFuenteInformacion = this.modelMapperUtils.map(tipoFuenteInformacionDto, TipoFuenteInformacion.class);	
		
		return this.modelMapperUtils.map(this.tipoFuenteInformacionRepository.save(tipoFuenteInformacion), TipoFuenteInformacionDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoFuenteInformacionLiteDto> getTiposFuenteInformacionConValorPorDefecto(String nombreFuenteInformacion) {

		TipoFuenteInformacion primerTipoFuenteInformacion;
		TipoFuenteInformacion tipoFuenteInformacionPorDefecto = tipoFuenteInformacionRepository.findByNombre(nombreFuenteInformacion);
		List<TipoFuenteInformacion> listaTiposFuenteInformacion = new ArrayList<TipoFuenteInformacion>();
		
		if (tipoFuenteInformacionPorDefecto != null) {
			primerTipoFuenteInformacion = tipoFuenteInformacionPorDefecto;
		} else {
			primerTipoFuenteInformacion = TipoFuenteInformacion.builder().build();
		}
		listaTiposFuenteInformacion.add(primerTipoFuenteInformacion);
		listaTiposFuenteInformacion.addAll(tipoFuenteInformacionRepository.findAll()
				                                          .stream()
				                                          .filter(tipoFuenteInformacion -> !tipoFuenteInformacion.equals(primerTipoFuenteInformacion))
				                                          .collect(Collectors.toList()));
		
		return this.modelMapperUtils.mapAll2List(listaTiposFuenteInformacion, TipoFuenteInformacionLiteDto.class);
	}
	
}
