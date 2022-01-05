package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoSistemaLocalRepository;
import com.dbcom.app.model.dto.TipoSistemaLocalDto;
import com.dbcom.app.model.entity.TipoSistemaLocal;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de sistema local
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoSistemaLocalServiceImpl implements TipoSistemaLocalService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoSistemaLocalRepository tipoSistemaLocalRepository;
	
	@Autowired
	public TipoSistemaLocalServiceImpl(ModelMapperUtils modelMapper,
									   TipoSistemaLocalRepository tipoSistemaLocalRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoSistemaLocalRepository = tipoSistemaLocalRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaLocalDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoSistemaLocalDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoSistemaLocal tipoSistemaLocalBBDD = this.tipoSistemaLocalRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoSistemaLocalRepository.delete(tipoSistemaLocalBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoSistemaLocalDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoSistemaLocal> tiposSistemaLocal = this.tipoSistemaLocalRepository.findAll();

		final List<TipoSistemaLocalDto> tiposSistemaLocalDto = new ArrayList<>(tiposSistemaLocal.size());		
		tiposSistemaLocal.forEach(tipoSistemaLocal -> tiposSistemaLocalDto.add(this.modelMapperUtils.map(tipoSistemaLocal, TipoSistemaLocalDto.class)));
		
		return tiposSistemaLocalDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaLocalDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoSistemaLocal tipoSistemaLocal = this.tipoSistemaLocalRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoSistemaLocal, TipoSistemaLocalDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoSistemaLocalDto saveUpdate(final TipoSistemaLocalDto tipoSistemaLocalDto) {		
		
		TipoSistemaLocal tipoSistemaLocal = this.modelMapperUtils.map(tipoSistemaLocalDto, TipoSistemaLocal.class);	
		
		return this.modelMapperUtils.map(this.tipoSistemaLocalRepository.save(tipoSistemaLocal), TipoSistemaLocalDto.class);
	}
}
