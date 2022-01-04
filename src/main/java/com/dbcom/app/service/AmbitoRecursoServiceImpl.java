package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.AmbitoRecursoRepository;
import com.dbcom.app.model.dto.AmbitoRecursoDto;
import com.dbcom.app.model.entity.AmbitoRecurso;
import com.dbcom.app.model.entity.FuncionPasarela;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public final class AmbitoRecursoServiceImpl implements AmbitoRecursoService{
	
	private final ModelMapperUtils modelMapperUtils;
	private final AmbitoRecursoRepository ambitoRecursoRepository;
	private final FuncionPasarelaService funcionPasarelasService;
	
	@Autowired
	public AmbitoRecursoServiceImpl(ModelMapperUtils modelMapper, AmbitoRecursoRepository ambitoRecursoRepository,
			FuncionPasarelaService funcionPasarelasService) {
		this.modelMapperUtils = modelMapper;
		this.ambitoRecursoRepository = ambitoRecursoRepository;
		this.funcionPasarelasService = funcionPasarelasService;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AmbitoRecursoDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return AmbitoRecursoDto.builder().funcionPasarelas(funcionPasarelasService.readAll()).build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final Long id) {			
		
		final AmbitoRecurso ambitoRecursoBBDD = this.ambitoRecursoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.ambitoRecursoRepository.delete(ambitoRecursoBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AmbitoRecursoDto> readAll() {
		
		log.info(LoggerConstants.LOG_READALL);

		final List<AmbitoRecurso> ambitoRecursos = this.ambitoRecursoRepository.findAll();

		final List<AmbitoRecursoDto> ambitoRecursosDto = new ArrayList<>(ambitoRecursos.size());		
		ambitoRecursos.forEach(ambitoRecurso -> ambitoRecursosDto.add(this.modelMapperUtils.map(ambitoRecurso, AmbitoRecursoDto.class)));
		
		return ambitoRecursosDto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AmbitoRecursoDto read(final Long id) {	
		
		log.info(LoggerConstants.LOG_READ);		

		final AmbitoRecurso ambitoRecurso = this.ambitoRecursoRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		AmbitoRecursoDto ambito = this.modelMapperUtils.map(ambitoRecurso, AmbitoRecursoDto.class);
		ambito.setFuncionPasarelas(funcionPasarelasService.readAll());

		return ambito; 

	}
			
	/**
	 * {@inheritDoc}
	 */
	public AmbitoRecursoDto saveUpdate(final AmbitoRecursoDto ambitoRecursoDto) {		
		
		AmbitoRecurso ambitoRecurso = this.modelMapperUtils.map(ambitoRecursoDto, AmbitoRecurso.class);
		
		return this.modelMapperUtils.map(this.ambitoRecursoRepository.save(ambitoRecurso), AmbitoRecursoDto.class);
	}

}
