package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para tipos de equipos/configuraciones de radio
 * 
 * @author jose.vallve
 */
@Entity
// Establecemos un nombre 'corto' a la tabla
@Table(name = "TIPO_EQUIPO_CONF_RADIO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoEquipoConfiguracionRadio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3185974424833741577L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOEQUIPOCONFRADIO_SEQ")
	@SequenceGenerator(sequenceName = "tipoequipoconfradio_seq", initialValue = 1, allocationSize = 1 , name = "TIPOEQUIPOCONFRADIO_SEQ")
	private Short id;
	
	@Size(min = 1, max = 120, message = "{validation.min1max120}")
	private String nombre;

	@Size(max = 20, message = "{validation.max20}")
	private String ite;
	
}
