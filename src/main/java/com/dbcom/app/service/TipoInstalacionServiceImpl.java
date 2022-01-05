package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoInstalacionRepository;
import com.dbcom.app.model.dto.TipoInstalacionDto;
import com.dbcom.app.model.entity.TipoInstalacion;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de instalación
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoInstalacionServiceImpl implements TipoInstalacionService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoInstalacionRepository tipoInstalacionRepository;
	
	@Autowired
	public TipoInstalacionServiceImpl(ModelMapperUtils modelMapper,
									  TipoInstalacionRepository tipoInstalacionRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoInstalacionRepository = tipoInstalacionRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoInstalacionDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoInstalacionDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoInstalacion tipoInstalacionBBDD = this.tipoInstalacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoInstalacionRepository.delete(tipoInstalacionBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoInstalacionDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoInstalacion> tiposInstalacion = this.tipoInstalacionRepository.findAll();

		final List<TipoInstalacionDto> tiposInstalacionDto = new ArrayList<>(tiposInstalacion.size());		
		tiposInstalacion.forEach(tipoInstalacion -> tiposInstalacionDto.add(this.modelMapperUtils.map(tipoInstalacion, TipoInstalacionDto.class)));
		
		return tiposInstalacionDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoInstalacionDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoInstalacion tipoInstalacion = this.tipoInstalacionRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoInstalacion, TipoInstalacionDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoInstalacionDto saveUpdate(final TipoInstalacionDto tipoInstalacionDto) {		
		
		TipoInstalacion tipoInstalacion = this.modelMapperUtils.map(tipoInstalacionDto, TipoInstalacion.class);
		
		return this.modelMapperUtils.map(this.tipoInstalacionRepository.save(tipoInstalacion), TipoInstalacionDto.class);
	}
}
