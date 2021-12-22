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
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidad para Sector de mantenimiento
 * 
 * @author jgm
 */
@Entity
@Data
// Evitamos referencias circulares
@EqualsAndHashCode(exclude = "regionOperativa")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorOACI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1213438075251157447L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECTOR_OACI_SEQ")
	@SequenceGenerator(sequenceName = "sector_oaci_seq", initialValue = 1, allocationSize = 1 , name = "SECTOR_OACI_SEQ")
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id")
	@NotNull(message = "{validation.notNull}")
	private RegionOperativa regionOperativa;
	
	private Integer flMin;

	private Integer flMax;

	private String descripcion;
	
	private String coordenadas;
}
