package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entidad para documentos
 * 
 * @author jose.vallve
 */
@Entity
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = "equipamiento")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Documento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8696774879484214225L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENTO_SEQ")
	@SequenceGenerator(sequenceName = "documento_seq", initialValue = 1, allocationSize = 1 , name = "DOCUMENTO_SEQ")
	private Long id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	 
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;
	
	@Lob
	private byte[] contenido;
	
	@NotEmpty
	private String tipo;
	
	@ManyToOne(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@Builder.Default
	private TipoDocumento tipoDocumento = new TipoDocumento();
	
	@ManyToOne(fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Equipamiento equipamiento;
	
}
