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
 * @author eduardo.tubilleja
 *
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FrecuenciaATC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8576418876261269151L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRECUENCIAATC_SEQ")
	@SequenceGenerator(sequenceName = "frecuencia_atc_seq", initialValue = 1, allocationSize = 1 , name = "FRECUENCIAATC_SEQ")
	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String dependencia;

	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;

	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String tipoServicio;
	
	private Boolean frecuenciaBackup;
	
	@Size(max = 250, message = "{validation.max250}")
	private String titular;
	
	@Size(max = 250, message = "{validation.max250}")
	private String docOACI;
	
	@Size(max = 250, message = "{validation.max250}")
	private String observaciones;
	
}
