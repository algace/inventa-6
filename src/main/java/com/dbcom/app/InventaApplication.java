package com.dbcom.app;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dbcom.app.model.dao.AmbitoRepository;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dao.DocumentoRepository;
import com.dbcom.app.model.dao.EquipamientoRepository;
import com.dbcom.app.model.dao.NivelRepository;
import com.dbcom.app.model.dao.PermisosRepository;
import com.dbcom.app.model.dao.TipoBandaFrecuenciaRepository;
import com.dbcom.app.model.dao.TipoCapacidadEnlaceTTRepository;
import com.dbcom.app.model.dao.TipoConfiguracionTiradaRepository;
import com.dbcom.app.model.dao.TipoDependenciaRepository;
import com.dbcom.app.model.dao.TipoDocumentoRepository;
import com.dbcom.app.model.dao.TipoEmplazamientoRepository;
import com.dbcom.app.model.dao.TipoEnlaceTTRepository;
import com.dbcom.app.model.dao.TipoEquipoConfiguracionRadioRepository;
import com.dbcom.app.model.dao.TipoEstructuraRepository;
import com.dbcom.app.model.dao.TipoFuenteInformacionRepository;
import com.dbcom.app.model.dao.TipoInstalacionRepository;
import com.dbcom.app.model.dao.TipoInterfazOperacionRepository;
import com.dbcom.app.model.dao.TipoJerarquiaRepository;
import com.dbcom.app.model.dao.TipoLineaTelefonicaRepository;
import com.dbcom.app.model.dao.TipoModulacionRepository;
import com.dbcom.app.model.dao.TipoPolarizacionRepository;
import com.dbcom.app.model.dao.TipoRackRepository;
import com.dbcom.app.model.dao.TipoRadioenlaceRepository;
import com.dbcom.app.model.dao.TipoRecursoOperativoRepository;
import com.dbcom.app.model.dao.TipoSectorATCRepository;
import com.dbcom.app.model.dao.TipoSistemaLocalRepository;
import com.dbcom.app.model.dao.TipoTiradaRepository;
import com.dbcom.app.model.dao.TipoTopologiaRepository;
import com.dbcom.app.model.dao.TipoUbicacionRepository;
import com.dbcom.app.model.dao.TipoUsoAntenaRepository;
import com.dbcom.app.model.dao.TipoUsoTiradaRepository;
import com.dbcom.app.model.dao.VersionSWRepository;
import com.dbcom.app.model.entity.Ambito;
import com.dbcom.app.model.entity.Nivel;
import com.dbcom.app.model.entity.Permisos;
import com.dbcom.app.model.entity.TipoBandaFrecuencia;
import com.dbcom.app.model.entity.TipoCapacidadEnlaceTT;
import com.dbcom.app.model.entity.TipoConfiguracionTirada;
import com.dbcom.app.model.entity.TipoDependencia;
import com.dbcom.app.model.entity.TipoDocumento;
import com.dbcom.app.model.entity.TipoEmplazamiento;
import com.dbcom.app.model.entity.TipoEnlaceTT;
import com.dbcom.app.model.entity.TipoEquipoConfiguracionRadio;
import com.dbcom.app.model.entity.TipoEstructura;
import com.dbcom.app.model.entity.TipoFuenteInformacion;
import com.dbcom.app.model.entity.TipoInstalacion;
import com.dbcom.app.model.entity.TipoInterfazOperacion;
import com.dbcom.app.model.entity.TipoJerarquia;
import com.dbcom.app.model.entity.TipoLineaTelefonica;
import com.dbcom.app.model.entity.TipoModulacion;
import com.dbcom.app.model.entity.TipoPolarizacion;
import com.dbcom.app.model.entity.TipoRack;
import com.dbcom.app.model.entity.TipoRadioenlace;
import com.dbcom.app.model.entity.TipoRecursoOperativo;
import com.dbcom.app.model.entity.TipoSectorATC;
import com.dbcom.app.model.entity.TipoSistemaLocal;
import com.dbcom.app.model.entity.TipoTirada;
import com.dbcom.app.model.entity.TipoTopologia;
import com.dbcom.app.model.entity.TipoUbicacion;
import com.dbcom.app.model.entity.TipoUsoAntena;
import com.dbcom.app.model.entity.TipoUsoTirada;

@SpringBootApplication
public class InventaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaApplication.class, args);
	}
	
	/**
	 * Carga de la configuración inicial.
	 * NOTA: Cuando se tenga el modelo de datos definitivo se deberá trasladar este método a scripts DML
	 * 
	 * @param aplicacionRepository
	 * @param equipamientoRepository
	 * @param documentoRepository
	 * @param tipoBandaFrecuenciaRepository
	 * @param tipoCapacidadEnlaceTTRepository
	 * @param tipoConfiguracionTiradaRepository
	 * @param tipoDocumentoRepository
	 * @param tipoDependenciaRepository
	 * @param tipoEquipoConfiguracionRadioRepository
	 * @param tipoEmplazamientoRepository
	 * @param tipoEnlaceTTRepository
	 * @param tipoEstructuraRepository
	 * @param tipoFuenteInformacionRepository
	 * @param tipoInstalacionRepository
	 * @param tipoInterfazOperacionRepository
	 * @param tipoJerarquiaRepository
	 * @param tipoLineaTelefonicaRepository
	 * @param tipoModulacionRepository
	 * @param tipoPolarizacionRepository
	 * @param tipoRackRepository
	 * @param tipoRadioenlaceRepository
	 * @param tipoRecursoOperativoRepository
	 * @param tipoSectorATCRepository
	 * @param tipoSistemaLocalRepository
	 * @param tipoTiradaRepository
	 * @param tipoTopologiaRepository
	 * @param tipoUbicacionRepository
	 * @param tipoUsoAntenaRepository
	 * @param tipoUsoTiradaRepository
	 * @param versionRepository
	 * @param nivelRepository
	 * @param ambitoRepository
	 * @param permisosRepository
	 * @return
	 */
	@Bean
    public CommandLineRunner createDevelopmentData(final AplicacionSWRepository aplicacionRepository,
    									final EquipamientoRepository equipamientoRepository,
    									final DocumentoRepository documentoRepository, 
    									final TipoBandaFrecuenciaRepository tipoBandaFrecuenciaRepository,
    									final TipoCapacidadEnlaceTTRepository tipoCapacidadEnlaceTTRepository,
    									final TipoConfiguracionTiradaRepository tipoConfiguracionTiradaRepository,
    									final TipoDocumentoRepository tipoDocumentoRepository, 
    									final TipoDependenciaRepository tipoDependenciaRepository,
    									final TipoEquipoConfiguracionRadioRepository tipoEquipoConfiguracionRadioRepository,
    									final TipoEmplazamientoRepository tipoEmplazamientoRepository,
    									final TipoEnlaceTTRepository tipoEnlaceTTRepository,
    									final TipoEstructuraRepository tipoEstructuraRepository,
    									final TipoFuenteInformacionRepository tipoFuenteInformacionRepository,
    									final TipoInstalacionRepository tipoInstalacionRepository,
    									final TipoInterfazOperacionRepository tipoInterfazOperacionRepository,
    									final TipoJerarquiaRepository tipoJerarquiaRepository,
    									final TipoLineaTelefonicaRepository tipoLineaTelefonicaRepository,
    									final TipoModulacionRepository tipoModulacionRepository,
    									final TipoPolarizacionRepository tipoPolarizacionRepository,
    									final TipoRackRepository tipoRackRepository,
    									final TipoRadioenlaceRepository tipoRadioenlaceRepository,
    									final TipoRecursoOperativoRepository tipoRecursoOperativoRepository,
    									final TipoSectorATCRepository tipoSectorATCRepository,
    									final TipoSistemaLocalRepository tipoSistemaLocalRepository,
    									final TipoTiradaRepository tipoTiradaRepository,
    									final TipoTopologiaRepository tipoTopologiaRepository,
    									final TipoUbicacionRepository tipoUbicacionRepository,
    									final TipoUsoAntenaRepository tipoUsoAntenaRepository,
    									final TipoUsoTiradaRepository tipoUsoTiradaRepository,
                                        final VersionSWRepository versionRepository,
                                        final NivelRepository nivelRepository,
                                        final AmbitoRepository ambitoRepository,
                                        final PermisosRepository permisosRepository) {

	    return args -> {
	    	
	    	final TipoDependencia tipoDependencia0 = TipoDependencia.builder().id((short) 1).nombre("ATS").build();
	    	final TipoDependencia tipoDependencia1 = TipoDependencia.builder().id((short) 2).nombre("CNS").build();
	    	final TipoDependencia tipoDependencia2 = TipoDependencia.builder().id((short) 3).nombre("No aplica").build();
	    	final TipoDependencia tipoDependencia3 = TipoDependencia.builder().id((short) 4).nombre("Otras").build();
	    	tipoDependenciaRepository.saveAll(Arrays.asList(tipoDependencia0, tipoDependencia1, tipoDependencia2, tipoDependencia3));
	    	
	    	final TipoDocumento tipoDocumento0 = TipoDocumento.builder().id((short) 1).nombre("Características técnicas").build();
	    	final TipoDocumento tipoDocumento1 = TipoDocumento.builder().id((short) 2).nombre("Manual de usuario").build();
	    	final TipoDocumento tipoDocumento2 = TipoDocumento.builder().id((short) 3).nombre("Manual técnico de mantenimiento (MTM)").build();
	    	final TipoDocumento tipoDocumento3 = TipoDocumento.builder().id((short) 4).nombre("Otros").build();
	    	tipoDocumentoRepository.saveAll(Arrays.asList(tipoDocumento0, tipoDocumento1, tipoDocumento2, tipoDocumento3));
	    	
	    	final TipoEmplazamiento tipoEmplazamiento0 = TipoEmplazamiento.builder().id((short) 1).nombre("ACC").build();
	    	final TipoEmplazamiento tipoEmplazamiento1 = TipoEmplazamiento.builder().id((short) 2).nombre("BLOQUE-TÉCNICO").build();
	    	final TipoEmplazamiento tipoEmplazamiento2 = TipoEmplazamiento.builder().id((short) 3).nombre("CECOM").build();
	    	final TipoEmplazamiento tipoEmplazamiento3 = TipoEmplazamiento.builder().id((short) 4).nombre("GP").build();
	    	final TipoEmplazamiento tipoEmplazamiento4 = TipoEmplazamiento.builder().id((short) 5).nombre("LLZ").build();
	    	final TipoEmplazamiento tipoEmplazamiento5 = TipoEmplazamiento.builder().id((short) 6).nombre("NDB").build();
	    	final TipoEmplazamiento tipoEmplazamiento6 = TipoEmplazamiento.builder().id((short) 7).nombre("RADAR").build();
	    	final TipoEmplazamiento tipoEmplazamiento7 = TipoEmplazamiento.builder().id((short) 8).nombre("TWR").build();
	    	final TipoEmplazamiento tipoEmplazamiento8 = TipoEmplazamiento.builder().id((short) 9).nombre("VOR").build();
	    	final TipoEmplazamiento tipoEmplazamiento9 = TipoEmplazamiento.builder().id((short) 10).nombre("Otros").build();
	    	tipoEmplazamientoRepository.saveAll(Arrays.asList(tipoEmplazamiento0, tipoEmplazamiento1, tipoEmplazamiento2, 
	    			tipoEmplazamiento3, tipoEmplazamiento4, tipoEmplazamiento5, tipoEmplazamiento6, tipoEmplazamiento7, 
	    			tipoEmplazamiento8, tipoEmplazamiento9));
	    	
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio0 = TipoEquipoConfiguracionRadio.builder().id((short) 1).nombre("Receptor radio (configuración 1+0)").ite("rx.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio1 = TipoEquipoConfiguracionRadio.builder().id((short) 2).nombre("Receptor radio (configuración M+N) Equipos M").ite("rx.m.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio2 = TipoEquipoConfiguracionRadio.builder().id((short) 3).nombre("Receptor radio (configuración M+N) Equipos N").ite("rx.s.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio3 = TipoEquipoConfiguracionRadio.builder().id((short) 4).nombre("Receptor radio emergencia (configuración 1+0)").ite("rx.e.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio4 = TipoEquipoConfiguracionRadio.builder().id((short) 5).nombre("Receptor radio principal (configuración 1+1)").ite("rx.m.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio5 = TipoEquipoConfiguracionRadio.builder().id((short) 6).nombre("Receptor radio reserva (configuración 1+1)").ite("rx.s.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio6 = TipoEquipoConfiguracionRadio.builder().id((short) 7).nombre("Transmisor radio (configuración 1+0)").ite("tx.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio7 = TipoEquipoConfiguracionRadio.builder().id((short) 8).nombre("Transmisor radio (configuración M+N) Equipos M").ite("tx.m.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio8 = TipoEquipoConfiguracionRadio.builder().id((short) 9).nombre("Transmisor radio (configuración M+N) Equipos N").ite("tx.s.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio9 = TipoEquipoConfiguracionRadio.builder().id((short) 10).nombre("Transmisor radio emergencia (configuración 1+0)").ite("tx.e.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio10 = TipoEquipoConfiguracionRadio.builder().id((short) 11).nombre("Transmisor radio principal (configuración 1+1)").ite("tx.m.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio11 = TipoEquipoConfiguracionRadio.builder().id((short) 12).nombre("Transmisor radio reserva (configuración 1+1)").ite("tx.s.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio12 = TipoEquipoConfiguracionRadio.builder().id((short) 13).nombre("Transceptor radio (configuración 1+0)").ite("txrx.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio13 = TipoEquipoConfiguracionRadio.builder().id((short) 14).nombre("Transceptor radio (configuración M+N) Equipos M").ite("txrx.m.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio14 = TipoEquipoConfiguracionRadio.builder().id((short) 15).nombre("Transceptor radio (configuración M+N) Equipos N").ite("txrx.s.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio15 = TipoEquipoConfiguracionRadio.builder().id((short) 16).nombre("Transceptor radio principal (configuración 1+1)").ite("txrx.m.").build();
	    	final TipoEquipoConfiguracionRadio tipoEquipoConfiguracionRadio16 = TipoEquipoConfiguracionRadio.builder().id((short) 17).nombre("Transceptor radio reserva (configuración 1+1)").ite("txrx.s.").build();
	    	tipoEquipoConfiguracionRadioRepository.saveAll(Arrays.asList(tipoEquipoConfiguracionRadio0, tipoEquipoConfiguracionRadio1,
	    			tipoEquipoConfiguracionRadio2,tipoEquipoConfiguracionRadio3,tipoEquipoConfiguracionRadio4,tipoEquipoConfiguracionRadio5,
	    			tipoEquipoConfiguracionRadio6,tipoEquipoConfiguracionRadio7,tipoEquipoConfiguracionRadio8,tipoEquipoConfiguracionRadio9,
	    			tipoEquipoConfiguracionRadio10,tipoEquipoConfiguracionRadio11,tipoEquipoConfiguracionRadio12,tipoEquipoConfiguracionRadio13,
	    			tipoEquipoConfiguracionRadio14,tipoEquipoConfiguracionRadio15,tipoEquipoConfiguracionRadio16));

	    	final TipoInterfazOperacion tipoInterfazOperacion0 = TipoInterfazOperacion.builder().id((short) 1).nombre("ANALOGICO").build();
	    	final TipoInterfazOperacion tipoInterfazOperacion1 = TipoInterfazOperacion.builder().id((short) 2).nombre("DATOS").build();
	    	final TipoInterfazOperacion tipoInterfazOperacion2 = TipoInterfazOperacion.builder().id((short) 3).nombre("VOIP").build();
	    	tipoInterfazOperacionRepository.saveAll(Arrays.asList(tipoInterfazOperacion0,tipoInterfazOperacion1,tipoInterfazOperacion2));
	    	
	    	final TipoModulacion tipoModulacion0 = TipoModulacion.builder().id((short) 1).nombre("128QAM").descripcionCorta("Quadrature Amplitude Modulation").descripcionLarga("").build();
	    	final TipoModulacion tipoModulacion1 = TipoModulacion.builder().id((short) 2).nombre("16QAM").descripcionCorta("Quadrature Amplitude Modulation").descripcionLarga("").build();
	    	final TipoModulacion tipoModulacion2 = TipoModulacion.builder().id((short) 3).nombre("4QAM").descripcionCorta("Quadrature Amplitude Modulation").descripcionLarga("").build();
	    	final TipoModulacion tipoModulacion3 = TipoModulacion.builder().id((short) 4).nombre("ASK").descripcionCorta("Amplitude Shift Keying").descripcionLarga("Modulación por desplazamiento de amplitud. Se representan los datos digitales (moduladora) como variaciones de la amplitud de la onda portadora.").build();
	    	final TipoModulacion tipoModulacion4 = TipoModulacion.builder().id((short) 5).nombre("FSK").descripcionCorta("Frequency Shift Keying").descripcionLarga("Modulación por desplazamiento de frecuencia. Técnica de transmisión digital binaria utilizando dos frecuencias distintas.").build();
	    	final TipoModulacion tipoModulacion5 = TipoModulacion.builder().id((short) 6).nombre("QAM Cuantizada").descripcionCorta("Quadrature Amplitude Modulation").descripcionLarga("Modulación de amplitud en cuadratura cuantizada. Es una modulación lineal que permite modular en doble banda lateral dos portadoras de la misma frecuencia desfasadas 90º.").build();
	    	final TipoModulacion tipoModulacion6 = TipoModulacion.builder().id((short) 7).nombre("QPSK").descripcionCorta("Quadriphase PSK").descripcionLarga("Modulación por desplazamiento de fase cuaternaria. Caso particular de PSK.").build();
	    	final TipoModulacion tipoModulacion7 = TipoModulacion.builder().id((short) 8).nombre("PSK").descripcionCorta("Phase Shift Keying").descripcionLarga("Modulación por desplazamiento de fase. La fase de la onda portadora varía directamente de acuerdo con la señal moduladora, resultando una señal de modulación en fase.").build();
	    	final TipoModulacion tipoModulacion8 = TipoModulacion.builder().id((short) 9).nombre("N/A").descripcionCorta("No asignado").descripcionLarga("No asignado").build();
	    	tipoModulacionRepository.saveAll(Arrays.asList(tipoModulacion0,tipoModulacion1,tipoModulacion2,tipoModulacion3,tipoModulacion4,
	    			tipoModulacion5,tipoModulacion6,tipoModulacion7,tipoModulacion8));
	    	
	    	final TipoPolarizacion tipoPolarizacion0 = TipoPolarizacion.builder().id((short) 1).nombre("CRUZADA").build(); 
	    	final TipoPolarizacion tipoPolarizacion1 = TipoPolarizacion.builder().id((short) 2).nombre("HORIZONTAL").build(); 
	    	final TipoPolarizacion tipoPolarizacion2 = TipoPolarizacion.builder().id((short) 3).nombre("VERTICAL").build(); 
	    	final TipoPolarizacion tipoPolarizacion3 = TipoPolarizacion.builder().id((short) 4).nombre("Otros").build(); 
	    	tipoPolarizacionRepository.saveAll(Arrays.asList(tipoPolarizacion0,tipoPolarizacion1,tipoPolarizacion2,tipoPolarizacion3));
	    	
	    	final TipoTopologia tipoTopologia0 = TipoTopologia.builder().id((short) 1).nombre("ANILLO").build();
	    	final TipoTopologia tipoTopologia1 = TipoTopologia.builder().id((short)  2).nombre("ESTRELLA").build();
	    	final TipoTopologia tipoTopologia2 = TipoTopologia.builder().id((short)  3).nombre("PaP").build();
	    	final TipoTopologia tipoTopologia3 = TipoTopologia.builder().id((short)  4).nombre("Otros").build();
	    	tipoTopologiaRepository.saveAll(Arrays.asList(tipoTopologia0,tipoTopologia1,tipoTopologia2,tipoTopologia3));
	    	
	    	final TipoBandaFrecuencia tipoBandaFrecuencia0 = TipoBandaFrecuencia.builder().id((short) 1).nombre("MF").build();
	    	final TipoBandaFrecuencia tipoBandaFrecuencia1 = TipoBandaFrecuencia.builder().id((short) 2).nombre("HF").build();
	    	final TipoBandaFrecuencia tipoBandaFrecuencia2 = TipoBandaFrecuencia.builder().id((short) 3).nombre("VHF").build();
	    	final TipoBandaFrecuencia tipoBandaFrecuencia3 = TipoBandaFrecuencia.builder().id((short) 4).nombre("UHF").build();
	    	final TipoBandaFrecuencia tipoBandaFrecuencia4 = TipoBandaFrecuencia.builder().id((short) 5).nombre("SHF").build();
	    	tipoBandaFrecuenciaRepository.saveAll(Arrays.asList(tipoBandaFrecuencia0,tipoBandaFrecuencia1,tipoBandaFrecuencia2,tipoBandaFrecuencia3,tipoBandaFrecuencia4));
	    	
	    	final TipoSectorATC tipoSectorATC0 = TipoSectorATC.builder().id((short) 1).nombre("Aeropuerto").build();
	    	final TipoSectorATC tipoSectorATC1 = TipoSectorATC.builder().id((short) 2).nombre("General").build();
	    	final TipoSectorATC tipoSectorATC2 = TipoSectorATC.builder().id((short) 3).nombre("Ruta").build();
	    	final TipoSectorATC tipoSectorATC3 = TipoSectorATC.builder().id((short) 4).nombre("TMA").build();
	    	tipoSectorATCRepository.saveAll(Arrays.asList(tipoSectorATC0,tipoSectorATC1,tipoSectorATC2,tipoSectorATC3));

	    	final TipoFuenteInformacion tipoFuenteInformacion0 = TipoFuenteInformacion.builder().id((short) 1).nombre("AIP").build();
	    	final TipoFuenteInformacion tipoFuenteInformacion1 = TipoFuenteInformacion.builder().id((short) 2).nombre("Personal Local").build();
	    	final TipoFuenteInformacion tipoFuenteInformacion2 = TipoFuenteInformacion.builder().id((short) 3).nombre("SACTA").build();
	    	final TipoFuenteInformacion tipoFuenteInformacion3 = TipoFuenteInformacion.builder().id((short) 4).nombre("Otros").build();
	    	tipoFuenteInformacionRepository.saveAll(Arrays.asList(tipoFuenteInformacion0,tipoFuenteInformacion1,tipoFuenteInformacion2,tipoFuenteInformacion3));

	    	final TipoRecursoOperativo tipoRecursoOperativo0 = TipoRecursoOperativo.builder().id((short) 1).nombre("CANAL RADIO").build();
	    	final TipoRecursoOperativo tipoRecursoOperativo1 = TipoRecursoOperativo.builder().id((short) 2).nombre("LÍNEA TELEFÓNICA").build();
	    	final TipoRecursoOperativo tipoRecursoOperativo2 = TipoRecursoOperativo.builder().id((short) 3).nombre("LOCAL").build();
	    	final TipoRecursoOperativo tipoRecursoOperativo3 = TipoRecursoOperativo.builder().id((short) 4).nombre("REMOTO").build();
	    	final TipoRecursoOperativo tipoRecursoOperativo4 = TipoRecursoOperativo.builder().id((short) 5).nombre("Otros").build();
	    	tipoRecursoOperativoRepository.saveAll(Arrays.asList(tipoRecursoOperativo0,tipoRecursoOperativo1,tipoRecursoOperativo2,tipoRecursoOperativo3,tipoRecursoOperativo4));
	    	
	    	final TipoLineaTelefonica lineaTelefonica0 = TipoLineaTelefonica.builder().id((short)1).nombre("BC").build();
	    	final TipoLineaTelefonica lineaTelefonica1 = TipoLineaTelefonica.builder().id((short)2).nombre("BCA").build();
	    	final TipoLineaTelefonica lineaTelefonica2 = TipoLineaTelefonica.builder().id((short)3).nombre("BL").build();
	    	final TipoLineaTelefonica lineaTelefonica3 = TipoLineaTelefonica.builder().id((short)4).nombre("E&M").build();
	    	final TipoLineaTelefonica lineaTelefonica4 = TipoLineaTelefonica.builder().id((short)5).nombre("FXO/PABX").build();
	    	final TipoLineaTelefonica lineaTelefonica5 = TipoLineaTelefonica.builder().id((short)6).nombre("FXS/MF/TFU").build();
	    	final TipoLineaTelefonica lineaTelefonica6 = TipoLineaTelefonica.builder().id((short)7).nombre("LCE").build();
	    	final TipoLineaTelefonica lineaTelefonica7 = TipoLineaTelefonica.builder().id((short)8).nombre("LCEN").build();
	    	final TipoLineaTelefonica lineaTelefonica8 = TipoLineaTelefonica.builder().id((short)9).nombre("LDRD").build();
	    	final TipoLineaTelefonica lineaTelefonica9 = TipoLineaTelefonica.builder().id((short)10).nombre("N5").build();
	    	final TipoLineaTelefonica lineaTelefonica10 = TipoLineaTelefonica.builder().id((short)11).nombre("QSIG").build();
	    	final TipoLineaTelefonica lineaTelefonica11 = TipoLineaTelefonica.builder().id((short)12).nombre("R2").build();
	    	final TipoLineaTelefonica lineaTelefonica12 = TipoLineaTelefonica.builder().id((short)13).nombre("RDSI").build();
	    	final TipoLineaTelefonica lineaTelefonica13 = TipoLineaTelefonica.builder().id((short)14).nombre("RTB").build();
	    	final TipoLineaTelefonica lineaTelefonica14 = TipoLineaTelefonica.builder().id((short)15).nombre("Otro").build();
	    	tipoLineaTelefonicaRepository.saveAll(Arrays.asList(lineaTelefonica0,lineaTelefonica1,lineaTelefonica2,lineaTelefonica3,lineaTelefonica4,lineaTelefonica5,lineaTelefonica6,
	    			lineaTelefonica7,lineaTelefonica8,lineaTelefonica9,lineaTelefonica10,lineaTelefonica11,lineaTelefonica12,lineaTelefonica13,lineaTelefonica14));
	    	
	    	final TipoUbicacion tipoUbicacion0 = TipoUbicacion.builder().id((short)1).nombre("Campo de Antenas").build();
	    	final TipoUbicacion tipoUbicacion1 = TipoUbicacion.builder().id((short)2).nombre("Sala de Control").build();
	    	final TipoUbicacion tipoUbicacion2 = TipoUbicacion.builder().id((short)3).nombre("Sala de Equipos").build();
	    	final TipoUbicacion tipoUbicacion3 = TipoUbicacion.builder().id((short)4).nombre("Sala de Supervisión Técnica").build();
	    	tipoUbicacionRepository.saveAll(Arrays.asList(tipoUbicacion0, tipoUbicacion1, tipoUbicacion2, tipoUbicacion3));
	    	
	    	final TipoInstalacion tipoInstalacion0 = TipoInstalacion.builder().id((short)1).nombre("EQUIPAMIENTO").build();
	    	final TipoInstalacion tipoInstalacion1 = TipoInstalacion.builder().id((short)2).nombre("POSICIÓN").build();
	    	final TipoInstalacion tipoInstalacion2 = TipoInstalacion.builder().id((short)3).nombre("RACK").build();
	    	tipoInstalacionRepository.saveAll(Arrays.asList(tipoInstalacion0,tipoInstalacion1,tipoInstalacion2));
	    	
	    	final TipoEnlaceTT tipoEnlaceTT0 = TipoEnlaceTT.builder().id((short)1).nombre("Cable Coaxial").build();
	    	final TipoEnlaceTT tipoEnlaceTT1 = TipoEnlaceTT.builder().id((short)2).nombre("Cable de Cobre").build();
	    	final TipoEnlaceTT tipoEnlaceTT2 = TipoEnlaceTT.builder().id((short)3).nombre("Fibra Óptica").build();
	    	final TipoEnlaceTT tipoEnlaceTT3 = TipoEnlaceTT.builder().id((short)4).nombre("Fibra Oscura").build();
	    	final TipoEnlaceTT tipoEnlaceTT4 = TipoEnlaceTT.builder().id((short)5).nombre("Radioenlace").build();
	    	final TipoEnlaceTT tipoEnlaceTT5 = TipoEnlaceTT.builder().id((short)6).nombre("Otro").build();
	    	tipoEnlaceTTRepository.saveAll(Arrays.asList(tipoEnlaceTT0,tipoEnlaceTT1,tipoEnlaceTT2,tipoEnlaceTT3,tipoEnlaceTT4,tipoEnlaceTT5));
	    	
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT0 = TipoCapacidadEnlaceTT.builder().id((short)1).nombre("2 Mbps").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT1 = TipoCapacidadEnlaceTT.builder().id((short)2).nombre("2x2 Mbps").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT2 = TipoCapacidadEnlaceTT.builder().id((short)3).nombre("4x2 Mbps").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT3 = TipoCapacidadEnlaceTT.builder().id((short)4).nombre("8x2 Mbps").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT4 = TipoCapacidadEnlaceTT.builder().id((short)5).nombre("16x2 Mbps").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT5 = TipoCapacidadEnlaceTT.builder().id((short)6).nombre("32x2 Mbps").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT6 = TipoCapacidadEnlaceTT.builder().id((short)7).nombre("64x2 Mbps").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT7 = TipoCapacidadEnlaceTT.builder().id((short)8).nombre("STM-1").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT8 = TipoCapacidadEnlaceTT.builder().id((short)9).nombre("STM-2").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT9 = TipoCapacidadEnlaceTT.builder().id((short)10).nombre("STM-4").build();
	    	final TipoCapacidadEnlaceTT tipoCapacidadEnlaceTT10 = TipoCapacidadEnlaceTT.builder().id((short)11).nombre("Otro").build();
	    	tipoCapacidadEnlaceTTRepository.saveAll(Arrays.asList(tipoCapacidadEnlaceTT0,tipoCapacidadEnlaceTT1,tipoCapacidadEnlaceTT2,tipoCapacidadEnlaceTT3,
	    			tipoCapacidadEnlaceTT4,tipoCapacidadEnlaceTT5,tipoCapacidadEnlaceTT6,tipoCapacidadEnlaceTT7,tipoCapacidadEnlaceTT8,tipoCapacidadEnlaceTT9,
	    			tipoCapacidadEnlaceTT10));
	    	
	    	final TipoRadioenlace tipoRadioEnlace0 = TipoRadioenlace.builder().id((short)1).nombre("Analógico").build();
	    	final TipoRadioenlace tipoRadioEnlace1 = TipoRadioenlace.builder().id((short)2).nombre("Digital").build();
	    	final TipoRadioenlace tipoRadioEnlace2 = TipoRadioenlace.builder().id((short)3).nombre("NA").build();
	    	tipoRadioenlaceRepository.saveAll(Arrays.asList(tipoRadioEnlace0,tipoRadioEnlace1,tipoRadioEnlace2));
	    	
	    	final TipoSistemaLocal tipoSistemaLocal0 = TipoSistemaLocal.builder().id((short)1).nombre("Grabador").build();
	    	final TipoSistemaLocal tipoSistemaLocal1 = TipoSistemaLocal.builder().id((short)2).nombre("Radio").build();
	    	final TipoSistemaLocal tipoSistemaLocal2 = TipoSistemaLocal.builder().id((short)3).nombre("SCV").build();
	    	final TipoSistemaLocal tipoSistemaLocal3 = TipoSistemaLocal.builder().id((short)4).nombre("Tierra-Tierra").build();
	    	final TipoSistemaLocal tipoSistemaLocal4 = TipoSistemaLocal.builder().id((short)5).nombre("Otros").build();
	    	tipoSistemaLocalRepository.saveAll(Arrays.asList(tipoSistemaLocal0, tipoSistemaLocal1, tipoSistemaLocal2, tipoSistemaLocal3, tipoSistemaLocal4));
	    	
	    	final TipoRack tipoRack0 = TipoRack.builder().id((short)1).nombre("REDAN").build();
	    	final TipoRack tipoRack1 = TipoRack.builder().id((short)2).nombre("SACTA").build();
	    	final TipoRack tipoRack2 = TipoRack.builder().id((short)3).nombre("Tierra-Aire").build();
	    	final TipoRack tipoRack3 = TipoRack.builder().id((short)4).nombre("Tierra-Tierra").build();
	    	final TipoRack tipoRack4 = TipoRack.builder().id((short)5).nombre("Otros").build();
	    	tipoRackRepository.saveAll(Arrays.asList(tipoRack0, tipoRack1, tipoRack2, tipoRack3, tipoRack4));
	    	
	    	final TipoEstructura tipoEstructura0 = TipoEstructura.builder().id((short)1).nombre("MASTIL").build();
	    	final TipoEstructura tipoEstructura1 = TipoEstructura.builder().id((short)2).nombre("PLATAFORMA").build();
	    	final TipoEstructura tipoEstructura2 = TipoEstructura.builder().id((short)3).nombre("TORRE").build();
	    	tipoEstructuraRepository.saveAll(Arrays.asList(tipoEstructura0, tipoEstructura1, tipoEstructura2));
	    	
	    	final TipoUsoAntena tipoUsoAntena0 = TipoUsoAntena.builder().id((short)1).nombre("RX").build();
	    	final TipoUsoAntena tipoUsoAntena1 = TipoUsoAntena.builder().id((short)2).nombre("TX").build();
	    	final TipoUsoAntena tipoUsoAntena2 = TipoUsoAntena.builder().id((short)3).nombre("TX-RX").build();
	    	final TipoUsoAntena tipoUsoAntena3 = TipoUsoAntena.builder().id((short)4).nombre("NO DEFINIDO").build();
	    	tipoUsoAntenaRepository.saveAll(Arrays.asList(tipoUsoAntena0,tipoUsoAntena1,tipoUsoAntena2,tipoUsoAntena3));
	    	
	    	final TipoUsoTirada tipoUsoTirada0 = TipoUsoTirada.builder().id((short)1).nombre("ALTERNATIVO").build();
	    	final TipoUsoTirada tipoUsoTirada1 = TipoUsoTirada.builder().id((short)2).nombre("EMERGENCIA").build();
	    	final TipoUsoTirada tipoUsoTirada2 = TipoUsoTirada.builder().id((short)3).nombre("PPAL").build();
	    	final TipoUsoTirada tipoUsoTirada3 = TipoUsoTirada.builder().id((short)4).nombre("URR").build();
	    	tipoUsoTiradaRepository.saveAll(Arrays.asList(tipoUsoTirada0, tipoUsoTirada1, tipoUsoTirada2, tipoUsoTirada3));
	    	
	    	final TipoConfiguracionTirada tipoConfiguracionTirada0 = TipoConfiguracionTirada.builder().id((short)1).nombre("1+0").build();
	    	final TipoConfiguracionTirada tipoConfiguracionTirada1 = TipoConfiguracionTirada.builder().id((short)2).nombre("1+1").build();
	    	final TipoConfiguracionTirada tipoConfiguracionTirada2 = TipoConfiguracionTirada.builder().id((short)3).nombre("M+N").build();
	    	final TipoConfiguracionTirada tipoConfiguracionTirada3 = TipoConfiguracionTirada.builder().id((short)4).nombre("TRANSCEPTOR").build();
	    	tipoConfiguracionTiradaRepository.saveAll(Arrays.asList(tipoConfiguracionTirada0,tipoConfiguracionTirada1,tipoConfiguracionTirada2,tipoConfiguracionTirada3));
	    	
	    	final TipoTirada tipoTirada0 = TipoTirada.builder().id((short)1).nombre("Ninguno").build();
	    	final TipoTirada tipoTirada1 = TipoTirada.builder().id((short)2).nombre("Tierra-Aire").build();
	    	final TipoTirada tipoTirada2 = TipoTirada.builder().id((short)3).nombre("Tierra-Aire-Reserva").build();
	    	final TipoTirada tipoTirada3 = TipoTirada.builder().id((short)4).nombre("Tierra-Tierra").build();
	    	tipoTiradaRepository.saveAll(Arrays.asList(tipoTirada0,tipoTirada1,tipoTirada2,tipoTirada3));
	    	
	    	final TipoJerarquia tipoJerarquia0 = TipoJerarquia.builder().id((short)1).nombre("SDH").build();
	    	final TipoJerarquia tipoJerarquia1 = TipoJerarquia.builder().id((short)2).nombre("PDH").build();
	    	final TipoJerarquia tipoJerarquia2 = TipoJerarquia.builder().id((short)3).nombre("NA").build();
	    	tipoJerarquiaRepository.saveAll(Arrays.asList(tipoJerarquia0, tipoJerarquia1, tipoJerarquia2));
	    	
	    	final Nivel nivel0= Nivel.builder().nombre("Administrador").build();
	    	final Nivel nivel1= Nivel.builder().nombre("Consulta").build();
	    	final Nivel nivel2= Nivel.builder().nombre("Nivel 1").build();
	    	final Nivel nivel3= Nivel.builder().nombre("Nivel 2").build();
	    	final Nivel nivel4= Nivel.builder().nombre("Nivel 3").build();
	    	final Nivel nivel5= Nivel.builder().nombre("Nivel 4").build();
	    	nivelRepository.saveAll(Arrays.asList(nivel0, nivel1, nivel2, nivel3, nivel4, nivel5));
	    	
	    	final Ambito ambito0= Ambito.builder().nombre("Global").build();
	    	final Ambito ambito1= Ambito.builder().nombre("Región").build();
	    	final Ambito ambito2= Ambito.builder().nombre("Sector").build();
	    	final Ambito ambito3= Ambito.builder().nombre("Dependencia").build();
	    	ambitoRepository.saveAll(Arrays.asList(ambito0, ambito1, ambito2, ambito3));
	    	
	    	final Permisos permisos0= Permisos.builder().lecturaEscritura(true).soloLectura(false).build();
	    	final Permisos permisos1= Permisos.builder().lecturaEscritura(false).soloLectura(true).build();
	    	permisosRepository.saveAll(Arrays.asList(permisos0, permisos1));
	    	
//	    	final VersionSW version1 = VersionSW.builder().id(1L).nombre("version1").descripcion("dedadsad sadsaddsafdsa dsfdsafdsfdsa fdsfsdf").build();
//	    	final VersionSW version2 = VersionSW.builder().id(2L).nombre("version2").descripcion("dedadsad sadsaddsafdsa dsfdsafdsfdsa fdsfsdsdfdsfdsafdsafdsfdsfdsfdsafdsafdsaf").build();
//	    	final VersionSW version3 = VersionSW.builder().id(3L).nombre("version3").descripcion("dedadsad sadsaddsafdsa dsfdsafdsfdsa fdsfsdsdfdsfdsafdsafdsfdsfdsfdsafdsafdsaf").build();
//	    	final VersionSW version4 = VersionSW.builder().id(4L).nombre("version4").descripcion("dedadsadafdsafdsfdsfdsfdsafdsafdsaf").build();
//	    	versionRepository.saveAll(Arrays.asList(version1, version2, version3, version4));
//	    		
//	    	final List<Version> versiones = new ArrayList<>();
//	    	for (int i=5; i<1001; i++) {
//	    		Version versionTemp = Version.builder().id((long)i).nombre("version"+i).descripcion("descripcion"+i).build();
//	    		versiones.add(versionTemp);
//	    	}
//	    	versionRepository.saveAll(versiones);
//	    	
//	    	Equipamiento equipamiento1 = Equipamiento.builder()
//	    			.id(1L)
//	    			.nombre("equipamiento1")
//	    			.sistema("dfdsfdsfdsafa")
//	    			.subsistema("sdfdsfdsfdsafsad")
//	    			.marca("Marca")
//	    			.modelo("Modelor")
//	    			.caracteristicas("sdasdasdasa")
//	    			.descripcion("xczxczxczxczxczc")
//	    			.entradas((short)2)
//	    			.salidas((short)3)
//	    			.ganancia(12123.33)
//	    			.perdida(123678.33)
//	    			.build();
//	    	Equipamiento equipamiento2 = Equipamiento.builder()
//	    			.id(2L)
//	    			.nombre("equipamiento2")
//	    			.sistema("dfdsfdsfdsafa")
//	    			.subsistema("sdfdsfdsfdsafsad")
//	    			.marca("Marca")
//	    			.caracteristicas("sdasdasdasa")
//	    			.descripcion("xczxczxczxczxczc")
//	    			.modelo("Modelor")
//	    			.entradas((short)2)
//	    			.salidas((short)3)
//	    			.ganancia(902123.33)
//	    			.perdida(120123.01)
//	    			.build();
//	    	equipamientoRepository.saveAll(Arrays.asList(equipamiento1, equipamiento2));
//	    	    	
//	    	
//	    	AplicacionSW aplicacion = AplicacionSW.builder().id(1L).nombre("App1").archivo("Archivo1").fecha(LocalDate.now()).hora(LocalTime.now()).build();
//	    	AplicacionSW aplicacion1 = AplicacionSW.builder().id(2L).nombre("App2").archivo("Archivo2").fecha(LocalDate.now()).hora(LocalTime.now()).build();
//	    	AplicacionSW aplicacion2 = AplicacionSW.builder().id(3L).nombre("App3").archivo("Archivo3").fecha(LocalDate.now()).hora(LocalTime.now()).build();
//	    	AplicacionSW aplicacion3 = AplicacionSW.builder().id(4L).nombre("App4").archivo("Archivo4").fecha(LocalDate.now()).hora(LocalTime.now()).build();
//	    	aplicacion.getVersionesSW().addAll(Arrays.asList(versionRepository.findById(1L).get(), versionRepository.findById(2L).get()));
//	    	aplicacion.getEquipamientos().addAll(Arrays.asList(equipamiento1, equipamiento2));
//	    	
//	    	aplicacion1.getVersionesSW().addAll(Arrays.asList(versionRepository.findById(1L).get(), versionRepository.findById(2L).get()));
//	    	aplicacion1.getEquipamientos().addAll(Arrays.asList(equipamiento1, equipamiento2));
//	    	
//	    	aplicacionRepository.saveAll(Arrays.asList(aplicacion, aplicacion1, aplicacion2, aplicacion3));
//	    	
//	    	Documento documento0 = Documento.builder().id(1L).nombre("Doc1").descripcion("descripcion1").tipoDocumento(tipoDocumento0).equipamiento(equipamiento1).tipo("pdf").build();
//	    	Documento documento1 = Documento.builder().id(2L).nombre("Doc2").descripcion("descripcion2").tipoDocumento(tipoDocumento0).equipamiento(equipamiento1).tipo("pdf").build();
//	    	Documento documento2 = Documento.builder().id(3L).nombre("Doc3").descripcion("descripcion3").tipoDocumento(tipoDocumento3).equipamiento(equipamiento2).tipo("pdf").build();
//	    	documentoRepository.saveAll(Arrays.asList(documento0, documento1, documento2));
	    	
	    };
	}
}
