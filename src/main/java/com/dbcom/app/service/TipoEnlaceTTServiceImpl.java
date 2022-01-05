package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoEnlaceTTRepository;
import com.dbcom.app.model.dto.TipoEnlaceTTDto;
import com.dbcom.app.model.entity.TipoEnlaceTT;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de enlace TT
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoEnlaceTTServiceImpl implements TipoEnlaceTTService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoEnlaceTTRepository tipoEnlaceTTRepository;
	
	@Autowired
	public TipoEnlaceTTServiceImpl(ModelMapperUtils modelMapper,
									TipoEnlaceTTRepository tipoEnlaceTTRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoEnlaceTTRepository = tipoEnlaceTTRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEnlaceTTDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoEnlaceTTDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoEnlaceTT tipoEnlaceTTBBDD = this.tipoEnlaceTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoEnlaceTTRepository.delete(tipoEnlaceTTBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoEnlaceTTDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoEnlaceTT> tiposEnlaceTT = this.tipoEnlaceTTRepository.findAll();

		final List<TipoEnlaceTTDto> tiposEnlaceTTDto = new ArrayList<>(tiposEnlaceTT.size());		
		tiposEnlaceTT.forEach(tipoEnlaceTT -> tiposEnlaceTTDto.add(this.modelMapperUtils.map(tipoEnlaceTT, TipoEnlaceTTDto.class)));
		
		return tiposEnlaceTTDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEnlaceTTDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoEnlaceTT tipoEnlaceTT = this.tipoEnlaceTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoEnlaceTT, TipoEnlaceTTDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEnlaceTTDto saveUpdate(final TipoEnlaceTTDto tipoEnlaceTTDto) {		
		
		TipoEnlaceTT tipoEnlaceTT = this.modelMapperUtils.map(tipoEnlaceTTDto, TipoEnlaceTT.class);
		
		return this.modelMapperUtils.map(this.tipoEnlaceTTRepository.save(tipoEnlaceTT), TipoEnlaceTTDto.class);
	}
}
