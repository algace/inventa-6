package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ApplicationConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.TipoTopologiaRepository;
import com.dbcom.app.model.dto.TipoTopologiaDto;
import com.dbcom.app.model.entity.TipoTopologia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para tipo de topología
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public final class TipoTopologiaServiceImpl implements TipoTopologiaService {
	
	private final ModelMapperUtils modelMapperUtils;
	private final TipoTopologiaRepository tipoTopologiaRepository;
	
	@Autowired
	public TipoTopologiaServiceImpl(ModelMapperUtils modelMapper,
									TipoTopologiaRepository tipoTopologiaRepository) {
		this.modelMapperUtils = modelMapper;
		this.tipoTopologiaRepository = tipoTopologiaRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoTopologiaDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new TipoTopologiaDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final TipoTopologia tipoTopologiaBBDD = this.tipoTopologiaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.tipoTopologiaRepository.delete(tipoTopologiaBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoTopologiaDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<TipoTopologia> tiposTopologia = this.tipoTopologiaRepository.findAll();

		final List<TipoTopologiaDto> tiposTopologiaDto = new ArrayList<>(tiposTopologia.size());		
		tiposTopologia.forEach(tipoTopologia -> tiposTopologiaDto.add(this.modelMapperUtils.map(tipoTopologia, TipoTopologiaDto.class)));
		
		return tiposTopologiaDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoTopologiaDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final TipoTopologia tipoTopologia = this.tipoTopologiaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(tipoTopologia, TipoTopologiaDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoTopologiaDto saveUpdate(final TipoTopologiaDto tipoTopologiaDto) {		
		
		TipoTopologia tipoTopologia = this.modelMapperUtils.map(tipoTopologiaDto, TipoTopologia.class);	
		
		return this.modelMapperUtils.map(this.tipoTopologiaRepository.save(tipoTopologia), TipoTopologiaDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoTopologiaDto> getTiposTopologiasConValorPorDefecto() {

		TipoTopologia primerTipoTopologia;
		TipoTopologia tipoTopologiaPorDefecto = tipoTopologiaRepository.findByNombre(ApplicationConstants.TIPO_TOPOLOGIA_POR_DEFECTO);
		List<TipoTopologia> listaTiposTopologia = new ArrayList<TipoTopologia>();
		
		if (tipoTopologiaPorDefecto != null) {
			primerTipoTopologia = tipoTopologiaPorDefecto;
		} else {
			primerTipoTopologia = TipoTopologia.builder().build();
		}
		listaTiposTopologia.add(primerTipoTopologia);
		listaTiposTopologia.addAll(tipoTopologiaRepository.findAll()
				                                          .stream()
				                                          .filter(tipoTopologia -> !tipoTopologia.equals(primerTipoTopologia))
				                                          .collect(Collectors.toList()));
		
		return this.modelMapperUtils.mapAll2List(listaTiposTopologia, TipoTopologiaDto.class);
	}
	
}
