package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
public class Ambito implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4508351627575423727L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AMBITO_SEQ")
	@SequenceGenerator(sequenceName = "ambito_seq", initialValue = 1, allocationSize = 1 , name = "AMBITO_SEQ")
	private Long id;
	
	@Size(max = 50, message = "{validation.max50}")
	private String nombre;
	
	@OneToOne(mappedBy = "ambito", cascade = CascadeType.ALL)
	private Rol rol;
	
}
