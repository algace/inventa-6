package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.RegionMantenimientoRepository;
import com.dbcom.app.model.dto.RegionMantenimientoDto;
import com.dbcom.app.model.entity.RegionMantenimiento;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionMantenimientoServiceImpl implements RegionMantenimientoService {

	private final RegionMantenimientoRepository regionMantenimientoRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public RegionMantenimientoServiceImpl(RegionMantenimientoRepository regionesMantenimientoRepository,
			ModelMapperUtils modelMapper) {
		this.regionMantenimientoRepository = regionesMantenimientoRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionMantenimientoDto create() {
		
		log.info(LoggerConstants.LOG_CREATE);
		return RegionMantenimientoDto.builder().build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		final RegionMantenimiento regionMantenimiento = this.regionMantenimientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.regionMantenimientoRepository.delete(regionMantenimiento);		
		
		log.info(LoggerConstants.LOG_DELETE, id);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionMantenimientoDto> readAll() {

		final List<RegionMantenimiento> regionesMantenimiento = this.regionMantenimientoRepository.findAll();
		
		final List<RegionMantenimientoDto> regionesMantenimientoDto = new ArrayList<>(regionesMantenimiento.size());		
		regionesMantenimiento.forEach(regionMantenimiento -> regionesMantenimientoDto.add(this.modelMapperUtils.map(regionMantenimiento, RegionMantenimientoDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return regionesMantenimientoDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionMantenimientoDto read(Long id) {

		final RegionMantenimiento regionMantenimiento = this.regionMantenimientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final RegionMantenimientoDto result = this.modelMapperUtils.map(regionMantenimiento, RegionMantenimientoDto.class);
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionMantenimientoDto save(RegionMantenimientoDto regionMantenimientoDto) {

		RegionMantenimiento regionMantenimiento = this.modelMapperUtils.map(regionMantenimientoDto, RegionMantenimiento.class);
	    
		regionMantenimiento = this.regionMantenimientoRepository.save(regionMantenimiento);	
		
		log.info(LoggerConstants.LOG_CREATE, regionMantenimiento.getNombre());		
		
		return this.modelMapperUtils.map(regionMantenimiento, RegionMantenimientoDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionMantenimientoDto update(RegionMantenimientoDto regionMantenimientoDto) {

		final RegionMantenimiento regionMantenimiento = this.modelMapperUtils.map(regionMantenimientoDto, RegionMantenimiento.class);
		
		RegionMantenimiento regionMantenimientoBBDD = this.regionMantenimientoRepository.findById(regionMantenimiento.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		regionMantenimientoBBDD.setNombre(regionMantenimientoDto.getNombre());
		regionMantenimientoBBDD.setDescripcion(regionMantenimientoDto.getDescripcion());
		regionMantenimientoBBDD = this.regionMantenimientoRepository.save(regionMantenimientoBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, regionMantenimientoBBDD.getId());
		
		return this.modelMapperUtils.map(regionMantenimientoBBDD, RegionMantenimientoDto.class);
	}

}
