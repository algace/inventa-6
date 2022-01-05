package com.dbcom.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dao.FotografiaRepository;
import com.dbcom.app.model.dto.FotografiaDto;
import com.dbcom.app.model.entity.Equipamiento;
import com.dbcom.app.model.entity.Fotografia;

import lombok.extern.slf4j.Slf4j;

/**
 * Lógica para fotografia
 * 
 * @author jose.vallve
 */
@Service
@Slf4j
public class FotografiaServiceImpl implements FotografiaService {

	private final FotografiaRepository fotografiaRepository;
	private final EquipamientoRepository equipamientoRepository;
	
	@Autowired
	public FotografiaServiceImpl(FotografiaRepository fotografiaRepository,
			                     EquipamientoRepository equipamientoRepository) {
		this.fotografiaRepository = fotografiaRepository;
		this.equipamientoRepository = equipamientoRepository;
	}
	
	@Override
	public FotografiaDto create() {
		log.info(LoggerConstants.LOG_CREATE);
		return new FotografiaDto();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FotografiaDto> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FotografiaDto read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FotografiaDto save(FotografiaDto fotografiaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FotografiaDto update(FotografiaDto fotografiaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upload(Long idEquipamiento, String descripcionFotografia, MultipartFile fichero) {

		final Equipamiento equipamiento = this.equipamientoRepository.findById(idEquipamiento)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		if (!fichero.getOriginalFilename().isEmpty()) {
			try {
				final Fotografia fotografia = Fotografia.builder()
												        .nombre(fichero.getOriginalFilename())
												        .descripcion(descripcionFotografia)
												        .contenido(fichero.getBytes())
//												        .equipamiento(equipamiento)
												        .build();
				this.fotografiaRepository.save(fotografia);
	
			} catch (IOException e) {
				log.error(ExceptionConstants.DAO_EXCEPTION, fichero.getOriginalFilename());
			}
			
		}

	}

}
