package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoCapacidadEnlaceTTRepository;
import com.dbcom.app.model.dto.TipoCapacidadEnlaceTTDto;
import com.dbcom.app.model.entity.TipoCapacidadEnlaceTT;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de capacidad enlace TT
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoCapacidadEnlaceTTServiceImpl implements TipoCapacidadEnlaceTTService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoCapacidadEnlaceTTRepository tipoCapacidadEnlaceTTRepository;
	
	@Autowired
	public TipoCapacidadEnlaceTTServiceImpl(ModelMapperUtils modelMapper,
									        TipoCapacidadEnlaceTTRepository tipoCapacidadEnlaceTTRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoCapacidadEnlaceTTRepository = tipoCapacidadEnlaceTTRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoCapacidadEnlaceTTDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoCapacidadEnlaceTTDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTTBBDD = this.tipoCapacidadEnlaceTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoCapacidadEnlaceTTRepository.delete(tipoCapacidadEnlaceTTBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoCapacidadEnlaceTTDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoCapacidadEnlaceTT> tiposCapacidadEnlaceTT = this.tipoCapacidadEnlaceTTRepository.findAll();

		final List<TipoCapacidadEnlaceTTDto> tiposCapacidadEnlaceTTDto = new ArrayList<>(tiposCapacidadEnlaceTT.size());		
		tiposCapacidadEnlaceTT.forEach(tipoCapacidadEnlaceTT -> tiposCapacidadEnlaceTTDto.add(this.modelMapperUtils.map(tipoCapacidadEnlaceTT, TipoCapacidadEnlaceTTDto.class)));
		
		return tiposCapacidadEnlaceTTDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoCapacidadEnlaceTTDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT = this.tipoCapacidadEnlaceTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoCapacidadEnlaceTT, TipoCapacidadEnlaceTTDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoCapacidadEnlaceTTDto saveUpdate(final TipoCapacidadEnlaceTTDto tipoCapacidadEnlaceTTDto) {		
		
		TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT = this.modelMapperUtils.map(tipoCapacidadEnlaceTTDto, TipoCapacidadEnlaceTT.class);	
		
		return this.modelMapperUtils.map(this.tipoCapacidadEnlaceTTRepository.save(tipoCapacidadEnlaceTT), TipoCapacidadEnlaceTTDto.class);
	}
}
