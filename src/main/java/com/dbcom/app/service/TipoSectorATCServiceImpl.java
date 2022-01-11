package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoSectorATCRepository;
import com.dbcom.app.model.dto.TipoSectorATCDto;
import com.dbcom.app.model.dto.TipoSectorATCLiteDto;
import com.dbcom.app.model.entity.TipoSectorATC;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de sector ATC
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoSectorATCServiceImpl implements TipoSectorATCService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoSectorATCRepository tipoSectorATCRepository;
	
	@Autowired
	public TipoSectorATCServiceImpl(ModelMapperUtils modelMapper,
									TipoSectorATCRepository tipoSectorATCRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoSectorATCRepository = tipoSectorATCRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSectorATCDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoSectorATCDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoSectorATC tipoSectorATCBBDD = this.tipoSectorATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoSectorATCRepository.delete(tipoSectorATCBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoSectorATCLiteDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoSectorATC> tiposSectorATC = this.tipoSectorATCRepository.findAll();

		final List<TipoSectorATCLiteDto> tiposSectorATCDto = new ArrayList<>(tiposSectorATC.size());		
		tiposSectorATC.forEach(tipoSectorATC -> tiposSectorATCDto.add(this.modelMapperUtils.map(tipoSectorATC, TipoSectorATCLiteDto.class)));
		
		return tiposSectorATCDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSectorATCDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoSectorATC tipoSectorATC = this.tipoSectorATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoSectorATC, TipoSectorATCDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSectorATCDto saveUpdate(final TipoSectorATCDto tipoSectorATCDto) {		
		
		TipoSectorATC tipoSectorATC = this.modelMapperUtils.map(tipoSectorATCDto, TipoSectorATC.class);	
		
		return this.modelMapperUtils.map(this.tipoSectorATCRepository.save(tipoSectorATC), TipoSectorATCDto.class);
	}
}
