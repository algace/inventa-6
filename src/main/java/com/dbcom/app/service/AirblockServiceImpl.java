package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AirblockRepository;
import com.dbcom.app.model.dao.SectorATCRepository;
import com.dbcom.app.model.dto.AirblockDto;
import com.dbcom.app.model.entity.Airblock;
import com.dbcom.app.model.entity.SectorATC;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * 
 * Lógica para airblocks
 */
@Service
@Slf4j
public final class AirblockServiceImpl implements AirblockService {
	
	private AirblockRepository airblockRepository;
	private final ModelMapperUtils  modelMapperUtils;
	private SectorATCRepository sectorATCRepository;

	@Autowired
	public AirblockServiceImpl(ModelMapperUtils modelMapper,
			AirblockRepository airblockRepository,
			SectorATCRepository sectorATCRepository) {
		this.modelMapperUtils = modelMapper;
		this.airblockRepository = airblockRepository;
		this.sectorATCRepository = sectorATCRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AirblockDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new AirblockDto();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long id) {			
		
		final Airblock airblockBBDD = this.airblockRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.airblockRepository.delete(airblockBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AirblockDto> readAll() {
		
		final List<Airblock> airblocks = this.airblockRepository.findAll();
		
		final List<AirblockDto> airblocksDto = new ArrayList<>(airblocks.size());		
		airblocks.forEach(airblock -> {
			AirblockDto airblockDto = this.modelMapperUtils.map(airblock, AirblockDto.class);
			final List<SectorATC> sectoresWithAirblocks = this.airblockRepository.findSectoresATCWithAirbloks(airblock.getId());
			String sectoresATCList = "";
			for (int i=0; i < sectoresWithAirblocks.size(); i++) {
				if ((sectoresWithAirblocks.size()-1) == i) {
					sectoresATCList = sectoresATCList + sectoresWithAirblocks.get(i).getNombre();
				}else {
					sectoresATCList = sectoresATCList + sectoresWithAirblocks.get(i).getNombre() + ":";
				}
			}
			airblockDto.setSectoresATC(sectoresATCList);
			airblocksDto.add(airblockDto);
		});
		
		log.info(LoggerConstants.LOG_READALL);
		
		return airblocksDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AirblockDto read(final Long id) {		
		
		final Airblock airblock = this.airblockRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);	
		
		final AirblockDto result = this.modelMapperUtils.map(airblock, AirblockDto.class);

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AirblockDto saveUpdate(final AirblockDto airblocksDto) {		
		
		Airblock airblock = this.modelMapperUtils.map(airblocksDto, Airblock.class);
		
		return this.modelMapperUtils.map(this.airblockRepository.save(airblock), AirblockDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AirblockDto> readNotContains(Short id) {

		final SectorATC sectorATC = this.sectorATCRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		log.info(LoggerConstants.LOG_READ);		

		final List<Airblock> airblock = this.airblockRepository.findAll();
		airblock.removeAll(sectorATC.getAirblocks());
		
		return this.modelMapperUtils.mapAll2List(airblock, AirblockDto.class);
	}
}
 