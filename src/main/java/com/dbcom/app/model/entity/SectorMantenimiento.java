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
@EqualsAndHashCode(exclude = "regionMantenimiento")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectorMantenimiento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -516364323277822012L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECTOR_MANTENIMIENTO_SEQ")
	@SequenceGenerator(sequenceName = "sector_mantenimiento_seq", initialValue = 1, allocationSize = 1 , name = "SECTOR_MANTENIMIENTO_SEQ")
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	private String airBlocks;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id")
	@NotNull(message = "{validation.notNull}")
	private RegionMantenimiento regionMantenimiento;
}
