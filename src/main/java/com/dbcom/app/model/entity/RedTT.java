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
 * Entidad para Redes T/T
 * 
 * @author neoris
 */
@Entity
@Data
// Evitamos referencias circulares
@EqualsAndHashCode(exclude = "tipoTopologia")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedTT implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6833809134133827062L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REDES_TT_SEQ")
	@SequenceGenerator(sequenceName = "redes_tt_seq", initialValue = 1, allocationSize = 1 , name = "REDES_TT_SEQ")
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String observaciones;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipoToplogia_id")
	@NotNull(message = "{validation.notNull}")
	private TipoTopologia tipoTopologia;
	
	/*
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name = "redTT_enlaceTT",
			  joinColumns = @JoinColumn(name = "id_redTT", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "id_enlaceTT", referencedColumnName = "id", foreignKey = @ForeignKey(name="none")))
	@Builder.Default
	private Set<EnlaceTT> enlacesTT = new HashSet<>();
	 */
}
