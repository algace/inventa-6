package com.dbcom.app.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

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
public class Rol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3749444374633256623L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_SEQ")
	@SequenceGenerator(sequenceName = "rol_seq", initialValue = 1, allocationSize = 1 , name = "ROL_SEQ")
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "FK_NIVEL", nullable = false)
	private Nivel nivel;
	
	@OneToOne
    @JoinColumn(name = "FK_AMBITO", nullable = false)
	private Ambito ambito;
	
	@OneToOne
    @JoinColumn(name = "FK_PERMISOS", nullable = false)
	private Permisos permisos;
	 
	@Size(max = 50, message = "{validation.max50}")
	private Integer idLocalizacion;

	private Boolean inventario;
	
	private Boolean planVoIP;
	
	@OneToOne(mappedBy = "rol", cascade = CascadeType.ALL)
    private Usuario usuario;
}
