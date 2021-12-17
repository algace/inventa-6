package com.dbcom.app.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidad para sistemas
 * 
 * @author jgm
 */
@Entity
@Data
// Evitamos referencias circulares
@EqualsAndHashCode(exclude = "tiposSubsistemas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoSistema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8188620105054565531L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SISTEMAS_SEQ")
	@SequenceGenerator(sequenceName = "sistemas_seq", initialValue = 1, allocationSize = 1 , name = "SISTEMAS_SEQ")
	private Long id;
	
	@NotNull(message = "{validation.notNull}")
	private String nombre;
	
	private String descripcion;
	
	@NotNull(message = "{validation.notNull}")
	private String color;
	
	@NotNull(message = "{validation.notNull}")
	private String colorTexto;
	
	private String ejemplo;
	
	@NotNull(message = "{validation.notNull}")
	private String codigoFuncionRed;
	
	@OneToMany(mappedBy="tipoSistema", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<TipoSubsistema> tiposSubsistemas;
	
}
