package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="servicioRadio_id")
	@NotNull(message = "{validation.notNull}")
	private ServicioRadio tipoServicio;
	
	@NotNull(message = "{validation.notNull}")
	private String frecuenciaBackup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="titular_id")
	@NotNull(message = "{validation.notNull}")
	private Propietario titular;
	
	//@Size(max = 250, message = "{validation.max250}")
	//private String docOACI;
	
	private String docOACITipo;
	
	private String docOACIRadio;
	
	@Max(999)
	private Integer docOACIFL;
	
	private String docOACILatitud;

	private String docOACILongitud;
	
	@Size(max = 250, message = "{validation.max250}")
	private String observaciones;
	
}
