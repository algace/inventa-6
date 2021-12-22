package com.dbcom.app.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class SectorATC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECTORATC_SEQ")
	@SequenceGenerator(sequenceName = "sectoratc_seq", initialValue = 1, allocationSize = 1 , name = "SECTORATC_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	//FALTA REGION OPERATIVA!!!!!!!!!!

	@ManyToOne(fetch = FetchType.EAGER,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})	
	@NotNull(message = "{validation.notNull}")
	private TipoSectorATC tipoSectorATC;
	
	
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPublicacion;
	
	@ManyToOne(fetch = FetchType.EAGER,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})	
	@NotNull(message = "{validation.notNull}")
	private TipoFuenteInformacion tipoFuenteInformacion;
	
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private Integer flMin;
	
	@Max(999)
	@NotNull(message = "{validation.notNull}")
	private Integer flMax;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String descripcion;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name = "sectorATC_airblock",
			  joinColumns = @JoinColumn(name = "id_sectorATC", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "id_airblock", referencedColumnName = "id", foreignKey = @ForeignKey(name="none")))
	@Builder.Default
	private Set<Airblock> airblocks = new HashSet<>(); 
}
