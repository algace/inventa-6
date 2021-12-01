package com.dbcom.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.service.DocumentoService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jose.vallve 
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class DocumentoController {
	
	// Mapeo de las acciones
	public static final String MAP_DOWNLOAD_DOCUMENTO = "/descargar/documento";
	public static final String MAP_UPLOAD_DOCUMENTO = "/subir/documento";
	
	private final DocumentoService documentoService;
	
	@Autowired
	public DocumentoController(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}
	
	
	/**
	 * Subida del documento
	 * NOTA: Recargamos la página desde el frontoffice porque al hacer la subida del fichero con ajax
	 *       no recargaba la vista
	 * @param id Identificador del objeto que contendrá el fichero. Ej: equipamientos, etc..
	 * @param idTipoDocumento Identificador del tipo de documento
	 * @param descripcionDocumento Descripción
	 * @param fichero Fichero a subir
	 */
	@PostMapping(MAP_UPLOAD_DOCUMENTO)
	public void subirFichero(@RequestParam("id") final Long id, 
							   @RequestParam("idTipoDocumento") final Short idTipoDocumento,
							   @RequestParam("descripcionDocumento") final String descripcionDocumento,
							   @RequestParam("fichero") final MultipartFile fichero) {		
		
		this.documentoService.upload(id, idTipoDocumento, descripcionDocumento, fichero);
	}
	
	/**
	 * Descarga del documento del identificador
	 * @param id Identificador
	 * @return Documento
	 */
	@GetMapping(MAP_DOWNLOAD_DOCUMENTO + "/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("id") final Long id) {

		final DocumentoDto documento = this.documentoService.read(id);
		
		log.info("");
		
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(documento.getTipo()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getNombre() + "\"")
                .body(new ByteArrayResource(documento.getContenido()));
    }	
}
