package com.dbcom.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.RedTTRepository;
import com.dbcom.app.model.dto.RedTTDto;
import com.dbcom.app.model.entity.RedTT;
import com.dbcom.app.model.entity.TipoTopologia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para aplicaciones
 * 
 * @author neoris
 */
@Service
@Slf4j
public class RedTTServiceImpl implements RedTTService {

	private final RedTTRepository redTTRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public RedTTServiceImpl(RedTTRepository redTTRepository,
			ModelMapperUtils modelMapper) {
		this.redTTRepository = redTTRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RedTTDto create() {

		log.info(LoggerConstants.LOG_CREATE);
		
		return RedTTDto.builder().build();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {
		
		final RedTT redTTBBDD = this.redTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.redTTRepository.delete(redTTBBDD);		
		
		log.info(LoggerConstants.LOG_DELETE, id);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RedTTDto> readAll() {

		log.info(LoggerConstants.LOG_READALL);

		return this.modelMapperUtils.mapAll2List(this.redTTRepository.findAll(), RedTTDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RedTTDto read(Long id) {

		final RedTT redTT = this.redTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final RedTTDto result = this.modelMapperUtils.map(redTT, RedTTDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RedTTDto saveUpdate(RedTTDto redTTDto) {

		RedTT redTT = this.modelMapperUtils.map(redTTDto, RedTT.class);
		redTT.setTipoTopologia(this.modelMapperUtils.map(redTTDto.getTipoTopologia(), TipoTopologia.class));
		
		return this.modelMapperUtils.map(this.redTTRepository.save(redTT), RedTTDto.class);
	}
	
}
