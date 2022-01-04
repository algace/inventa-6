package com.dbcom.app.model.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaPasarela implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TARJETAPASARELA_SEQ")
	@SequenceGenerator(sequenceName = "tarjetapasarela_seq", initialValue = 1, allocationSize = 1 , name = "TARJETAPASARELA_SEQ")
	private Short id;
	
	@Size(min = 1, max = 50, message = "{validation.min1max50}")
	private String nombre;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name = "tarjetaPasarela_chasisPasarela",
			  joinColumns = @JoinColumn(name = "id_tarjetaPasarela", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "id_chasisPasarela", referencedColumnName = "id", foreignKey = @ForeignKey(name="none")))
	@Builder.Default
	private Set<ChasisPasarela> chasisPasarelas = new HashSet<>();
}
