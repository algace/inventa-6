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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author jose.vallve
 *
 */
@Entity
@Data
//Evitamos referencias circulares
@EqualsAndHashCode(exclude = "documentos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Equipamiento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4508351627575423727L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EQUIPAMIENTO_SEQ")
	@SequenceGenerator(sequenceName = "equipamiento_seq", initialValue = 1, allocationSize = 1 , name = "EQUIPAMIENTO_SEQ")
	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;
	 
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String marca;

	@Size(max = 50, message = "{validation.max50}")
	private String modelo;

	@Size(max = 250, message = "{validation.max250}")
	private String caracteristicas;
	
	@Size(max = 250, message = "{validation.max250}")
	private String descripcion;

	@Builder.Default
	@Max(999)
	private Short entradas = 0;
	
	@Builder.Default
	@Max(999)
	private Short salidas = 0;

	@Builder.Default
	@Max(999)
	private Short numeroPuertos = 0;
	
	@Builder.Default
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double ganancia = 0.0;

	@Builder.Default
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double perdida = 0.0;

	@Builder.Default
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double apertura = 0.0;

	@Builder.Default
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double diametro = 0.0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sistema_id")
	@NotNull(message = "{validation.notNull}")
	private TipoSistema tipoSistema;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="subsistema_id")
	@NotNull(message = "{validation.notNull}")
	private TipoSubsistema tipoSubsistema;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name = "equipamiento_id", referencedColumnName = "id")
	@Builder.Default
	private Set<Documento> documentos = new HashSet<>();
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name = "equipamiento_id", referencedColumnName = "id")
	@Builder.Default
	private Set<Fotografia> fotografias = new HashSet<>();
	
}
