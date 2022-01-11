package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.SectorATCRepository;
import com.dbcom.app.model.dto.AirblockDto;
import com.dbcom.app.model.dto.SectorATCDto;
import com.dbcom.app.model.entity.SectorATC;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class SectorATCServiceImpl implements SectorATCService{

	private final ModelMapperUtils modelMapperUtils;
	private final SectorATCRepository sectorATCRepository;
	
	@Autowired
	public SectorATCServiceImpl(ModelMapperUtils modelMapper,
									   SectorATCRepository sectorATCRepository) {
		this.modelMapperUtils = modelMapper;
		this.sectorATCRepository = sectorATCRepository;
	}

	@Override
	public SectorATCDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		
		return  SectorATCDto.builder().build();
	}

	@Override
	public void delete(Short id) {
		
		final SectorATC sectorATCBBDD = this.sectorATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.sectorATCRepository.delete(sectorATCBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
		
	}

	@Override
	public List<SectorATCDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<SectorATC> sectoresATC = this.sectorATCRepository.findAll();

		final List<SectorATCDto> sectoresATCDto = new ArrayList<>(sectoresATC.size());		
		sectoresATC.forEach(sectorATC -> {
			SectorATCDto sectorATCDto = this.modelMapperUtils.map(sectorATC, SectorATCDto.class);
			String airblockList = "";
			for (int i=0; i < sectorATCDto.getAirblocks().size(); i++) {
				if ((sectorATCDto.getAirblocks().size()-1) == i) {
					airblockList = airblockList + sectorATCDto.getAirblocks().get(i).getNombre();
				}else {
					airblockList = airblockList + sectorATCDto.getAirblocks().get(i).getNombre() + ":";
				}
			}
			sectorATCDto.setAirblockList(airblockList);
			sectoresATCDto.add(sectorATCDto);
		});
		
		return sectoresATCDto;
	}

	@Override
	public SectorATCDto read(Short id) {
		
		log.info(LoggerConstants.LOG_READ);		

		final SectorATC sectorATC = this.sectorATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		SectorATCDto sector = this.modelMapperUtils.map(sectorATC, SectorATCDto.class);

		return sector; 
	}

	@Override
	public SectorATCDto saveUpdate(SectorATCDto sectorATCDto) {
		
		sectorATCDto.setAirblocks(filterListAirblock(sectorATCDto.getAirblocks()));
		
		SectorATC sectorATC = this.modelMapperUtils.map(sectorATCDto, SectorATC.class);	
		
		return this.modelMapperUtils.map(this.sectorATCRepository.save(sectorATC), SectorATCDto.class);
	}

	@Override
	public List<AirblockDto> listAirblocksSeleccionados(List<AirblockDto> allAirblocks, List<AirblockDto> airblockSeleccionados){

		List<AirblockDto> airblocks = new ArrayList<>();
	
		allAirblocks.stream().forEach(airblock -> {
			if (airblockSeleccionados.stream().anyMatch(ab -> airblock.getId() == ab.getId())) {
				airblocks.add(airblock);
			}
		});
	
		return airblocks;
	}

	/**
	 * Procesa una lista de objetos Airblock proveniente del front y elimina de esta lista los 
	 * objetos que tienen un id null
     * @param lista de objetos AirblockDto provenientes del front 
     * @return lista de objetos AirblockDto filtrada 
	 */
	private List<AirblockDto> filterListAirblock(List<AirblockDto> listAirblocks) {
	
		return listAirblocks.stream()
                            .filter(version -> !Objects.isNull(version.getId()))
                            .collect(Collectors.toList());
	}
}
