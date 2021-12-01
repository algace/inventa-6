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
public class Airblock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3923276745949121957L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AIRBLOCKS_SEQ")
	@SequenceGenerator(sequenceName = "airblocks_seq", initialValue = 1, allocationSize = 1 , name = "AIRBLOCKS_SEQ")
	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String flMin;

	@Size(min = 1, max = 50, message = "{validation.min1max70}")
	private String flMax;

	@Size(max = 250, message = "{validation.max250}")
	private String coordenadas;
	
	@Size(max = 250, message = "{validation.max250}")
	private String sectoresATC;
	
}
