package com.dbcom.app.service;

import java.util.ArrayList;
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
	private final RegionMantenimientoService regionMantenimientoService;
	private final RegionMantenimientoRepository regionMantenimientoRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public SectorMantenimientoServiceImpl(SectorMantenimientoRepository sectorMantenimientoRepository,
			RegionMantenimientoService regionMantenimientoService,
			RegionMantenimientoRepository regionMantenimientoRepository,
			ModelMapperUtils modelMapper) {
		this.sectorMantenimientoRepository = sectorMantenimientoRepository;
		this.regionMantenimientoService = regionMantenimientoService;
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
				                     .regionesMantenimientoDisponibles(regionMantenimientoService.readAll())
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

		final List<SectorMantenimiento> sectoresMantenimiento = this.sectorMantenimientoRepository.findAll();
		
		final List<SectorMantenimientoDto> sectoresMantenimientoDto = new ArrayList<>(sectoresMantenimiento.size());
		sectoresMantenimiento.forEach(sectorMantenimiento -> sectoresMantenimientoDto.add(this.modelMapperUtils.map(sectorMantenimiento, SectorMantenimientoDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return sectoresMantenimientoDto;
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
		result.setRegionesMantenimientoDisponibles(regionMantenimientoService.readAll());
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorMantenimientoDto save(SectorMantenimientoDto sectorMantenimientoDto) {

		SectorMantenimiento sectorMantenimiento = this.modelMapperUtils.map(sectorMantenimientoDto, SectorMantenimiento.class);
		sectorMantenimiento.setRegionMantenimiento(this.modelMapperUtils.map(sectorMantenimientoDto.getRegionMantenimiento(), RegionMantenimiento.class));
		
		sectorMantenimiento = this.sectorMantenimientoRepository.save(sectorMantenimiento);	
		
		log.info(LoggerConstants.LOG_CREATE, sectorMantenimiento.getNombre());		
		
		return this.modelMapperUtils.map(sectorMantenimiento, SectorMantenimientoDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorMantenimientoDto update(SectorMantenimientoDto sectorMantenimientoDto) {

		final SectorMantenimiento sectorMantenimiento = this.modelMapperUtils.map(sectorMantenimientoDto, SectorMantenimiento.class);
		
		SectorMantenimiento sectorMantenimientoBBDD = this.sectorMantenimientoRepository.findById(sectorMantenimiento.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		sectorMantenimientoBBDD.setNombre(sectorMantenimientoDto.getNombre());
		sectorMantenimientoBBDD.setDescripcion(sectorMantenimientoDto.getDescripcion());
		sectorMantenimientoBBDD.setRegionMantenimiento(this.modelMapperUtils.map(sectorMantenimientoDto.getRegionMantenimiento(),RegionMantenimiento.class));
		sectorMantenimientoBBDD = this.sectorMantenimientoRepository.save(sectorMantenimientoBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, sectorMantenimientoBBDD.getId());
		
		return this.modelMapperUtils.map(sectorMantenimientoBBDD, SectorMantenimientoDto.class);
	}
}
