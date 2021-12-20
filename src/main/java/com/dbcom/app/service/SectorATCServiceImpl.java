package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.model.dao.SectorATCRepository;
import com.dbcom.app.model.dto.FuncionPasarelaDto;
import com.dbcom.app.model.dto.SectorATCDto;
import com.dbcom.app.model.entity.FuncionPasarela;
import com.dbcom.app.model.entity.SectorATC;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class SectorATCServiceImpl implements SectorATCService{

	private final ModelMapperUtils modelMapperUtils;
	private final SectorATCRepository sectorATCRepository;
	private final TipoSectorATCService tipoSectorATCService;
	private final TipoFuenteInformacionService tipoFuenteInformacionService;
	
	@Autowired
	public SectorATCServiceImpl(ModelMapperUtils modelMapper,
									   SectorATCRepository sectorATCRepository,
									   TipoSectorATCService tipoSectorATCService,
									   TipoFuenteInformacionService tipoFuenteInformacionService) {
		this.modelMapperUtils = modelMapper;
		this.sectorATCRepository = sectorATCRepository;
		this.tipoSectorATCService = tipoSectorATCService;
		this.tipoFuenteInformacionService = tipoFuenteInformacionService;
	}

	@Override
	public SectorATCDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		return  SectorATCDto.builder()
				.tiposSectorATCDto(tipoSectorATCService.readAll())
				.tiposFuenteInformacionDto(tipoFuenteInformacionService.readAll())
				.build();
	}

	@Override
	public void delete(Short id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SectorATCDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<SectorATC> sectoresATC = this.sectorATCRepository.findAll();

		final List<SectorATCDto> sectoresATCDto = new ArrayList<>(sectoresATC.size());		
		sectoresATC.forEach(sectorATC -> sectoresATCDto.add(this.modelMapperUtils.map(sectorATC, SectorATCDto.class)));
		
		return sectoresATCDto;
	}

	@Override
	public SectorATCDto read(Short id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SectorATCDto save(SectorATCDto sectorATCDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SectorATCDto update(SectorATCDto sectorATCDto) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
