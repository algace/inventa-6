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
 * Entidad para Subistemas
 * 
 * @author neoris
 */
@Entity
@Data
// Evitamos referencias circulares
@EqualsAndHashCode(exclude = "tipoSistema")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSubsistema implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7103723791170940577L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBSISTEMAS_SEQ")
	@SequenceGenerator(sequenceName = "subsistemas_seq", initialValue = 1, allocationSize = 1 , name = "SUBSISTEMAS_SEQ")
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	private TipoInterfazOperacion tipoInterfazOperacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sistema_id")
	@NotNull(message = "{validation.notNull}")
	private TipoSistema tipoSistema;
}
