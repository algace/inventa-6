package com.dbcom.app.model.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class UsuarioPerfil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4039124184265960959L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_PERFIL_SEQ")
	@SequenceGenerator(sequenceName = "usuario_perfil_seq", initialValue = 1, allocationSize = 1 , name = "USUARIO_PERFIL_SEQ")
	private Long id;
	
	@Size(max = 50, message = "{validation.max50}")
	private String nombre;
	
	@Size(max = 50, message = "{validation.max50}")
	private String local;
	
	@Size(max = 50, message = "{validation.max50}")
	private String contrasegna;
	 
	@Size(max = 100, message = "{validation.max100}")
	private String apellidos;
	
	@Size(max = 100, message = "{validation.max100}")
	private String email;
	
	@Size(max = 9, message = "{validation.max9}")
	private Integer telefono;
	
	@Size(max = 100, message = "{validation.max100}")
	private String empresa;
	
	@Size(max = 100, message = "{validation.max100}")
	private String departamento;
	
	@Size(max = 250, message = "{validation.max250}")
	private String direccion;
	
	@OneToOne(mappedBy = "usuarioPerfil", cascade = CascadeType.ALL)
    private Usuario usuario;
}
