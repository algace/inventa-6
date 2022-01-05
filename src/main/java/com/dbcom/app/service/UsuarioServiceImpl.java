package com.dbcom.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.exception.DaoException;
import com.dbcom.app.model.dao.UsuarioRepository;
import com.dbcom.app.model.dto.UsuarioDto;
import com.dbcom.app.model.entity.Usuario;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * 
 * Lógica para usuarios
 */
@Service
@Slf4j
public final class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	private final ModelMapperUtils  modelMapperUtils;

	@Autowired
	public UsuarioServiceImpl(ModelMapperUtils modelMapper,
			UsuarioRepository usuarioRepository) {
		this.modelMapperUtils = modelMapper;
		this.usuarioRepository = usuarioRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioDto create() {		
		log.info(LoggerConstants.LOG_CREATE);
		return new UsuarioDto();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long id) {			
		
		final Usuario usuarioBBDD = this.usuarioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
		
		this.usuarioRepository.delete(usuarioBBDD);		

		log.info(LoggerConstants.LOG_DELETE, id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioDto> readAll() {
		
		final List<Usuario> usuarios = this.usuarioRepository.findAll();
		
		final List<UsuarioDto> usuariosDtos = new ArrayList<>(usuarios.size());		
		usuarios.forEach(usuario -> usuariosDtos.add(this.modelMapperUtils.map(usuario, UsuarioDto.class)));
		
		log.info(LoggerConstants.LOG_READALL);
		
		return usuariosDtos;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioDto read(final Long id) {		
		
		final Usuario usuario = this.usuarioRepository.findById(id)
				.orElseThrow(() -> new DaoException(ExceptionConstants.DAO_EXCEPTION));
	
		final UsuarioDto result = this.modelMapperUtils.map(usuario, UsuarioDto.class);
		
		log.info(LoggerConstants.LOG_READ);		

		return result; 		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioDto saveUpdate(final UsuarioDto usuarioDto) {		
		
		Usuario usuario = this.modelMapperUtils.map(usuarioDto, Usuario.class);	
		
		return this.modelMapperUtils.map(this.usuarioRepository.save(usuario), UsuarioDto.class);
	}
}
 