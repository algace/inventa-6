package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
public class Permisos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1914400204924092682L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISOS_SEQ")
	@SequenceGenerator(sequenceName = "permisos_seq", initialValue = 1, allocationSize = 1 , name = "PERMISOS_SEQ")
	private Long id;
	
	private Boolean soloLectura;

	private Boolean lecturaEscritura;
	
	@OneToOne(mappedBy = "permisos", cascade = CascadeType.ALL)
	private Rol rol;
	
}
