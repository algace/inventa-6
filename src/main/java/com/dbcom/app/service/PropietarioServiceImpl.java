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
import com.dbcom.app.model.dao.PropietarioRepository;
import com.dbcom.app.model.dto.PropietarioDto;
import com.dbcom.app.model.entity.Propietario;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PropietarioServiceImpl implements PropietarioService{
	
	private final ModelMapperUtils modelMapperUtils;
	private final PropietarioRepository propietarioRepository;
	
	@Autowired
	public PropietarioServiceImpl(ModelMapperUtils modelMapper,
									   PropietarioRepository propietarioRepository) {
		this.modelMapperUtils = modelMapper;
		this.propietarioRepository = propietarioRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropietarioDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new PropietarioDto();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Short id) {			
		
		final Propietario propietarioBBDD = this.propietarioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.propietarioRepository.delete(propietarioBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropietarioDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<Propietario> propietarios = this.propietarioRepository.findAll();

		final List<PropietarioDto> propietariosDto = new ArrayList<>(propietarios.size());		
		propietarios.forEach(propietario -> propietariosDto.add(this.modelMapperUtils.map(propietario, PropietarioDto.class)));
		
		return propietariosDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropietarioDto read(final Short id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final Propietario propietario = this.propietarioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));

		return this.modelMapperUtils.map(propietario, PropietarioDto.class); 

	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropietarioDto saveUpdate(final PropietarioDto propietarioDto) {		
		
		Propietario propietario = this.modelMapperUtils.map(propietarioDto, Propietario.class);
		
		return this.modelMapperUtils.map(this.propietarioRepository.save(propietario), PropietarioDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PropietarioDto> getPropietariosConValorPorDefecto() {

		Propietario primerPropietario;
		Propietario propietarioPorDefecto = propietarioRepository.findByPropietario(ApplicationConstants.PROPIETARIO_POR_DEFECTO);
		List<Propietario> listaPropietarios = new ArrayList<Propietario>();
		
		if (propietarioPorDefecto != null) {
			primerPropietario = propietarioPorDefecto;
		} else {
			primerPropietario = Propietario.builder().build();
		}
		listaPropietarios.add(primerPropietario);
		listaPropietarios.addAll(propietarioRepository.findAll()
				                                          .stream()
				                                          .filter(prop -> !prop.equals(primerPropietario))
				                                          .collect(Collectors.toList()));
		
		return this.modelMapperUtils.mapAll2List(listaPropietarios, PropietarioDto.class);
	}
}
