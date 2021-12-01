package com.dbcom.app.model.dto;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import com.dbcom.app.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO con el que evitamos exponer la entidad en la vista
 * 
 * @author eduardo.tubilleja
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class UsuarioPerfilDto implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8738663004541773402L;

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
	
    private Usuario usuario;
	
}
