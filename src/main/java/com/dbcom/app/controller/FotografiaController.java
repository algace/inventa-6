package com.dbcom.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dbcom.app.service.FotografiaService;

import lombok.extern.slf4j.Slf4j;

/**
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class FotografiaController {

	// Mapeo de las acciones
	public static final String MAP_DOWNLOAD_FOTOGRAFIA = "/descargar/fotografia";
	public static final String MAP_UPLOAD_FOTOGRAFIA = "/subir/fotografia";
	
	private final FotografiaService fotografiaService;
	
	@Autowired
	public FotografiaController(FotografiaService fotografiaService) {
		this.fotografiaService = fotografiaService;
	}
	
	/**
	 * Subida de la fotografia
	 * NOTA: Recargamos la página desde el frontoffice porque al hacer la subida de la fotografia con ajax
	 *       no recargaba la vista
	 * @param id Identificador del objeto que contendrá el fichero. Ej: equipamientos, etc..
	 * @param descripcionFotografia Descripción
	 * @param fichero Fichero a subir
	 */
	@PostMapping(MAP_UPLOAD_FOTOGRAFIA)
	public void subirFichero(@RequestParam("id") final Long id, 
							   @RequestParam("descripcionFotografia") final String descripcionFotografia,
							   @RequestParam("fichero") final MultipartFile fichero) {		
		
		this.fotografiaService.upload(id, descripcionFotografia, fichero);
	}
}
