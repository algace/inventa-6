package com.dbcom.app.constants;

/**
 * Constantes comunes a los controladores
 * 
 * @author jose.vallve
 */
public final class ControllerConstants {
	
	// Variables comunes
	private static final String BORRAR = "borrar";
	private static final String MODIFICAR = "modificar";
	
	// Atributos comunes
	public static final String ATTRIBUTE_ACTION = "action";
	public static final String ATTRIBUTE_LISTA = "lista";
	
	// Atributos para botones
	public static final String ATTRIBUTE_BOTON_AGNADIR = "agnadir";
	public static final String ATTRIBUTE_BOTON_BORRAR = BORRAR;
	public static final String ATTRIBUTE_BOTON_BUSCAR = "buscar";
	public static final String ATTRIBUTE_BOTON_ELIMINAR = "eliminar";
	public static final String ATTRIBUTE_BOTON_LEER = "leer";
	public static final String ATTRIBUTE_BOTON_MODIFICAR = MODIFICAR;
	public static final String ATTRIBUTE_BOTON_VOLVER = "volver";	
	
	// Atributos para popup
	public static final String ATTRIBUTE_POPUP_ELIMINAR_AVISO = "popupEliminarAviso";
	public static final String ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA = "popupEliminarPregunta";

	// Atributos para descargas
	public static final String ATTRIBUTE_DOWNLOAD = "download";
	public static final String ATTRIBUTE_FICHERO_TAMAGNO_MAX = "ficheroTamagnoMax";
	public static final String ATTRIBUTE_TIPOS_DOCUMENTO = "tiposDocumento";
	public static final String ATTRIBUTE_UPLOAD = "upload";
	public static final String ATTRIBUTE_UPLOAD_DOCUMENTO = "uploadDocumento";
	public static final String ATTRIBUTE_UPLOAD_FOTOGRAFIA = "uploadFotografia";
	
	// Atributos para visualización
	public static final String ATTRIBUTE_ES_CAMPO_SOLO_LECTURA = "esCampoSoloLectura";
	public static final String ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO = "estaBotonAceptarActivo";
	public static final String ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO = "estaBotonCancelarActivo";
	public static final String ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO = "estaBotonEliminarActivo";

	// Acciones para mapas
	public static final String MAP_ACTION_SLASH = "/";
	public static final String MAP_ACTION_CREAR = MAP_ACTION_SLASH + "crear";
	public static final String MAP_ACTION_BORRAR = MAP_ACTION_SLASH + BORRAR;
	public static final String MAP_ACTION_GUARDAR = MAP_ACTION_SLASH + "guardar";
	public static final String MAP_ACTION_MODIFICAR = MAP_ACTION_SLASH + MODIFICAR;
	
	// Rutas de las plantillas para mapas
	public static final String MAP_PATH_MENU = "menu" + MAP_ACTION_SLASH;
	public static final String MAP_PATH_MENU_CONTROLVERSIONESSW = MAP_PATH_MENU + "controlVersionesSW" + MAP_ACTION_SLASH;
	public static final String MAP_PATH_MENU_ORGANIZACION = MAP_PATH_MENU + "organizacion" + MAP_ACTION_SLASH;
	public static final String MAP_PATH_MENU_PASARELASVOIP = MAP_PATH_MENU + "pasarelasVoIP" + MAP_ACTION_SLASH;
	public static final String MAP_PATH_MENU_REDESTT = MAP_PATH_MENU + "redesTT" + MAP_ACTION_SLASH;
	public static final String MAP_PATH_MENU_SECTORESESPACIOAEREO = MAP_PATH_MENU + "sectoresEspacioAereo" + MAP_ACTION_SLASH;
	public static final String MAP_PATH_MENU_SISTEMAS = MAP_PATH_MENU + "sistemas" + MAP_ACTION_SLASH;
	public static final String MAP_PATH_MENU_TIPOS = MAP_PATH_MENU + "tipos" + MAP_ACTION_SLASH;

	public static final String REDIRECT = "redirect:";

	private ControllerConstants() {}

}
