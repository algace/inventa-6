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
 * Entidad para tipos de configuración de radioenlace
 * 
 * @author jose.vallve
 */
@Entity
//Establecemos un nombre 'corto' a la tabla
@Table(name = "TIPO_CONF_RADIOENLACE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoConfiguracionRadioenlace implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3359840147235979998L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOCONFRADIOENLACE_SEQ")
	@SequenceGenerator(sequenceName = "tipoconfradioenlace_seq", initialValue = 1, allocationSize = 1 , name = "TIPOCONFRADIOENLACE_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
}
