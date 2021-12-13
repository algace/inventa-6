package com.dbcom.app.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidad para aplicaciones
 * 
 * @author jose.vallve
 */
@Entity
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = "equipamientos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AplicacionSW implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8707067485661228126L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APLICACIONSW_SEQ")
	@SequenceGenerator(sequenceName = "aplicacionsw_seq", initialValue = 1, allocationSize = 1 , name = "APLICACIONSW_SEQ")
	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	 
	@Size(min = 1, max = 250, message = "{validation.min1max250}")
	private String archivo;
	
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	 
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hora;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aplicacionSW_versionSW",
			  joinColumns = @JoinColumn(name = "id_aplicacionSW", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "id_versionSW", referencedColumnName = "id"))
	@Builder.Default
	private Set<VersionSW> versionesSW = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aplicacionSW_equipamiento",
			  joinColumns = @JoinColumn(name = "id_aplicacionSW", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "id_equipamiento", referencedColumnName = "id"))
	@Builder.Default
	private Set<Equipamiento> equipamientos = new HashSet<>();
	
}
