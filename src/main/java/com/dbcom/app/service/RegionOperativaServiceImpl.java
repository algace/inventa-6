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
import com.dbcom.app.model.dao.RegionOperativaRepository;
import com.dbcom.app.model.dto.RegionOperativaDto;
import com.dbcom.app.model.entity.RegionOperativa;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionOperativaServiceImpl implements RegionOperativaService {

	private final RegionOperativaRepository regionOperativaRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public RegionOperativaServiceImpl(RegionOperativaRepository regionOperativaRepository,
			ModelMapperUtils modelMapper) {
		this.regionOperativaRepository = regionOperativaRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionOperativaDto create() {
		
		log.info(LoggerConstants.LOG_CREATE);
		return RegionOperativaDto.builder().build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		final RegionOperativa regionOperativa = this.regionOperativaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.regionOperativaRepository.delete(regionOperativa);		
		
		log.info(LoggerConstants.LOG_DELETE, id);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionOperativaDto> readAll() {

		final List<RegionOperativa> regionesMantenimiento = this.regionOperativaRepository.findAll();
		
		final List<RegionOperativaDto> regionesMantenimientoDto = new ArrayList<>(regionesMantenimiento.size());		
		regionesMantenimiento.forEach(regionMantenimiento -> regionesMantenimientoDto.add(this.modelMapperUtils.map(regionMantenimiento, RegionOperativaDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return regionesMantenimientoDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionOperativaDto read(Long id) {

		final RegionOperativa regionOperativa = this.regionOperativaRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final RegionOperativaDto result = this.modelMapperUtils.map(regionOperativa, RegionOperativaDto.class);
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RegionOperativaDto saveUpdate(RegionOperativaDto regionOperativaDto) {

		RegionOperativa regionOperativa = this.modelMapperUtils.map(regionOperativaDto, RegionOperativa.class);
		
		return this.modelMapperUtils.map(this.regionOperativaRepository.save(regionOperativa), RegionOperativaDto.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegionOperativaDto> getRegionesOperativasConValorPorDefecto() {

		RegionOperativa primerRegionOperativa;
		RegionOperativa regionOperativaPorDefecto = regionOperativaRepository.findByNombre(ApplicationConstants.REGION_OPERATIVA_POR_DEFECTO);
		List<RegionOperativa> listaRegiones = new ArrayList<RegionOperativa>();
		
		if (regionOperativaPorDefecto != null) {
			primerRegionOperativa = regionOperativaPorDefecto;
		} else {
			primerRegionOperativa = RegionOperativa.builder().build();
		}
		listaRegiones.add(primerRegionOperativa);
		listaRegiones.addAll(regionOperativaRepository.findAll()
				                                          .stream()
				                                          .filter(regionOperativa -> !regionOperativa.equals(primerRegionOperativa))
				                                          .collect(Collectors.toList()));
		
		return this.modelMapperUtils.mapAll2List(listaRegiones, RegionOperativaDto.class);
	}

}
