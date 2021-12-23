package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.RedTTRepository;
import com.dbcom.app.model.dao.TipoTopologiaRepository;
import com.dbcom.app.model.dto.RedTTDto;
import com.dbcom.app.model.dto.TipoTopologiaDto;
import com.dbcom.app.model.dto.TipoTopologiaLiteDto;
import com.dbcom.app.model.entity.RedTT;
import com.dbcom.app.model.entity.TipoTopologia;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para aplicaciones
 * 
 * @author neoris
 */
@Service
@Slf4j
public class RedTTServiceImpl implements RedTTService {

	private final RedTTRepository redTTRepository;
	private final TipoTopologiaRepository tipoTopologiaRepository;
	private final TipoTopologiaService tipoTopologiaService;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public RedTTServiceImpl(RedTTRepository redTTRepository,
			TipoTopologiaRepository tipoTopologiaRepository,
			TipoTopologiaService tipoTopologiaService,
			ModelMapperUtils modelMapper) {
		this.redTTRepository = redTTRepository;
		this.tipoTopologiaRepository = tipoTopologiaRepository;
		this.tipoTopologiaService = tipoTopologiaService;
		this.modelMapperUtils = modelMapper;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RedTTDto create() {

		log.info(LoggerConstants.LOG_CREATE);
		
		List<TipoTopologiaDto> listaTopologiasDisponibles = tipoTopologiaService.getTiposTopologiasConValorPorDefecto();
		return RedTTDto.builder()
			       .tipoTopologia(this.modelMapperUtils.map(listaTopologiasDisponibles.get(0), TipoTopologiaLiteDto.class))
			       .tiposTopologiaDisponibles(listaTopologiasDisponibles)
			       .build();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {
		
		final RedTT redTTBBDD = this.redTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.redTTRepository.delete(redTTBBDD);		
		
		log.info(LoggerConstants.LOG_DELETE, id);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RedTTDto> readAll() {

		final List<RedTT> redesTT = this.redTTRepository.findAll();
		
		final List<RedTTDto> redesTTDto = new ArrayList<>(redesTT.size());		
		redesTT.forEach(redTT -> redesTTDto.add(this.modelMapperUtils.map(redTT, RedTTDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);

		return redesTTDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RedTTDto read(Long id) {

		final RedTT redTT = this.redTTRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final RedTTDto result = this.modelMapperUtils.map(redTT, RedTTDto.class);
		
		
		//El tipo de topología se mostrará en una lista desplegable. El tipo de topología asociado
		//que hemos obtenido debe ponerse el primero en la lista de tipos de topología para que se muestre
		//en esa lista desplegable
		result.setTiposTopologiaDisponibles(getTiposTopologiasConAsociadaPrimero(result));
		
				
		//Falta añadir los enlaces T/T disponibles cuando se implemente la entidad EnlaceTT

		log.info(LoggerConstants.LOG_READ);		

		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RedTTDto save(RedTTDto redTTDto) {

		RedTT redTT = this.modelMapperUtils.map(redTTDto, RedTT.class);
		redTT.setTipoTopologia(this.modelMapperUtils.map(redTTDto.getTipoTopologia(), TipoTopologia.class));
		redTT = this.redTTRepository.save(redTT);
		
		log.info(LoggerConstants.LOG_CREATE, redTT.getNombre());		
		
		return this.modelMapperUtils.map(redTT, RedTTDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RedTTDto update(RedTTDto redTTDto) {

		final RedTT redTT = this.modelMapperUtils.map(redTTDto, RedTT.class);
		
		RedTT redTTBBDD = this.redTTRepository.findById(redTT.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		// Actualizamos el registro de bbdd
		redTTBBDD.setNombre(redTTDto.getNombre());
		redTTBBDD.setTipoTopologia(this.modelMapperUtils.map(redTTDto.getTipoTopologia(),TipoTopologia.class));
		redTTBBDD.setObservaciones(redTTDto.getObservaciones());
		
		//Falta añadir los enlaces T/T asociados cuando se implemente la entidad EnlaceTT

		redTTBBDD = this.redTTRepository.save(redTTBBDD);		
		
		log.info(LoggerConstants.LOG_UPDATE, redTTBBDD.getId());
		
		return this.modelMapperUtils.map(redTTBBDD, RedTTDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TipoTopologiaDto> getTiposTopologiasConAsociadaPrimero(RedTTDto redTTDto) {
	
		//El tipo de topología asociada a la red T/T debe ser la primera de la lista
		TipoTopologia tipoTopologiaAsociada = this.modelMapperUtils.map(redTTDto.getTipoTopologia(), TipoTopologia.class);
		
		TipoTopologia tipoTopologiaAsociadaBBDD = tipoTopologiaRepository.findById(tipoTopologiaAsociada.getId())
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		List<TipoTopologia> listaTiposTopologia = new ArrayList<TipoTopologia>();
		listaTiposTopologia.add(tipoTopologiaAsociadaBBDD);
		listaTiposTopologia.addAll(tipoTopologiaRepository.findAll()
						                                  .stream()
						                                  .filter(tipoTopologia -> !tipoTopologia.equals(tipoTopologiaAsociadaBBDD))
						                                  .collect(Collectors.toList()));
		
		return this.modelMapperUtils.mapAll2List(listaTiposTopologia, TipoTopologiaDto.class);
	}
	
}
