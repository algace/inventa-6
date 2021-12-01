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
public class Frecuencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1534422614709935764L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRECUENCIA_SEQ")
	@SequenceGenerator(sequenceName = "frecuencia_seq", initialValue = 1, allocationSize = 1 , name = "FRECUENCIA_SEQ")
	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String valor;

	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String unidad;

	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String banda;
	
	@Size(max = 250, message = "{validation.max250}")
	private String fechaPublicacion;
	
	@Size(max = 250, message = "{validation.max250}")
	private String fuente;
	
	@Size(max = 250, message = "{validation.max250}")
	private String observaciones;
	
}
