package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para sistemas
 * 
 * @author jgm
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionMantenimiento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4951949478328724517L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGIONES_MANTENIMIENTO_SEQ")
	@SequenceGenerator(sequenceName = "regiones_mantenimiento_seq", initialValue = 1, allocationSize = 1 , name = "REGIONES_MANTENIMIENTO_SEQ")
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;

}
