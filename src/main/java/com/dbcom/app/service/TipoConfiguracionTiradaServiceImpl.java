package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoConfiguracionTiradaRepository;
import com.dbcom.app.model.dto.TipoConfiguracionTiradaDto;
import com.dbcom.app.model.entity.TipoConfiguracionTirada;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de configuración tirada
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoConfiguracionTiradaServiceImpl implements TipoConfiguracionTiradaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoConfiguracionTiradaRepository tipoConfiguracionTiradaRepository;
	
	@Autowired
	public TipoConfiguracionTiradaServiceImpl(ModelMapperUtils modelMapper,
									   TipoConfiguracionTiradaRepository tipoConfiguracionTiradaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoConfiguracionTiradaRepository = tipoConfiguracionTiradaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoConfiguracionTiradaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoConfiguracionTiradaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoConfiguracionTirada tipoConfiguracionTiradaBBDD = this.tipoConfiguracionTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoConfiguracionTiradaRepository.delete(tipoConfiguracionTiradaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoConfiguracionTiradaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoConfiguracionTirada> tiposConfiguracionTirada = this.tipoConfiguracionTiradaRepository.findAll();

		final List<TipoConfiguracionTiradaDto> tiposConfiguracionTiradaDto = new ArrayList<>(tiposConfiguracionTirada.size());		
		tiposConfiguracionTirada.forEach(tipoConfiguracionTirada -> tiposConfiguracionTiradaDto.add(this.modelMapperUtils.map(tipoConfiguracionTirada, TipoConfiguracionTiradaDto.class)));
		
		return tiposConfiguracionTiradaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoConfiguracionTiradaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoConfiguracionTirada tipoConfiguracionTirada = this.tipoConfiguracionTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoConfiguracionTirada, TipoConfiguracionTiradaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoConfiguracionTiradaDto saveUpdate(final TipoConfiguracionTiradaDto tipoConfiguracionTiradaDto) {		
		
		TipoConfiguracionTirada tipoConfiguracionTirada = this.modelMapperUtils.map(tipoConfiguracionTiradaDto, TipoConfiguracionTirada.class);		
		
		return this.modelMapperUtils.map(this.tipoConfiguracionTiradaRepository.save(tipoConfiguracionTirada), TipoConfiguracionTiradaDto.class);
	}
}
