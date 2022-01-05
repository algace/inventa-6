package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ApplicationConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.RegionOperativaRepository;
import com.dbcom.app.model.dao.SectorOACIRepository;
import com.dbcom.app.model.dto.RegionOperativaLiteDto;
import com.dbcom.app.model.dto.SectorOACIDto;
import com.dbcom.app.model.entity.RegionOperativa;
import com.dbcom.app.model.entity.SectorOACI;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SectorOACIServiceImpl implements SectorOACIService {

	private final SectorOACIRepository sectorOACIRepository;
	private final RegionOperativaService regionOperativaService;
	private final RegionOperativaRepository regionOperativaRepository;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public SectorOACIServiceImpl(SectorOACIRepository sectorOACIRepository,
			RegionOperativaService regionOperativaService,
			RegionOperativaRepository regionOperativaRepository,
			ModelMapperUtils modelMapper) {
		this.sectorOACIRepository = sectorOACIRepository;
		this.regionOperativaService = regionOperativaService;
		this.regionOperativaRepository = regionOperativaRepository;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorOACIDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		
		RegionOperativa regionOperativa;
		RegionOperativa regOperativaPorDefecto = regionOperativaRepository.findByNombre(ApplicationConstants.REGION_OPERATIVA_POR_DEFECTO);
		if (regOperativaPorDefecto == null) {
			regionOperativa = RegionOperativa.builder().build();
		} else {
			regionOperativa = regOperativaPorDefecto;
		}
		
		return SectorOACIDto.builder()
				            .regionesOperativasDisponibles(regionOperativaService.readAll())
				            .regionOperativa(this.modelMapperUtils.map(regionOperativa,RegionOperativaLiteDto.class))
				            .build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		final SectorOACI sectorOACIBBDD = this.sectorOACIRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.sectorOACIRepository.delete(sectorOACIBBDD);		
		
		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SectorOACIDto> readAll() {

		final List<SectorOACI> sectoresOACI = this.sectorOACIRepository.findAll();
		
		final List<SectorOACIDto> sectoresOACIDto = new ArrayList<>(sectoresOACI.size());
		sectoresOACI.forEach(sectorOACI -> sectoresOACIDto.add(this.modelMapperUtils.map(sectorOACI, SectorOACIDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return sectoresOACIDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorOACIDto read(Long id) {

		final SectorOACI sectorOACI = this.sectorOACIRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);
		
		final SectorOACIDto result = this.modelMapperUtils.map(sectorOACI, SectorOACIDto.class);
		result.setRegionesOperativasDisponibles(regionOperativaService.readAll());
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SectorOACIDto saveUpdate(SectorOACIDto sectorOACIDto) {

		SectorOACI sectorOACI = this.modelMapperUtils.map(sectorOACIDto, SectorOACI.class);
		sectorOACI.setRegionOperativa(this.modelMapperUtils.map(sectorOACIDto.getRegionOperativa(), RegionOperativa.class));	
		
		return this.modelMapperUtils.map(this.sectorOACIRepository.save(sectorOACI), SectorOACIDto.class);
	}
}
