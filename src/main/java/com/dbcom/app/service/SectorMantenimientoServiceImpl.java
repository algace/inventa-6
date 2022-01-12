package com.dbcom.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ApplicationConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.RegionMantenimientoRepository;
import com.dbcom.app.model.dao.SectorMantenimientoRepository;
import com.dbcom.app.model.dto.RegionMantenimientoLiteDto;
import com.dbcom.app.model.dto.SectorMantenimientoDto;
import com.dbcom.app.model.entity.RegionMantenimiento;
import com.dbcom.app.model.entity.SectorMantenimiento;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SectorMantenimientoServiceImpl implements SectorMantenimientoService {

	private final SectorMantenimientoRepository sectorMantenimientoRepository;
	private final RegionMantenimientoRepository regionMantenimientoRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public SectorMantenimientoServiceImpl(SectorMantenimientoRepository sectorMantenimientoRepository,
			RegionMantenimientoRepository regionMantenimientoRepository,
			ModelMapperUtils modelMapper) {
		this.sectorMantenimientoRepository = sectorMantenimientoRepository;
		this.regionMantenimientoRepository = regionMantenimientoRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorMantenimientoDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		
		RegionMantenimiento regionMantenimiento;
		RegionMantenimiento regMantenimientoPorDefecto = regionMantenimientoRepository.findByNombre(ApplicationConstants.REGION_MANTENIMIENTO_POR_DEFECTO);
		if (regMantenimientoPorDefecto == null) {
			regionMantenimiento = RegionMantenimiento.builder().build();
		} else {
			regionMantenimiento = regMantenimientoPorDefecto;
		}
		
		return SectorMantenimientoDto.builder()
				                     .regionMantenimiento(this.modelMapperUtils.map(regionMantenimiento,RegionMantenimientoLiteDto.class))
				                     .build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		final SectorMantenimiento sectorMantenimientoBBDD = this.sectorMantenimientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.sectorMantenimientoRepository.delete(sectorMantenimientoBBDD);		
		
		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SectorMantenimientoDto> readAll() {

		return this.modelMapperUtils.mapAll2List(this.sectorMantenimientoRepository.findAll(), SectorMantenimientoDto.class);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorMantenimientoDto read(Long id) {

		final SectorMantenimiento sectorMantenimiento = this.sectorMantenimientoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final SectorMantenimientoDto result = this.modelMapperUtils.map(sectorMantenimiento, SectorMantenimientoDto.class);
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorMantenimientoDto saveUpdate(SectorMantenimientoDto sectorMantenimientoDto) {

		SectorMantenimiento sectorMantenimiento = this.modelMapperUtils.map(sectorMantenimientoDto, SectorMantenimiento.class);
		sectorMantenimiento.setRegionMantenimiento(this.modelMapperUtils.map(sectorMantenimientoDto.getRegionMantenimiento(), RegionMantenimiento.class));	
		
		return this.modelMapperUtils.map(this.sectorMantenimientoRepository.save(sectorMantenimiento), SectorMantenimientoDto.class);
	}
}
