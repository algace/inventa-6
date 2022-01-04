package com.dbcom.app.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidad para tipos de documentos
 * 
 * @author jose.vallve
 */
@Entity
@Data
// Evitamos referencias circulares
@EqualsAndHashCode(exclude = "documentos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7512655134269785386L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPODOCUMENTO_SEQ")
	@SequenceGenerator(sequenceName = "tipodocumento_seq", initialValue = 1, allocationSize = 1 , name = "TIPODOCUMENTO_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;
	
}
