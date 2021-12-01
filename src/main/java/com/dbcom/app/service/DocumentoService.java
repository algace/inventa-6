package com.dbcom.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dbcom.app.model.dto.DocumentoDto;

/**
 * Lógica para documento
 * 
 * @author jose.vallve
 */
public interface DocumentoService extends GenericService<DocumentoDto, Long> {

	/**
	 * Creamos un documento sin persistencia
	 * @return Nuevo documento
	 */
	DocumentoDto create();

	/**
	 * Borrado del documento con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de documentos
	 * @return Listado
	 */
	List<DocumentoDto> readAll();
	
	/**
	 * Obtenemos el documento con el id facilitado
	 * @param id Identificador
	 * @return Documento
	 */
	DocumentoDto read(final Long id);
	
	/**
	 * Persistimos el documento pasado como parámetro
	 * @param documentoDto Documento a persistir
	 * @return Documento persistido
	 */
	DocumentoDto save(final DocumentoDto documentoDto);
		
	/**
	 * Actualizamos el documento pasado como parámetro
	 * @param documentoDto Documento a actualizar
	 * @return Documento actualizado
	 */
	DocumentoDto update(final DocumentoDto documentoDto);
	
	public void upload(final Long idEquipamiento, final Short idTipoDocumento, 
					   final String descripcionDocumento, final MultipartFile fichero);
}
