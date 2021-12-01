package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para tipos de instalación
 * 
 * @author jose.vallve
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoInstalacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5723758221351423716L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOINSTALACION_SEQ")
	@SequenceGenerator(sequenceName = "tipoinstalacion_seq", initialValue = 1, allocationSize = 1 , name = "TIPOINSTALACION_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
}
