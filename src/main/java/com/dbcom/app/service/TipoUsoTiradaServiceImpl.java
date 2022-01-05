package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoUsoTiradaRepository;
import com.dbcom.app.model.dto.TipoUsoTiradaDto;
import com.dbcom.app.model.entity.TipoUsoTirada;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de uso tirada
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoUsoTiradaServiceImpl implements TipoUsoTiradaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoUsoTiradaRepository tipoUsoTiradaRepository;
	
	@Autowired
	public TipoUsoTiradaServiceImpl(ModelMapperUtils modelMapper,
									TipoUsoTiradaRepository tipoUsoTiradaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoUsoTiradaRepository = tipoUsoTiradaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsoTiradaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoUsoTiradaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoUsoTirada tipoUsoTiradaBBDD = this.tipoUsoTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoUsoTiradaRepository.delete(tipoUsoTiradaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoUsoTiradaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoUsoTirada> tiposUsoTirada = this.tipoUsoTiradaRepository.findAll();

		final List<TipoUsoTiradaDto> tiposUsoTiradaDto = new ArrayList<>(tiposUsoTirada.size());		
		tiposUsoTirada.forEach(tipoUsoTirada -> tiposUsoTiradaDto.add(this.modelMapperUtils.map(tipoUsoTirada, TipoUsoTiradaDto.class)));
		
		return tiposUsoTiradaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsoTiradaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoUsoTirada tipoUsoTirada = this.tipoUsoTiradaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoUsoTirada, TipoUsoTiradaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsoTiradaDto saveUpdate(final TipoUsoTiradaDto tipoUsoTiradaDto) {		
		
		TipoUsoTirada tipoUsoTirada = this.modelMapperUtils.map(tipoUsoTiradaDto, TipoUsoTirada.class);	
		
		return this.modelMapperUtils.map(this.tipoUsoTiradaRepository.save(tipoUsoTirada), TipoUsoTiradaDto.class);
	}	
}
