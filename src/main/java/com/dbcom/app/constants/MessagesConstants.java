package com.dbcom.app.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Textos de los mensajes para la vista que son seleccionados en el controlador y que son leídos desde el properties
 * Por ejemplo, el texto para un popup.
 * 
 * @author jose.vallve
 */
@Component
@PropertySource("classpath:messages/messages.properties")
public final class MessagesConstants {
	
    /**
	 * Tamaño máximo del fichero en bytes
	 */
	public static Long FILE_MAX_SIZE;	
	
	// Mensajes de error
	public static String ERROR_400; 
	public static String ERROR_403; 
	public static String ERROR_404; 
	public static String ERROR_500; 
	public static String ERROR_DESCONOCIDO; 
	
	// Mensajes para los popups
	public static String POPUP_ELIMINAR_APLICACION_PREGUNTA;
	public static String POPUP_ELIMINAR_CHASISPASARELA_PREGUNTA;
	public static String POPUP_ELIMINAR_EQUIPAMIENTO_PREGUNTA;
	public static String POPUP_ELIMINAR_FUNCIONPASARELA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_BANDAFRECUENCIA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_CANALIZACION_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_CAPACIDADENLACETT_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_CHASIS_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_CONFICURACIONRADIOENLACE_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_CONFICURACIONTIRADA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_DEPENDENCIA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_DOCUMENTO_AVISO;
	public static String POPUP_ELIMINAR_TIPO_DOCUMENTO_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_EMPLAZAMIENTO_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_ENLACETT_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_EQUIPOCONFIGURACIONRADIO_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_ESTRUCTURA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_FUENTEINFORMACION_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_INSTALACION_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_INTERFAZOPERACION_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_JERARQUIA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_LINEATELEFONICA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_MODULACION_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_POLARIZACION_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_RACK_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_RADIOENLACE_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_RECURSOOPERATIVO_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_RECURSORADIO_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_SECTORATC_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_SISTEMALOCAL_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_TIRADA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_TOPOLOGIA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_UBICACION_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_USOANTENA_PREGUNTA;
	public static String POPUP_ELIMINAR_TIPO_USOTIRADA_PREGUNTA;
	public static String POPUP_ELIMINAR_VERSION_PREGUNTA;

    @Value("${fichero.tamagno.maximo}")
    public void setValidationException(final Long tamagno) {
    	FILE_MAX_SIZE = tamagno;
    }
	
	@Value("${html.label.eliminar.equipamiento.pregunta}")
	public void setMessagePopupEliminarAplicacionPregunta(final String texto) {
		POPUP_ELIMINAR_APLICACION_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.equipamiento.pregunta}")
	public void setMessagePopupEliminarEquipamientoPregunta(final String texto) {
		POPUP_ELIMINAR_EQUIPAMIENTO_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.chasisPasarela.pregunta}")
	public void setMessagePopupEliminarPasarelaPregunta(final String texto) {
		POPUP_ELIMINAR_CHASISPASARELA_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.funcionPasarela.pregunta}")
	public void setMessagePopupEliminarFuncionPasarelaPregunta(final String texto) {
		POPUP_ELIMINAR_FUNCIONPASARELA_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoDocumento.aviso}")
	public void setMessagePopupEliminarTipoDocumentoAviso(final String texto) {
		POPUP_ELIMINAR_TIPO_DOCUMENTO_AVISO = texto;
	}

	@Value("${html.label.eliminar.tipoBandaFrecuencia.pregunta}")
	public void setMessagePopupEliminarTipoBandaFrecuenciaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_BANDAFRECUENCIA_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoCanalizacion.pregunta}")
	public void setMessagePopupEliminarTipoCanalizacionPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_CANALIZACION_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoCapacidadEnlaceTT.pregunta}")
	public void setMessagePopupEliminarTipoCapacidadEnlaceTTPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_CAPACIDADENLACETT_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoChasis.pregunta}")
	public void setMessagePopupEliminarTipoChasisregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_CHASIS_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoConfiguracionRadioenlace.pregunta}")
	public void setMessagePopupEliminarTipoConfiguracionRadioenlacePregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_CONFICURACIONRADIOENLACE_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoConfiguracionTirada.pregunta}")
	public void setMessagePopupEliminarTipoConfiguracionTiradaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_CONFICURACIONTIRADA_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoDependencia.pregunta}")
	public void setMessagePopupEliminarTipoDependenciaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_DEPENDENCIA_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoDocumento.pregunta}")
	public void setMessagePopupEliminarTipoDocumentoPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_DOCUMENTO_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoEmplazamiento.pregunta}")
	public void setMessagePopupEliminarTipoEmplazamientoPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_EMPLAZAMIENTO_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoEnlaceTT.pregunta}")
	public void setMessagePopupEliminarTipoEnlaceTTPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_ENLACETT_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoEquipoConfiguracionRadio.pregunta}")
	public void setMessagePopupEliminarTipoEquipoConfiguracionRadioPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_EQUIPOCONFIGURACIONRADIO_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoEstructura.pregunta}")
	public void setMessagePopupEliminarTipoEstructuraPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_ESTRUCTURA_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoFuenteInformacion.pregunta}")
	public void setMessagePopupEliminarTipoFuenteInformacionPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_FUENTEINFORMACION_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoInstalacion.pregunta}")
	public void setMessagePopupEliminarTipoInstalacionPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_INSTALACION_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoInterfazOperacion.pregunta}")
	public void setMessagePopupEliminarTipoInterfazOperacionPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_INTERFAZOPERACION_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoJerarquia.pregunta}")
	public void setMessagePopupEliminarTipoJerarquiaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_JERARQUIA_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoLineaTelefonica.pregunta}")
	public void setMessagePopupEliminarTipoLineaTelefonicaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_LINEATELEFONICA_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoModulacion.pregunta}")
	public void setMessagePopupEliminarTipoModulacionPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_MODULACION_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoPolarizacion.pregunta}")
	public void setMessagePopupEliminarTipoPolarizacionPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_POLARIZACION_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoRack.pregunta}")
	public void setMessagePopupEliminarTipoRackPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_RACK_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoRadioenlace.pregunta}")
	public void setMessagePopupEliminarTipoRadioenlacePregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_RADIOENLACE_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoRecursoOperativo.pregunta}")
	public void setMessagePopupEliminarTipoRecursoOperativoPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_RECURSOOPERATIVO_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoRecursoRadio.pregunta}")
	public void setMessagePopupEliminarTipoRecursoRadioPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_RECURSORADIO_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoSectorATC.pregunta}")
	public void setMessagePopupEliminarTipoSectorATCPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_SECTORATC_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoSistemaLocal.pregunta}")
	public void setMessagePopupEliminarTipoSistemaLocalPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_SISTEMALOCAL_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoTirada.pregunta}")
	public void setMessagePopupEliminarTipoTiradaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_TIRADA_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoTopologia.pregunta}")
	public void setMessagePopupEliminarTipoTopologiaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_TOPOLOGIA_PREGUNTA = texto;
	}

	@Value("${html.label.eliminar.tipoUbicacion.pregunta}")
	public void setMessagePopupEliminarTipoUbicacionPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_UBICACION_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoUsoAntena.pregunta}")
	public void setMessagePopupEliminarTipoUsoAntenaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_USOANTENA_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.tipoUsoTirada.pregunta}")
	public void setMessagePopupEliminarTipoUsoTiradaPregunta(final String texto) {
		POPUP_ELIMINAR_TIPO_USOTIRADA_PREGUNTA = texto;
	}
	
	@Value("${html.label.eliminar.version.pregunta}")
    public void setMessagePopupEliminarVersionPregunta(final String texto) {
		POPUP_ELIMINAR_VERSION_PREGUNTA = texto;
    }
	
    @Value("${error.400}")
    public void setError400(final String texto) {
    	ERROR_400 = texto;
    }
    
    @Value("${error.403}")
    public void setError403(final String texto) {
    	ERROR_403 = texto;
    }
    
    @Value("${error.404}")
    public void setError404(final String texto) {
    	ERROR_404 = texto;
    }
    
    @Value("${error.500}")
    public void setError500(final String texto) {
    	ERROR_400 = texto;
    }
    
    @Value("${error.desconocido}")
    public void setErrorDesconocido(final String texto) {
    	ERROR_DESCONOCIDO = texto;
    }

}
