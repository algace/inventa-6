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
 * Entidad para veriones
 * 
 * @author jose.vallve
 */
@Entity
@Data
// Evitamos referencias circulares
//@EqualsAndHashCode(exclude = "aplicacionesSW")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VersionSW implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1570312222962728609L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERSIONSW_SEQ")
	@SequenceGenerator(sequenceName = "versionsw_seq", initialValue = 1, allocationSize = 1 , name = "VERSIONSW_SEQ")
	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	 
	@Size(min = 1, max = 250, message = "{validation.min1max250}")
	private String descripcion;
	
/*	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aplicacionSW_versionSW",
	  joinColumns = @JoinColumn(name = "id_versionSW", referencedColumnName = "id"), 
	  inverseJoinColumns = @JoinColumn(name = "id_aplicacionSW", referencedColumnName = "id"))
	@Builder.Default
	private Set<AplicacionSW> aplicacionesSW = new HashSet<>(); */
	
}
