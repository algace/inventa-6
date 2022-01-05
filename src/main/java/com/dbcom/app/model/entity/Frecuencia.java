package com.dbcom.app.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eduardo.tubilleja
 *
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Frecuencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1534422614709935764L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRECUENCIA_SEQ")
	@SequenceGenerator(sequenceName = "frecuencia_seq", initialValue = 1, allocationSize = 1 , name = "FRECUENCIA_SEQ")
	private Long id;
	
	@Size(min = 1, max = 70, message = "{validation.min1max70}")
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipoUnidadFrecuencia_id")
	@NotNull
	private TipoUnidadFrecuencia tipoUnidadFrecuencia;
	
	@NotNull(message = "{validation.notNull}")
	@NumberFormat(pattern = "###,###.##") 
	private Double valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipoBandaFrecuencia_id")
//	@NotNull
	private TipoBandaFrecuencia tipoBandaFrecuencia;
	
	@NotNull(message = "{validation.notNull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPublicacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipoFuenteInformacion_id")
	@NotNull
	private TipoFuenteInformacion tipoFuenteInformacion;
	
	private String observaciones;
	
}
