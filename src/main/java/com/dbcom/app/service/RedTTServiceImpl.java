package com.dbcom.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.FotografiaRepository;
import com.dbcom.app.model.dao.RedTTRepository;
import com.dbcom.app.model.dto.FotografiaDto;
import com.dbcom.app.model.dto.RedTTDto;
import com.dbcom.app.model.dto.RedTTLiteDto;
import com.dbcom.app.model.entity.Fotografia;
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
	private final FotografiaRepository fotografiaRepository;
	
	@Autowired
	public RedTTServiceImpl(RedTTRepository redTTRepository,
			FotografiaRepository fotografiaRepository,
			ModelMapperUtils modelMapper) {
		this.redTTRepository = redTTRepository;
		this.modelMapperUtils = modelMapper;
		this.fotografiaRepository = fotografiaRepository;
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
	public List<RedTTLiteDto> readAll() {

		log.info(LoggerConstants.LOG_READALL);

		return this.modelMapperUtils.mapAll2List(this.redTTRepository.findAll(), RedTTLiteDto.class);
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
	public RedTTDto save(RedTTDto redTTDto) {

		RedTT redTT = this.modelMapperUtils.map(redTTDto, RedTT.class);
		
		return this.modelMapperUtils.map(this.redTTRepository.save(redTT), RedTTDto.class);
	}

	@Override
	public RedTTDto update(RedTTDto redTTDto) {
		
		RedTT redTT = redTTRepository.findById(redTTDto.getId())
				.map(redTTBD -> {
					
					redTTBD.setTipoTopologia(this.modelMapperUtils.map(redTTDto.getTipoTopologia(), TipoTopologia.class));
					redTTBD.setNombre(redTTDto.getNombre());
					redTTBD.setObservaciones(redTTDto.getObservaciones());

					return redTTBD;
					
				}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		return this.modelMapperUtils.map(this.redTTRepository.save(redTT), RedTTDto.class);
	}

	@Override
	public Optional<FotografiaDto> getFotografia(Long idFotografia){
		return this.fotografiaRepository.findById(idFotografia).map(fotografia -> this.modelMapperUtils.map(fotografia, FotografiaDto.class));
	}

	@Override
	public Optional<Long> insertFotografia(Long idRedTT, FotografiaDto fotografiaDto) {
		
		RedTT redTT = redTTRepository.findById(idRedTT)
		.map(redTTBD -> {
			
			redTTBD.getFotografias().add(this.modelMapperUtils.map(fotografiaDto, Fotografia.class));
			
			return redTTBD;
			
		}).orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		redTT = this.redTTRepository.save(redTT);
		
		return redTT.getFotografias()
						   .stream()
						   .filter(fotografia -> fotografia.getNombre().equals(fotografiaDto.getNombre()) && fotografia.getDescripcion().equals(fotografiaDto.getDescripcion()))
						   .findFirst()
						   .map(fotografia -> fotografia.getId());
	}

	@Override
	public Long deleteFotografia(Long idFotografia) {

		this.fotografiaRepository.deleteById(idFotografia);
		
		return idFotografia;
	}

	@Override
	public void setAllAttributesRedTTDto(RedTTDto redTTDto) {

		RedTTDto redTTDtoBD = read(redTTDto.getId());
		redTTDto.setFotografias(redTTDtoBD.getFotografias());
		
	}
	
}
