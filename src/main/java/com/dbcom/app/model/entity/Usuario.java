package com.dbcom.app.model.entity;

import java.io.Serializable;
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
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4508351627575423727L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	@SequenceGenerator(sequenceName = "usuario_seq", initialValue = 1, allocationSize = 1 , name = "USUARIO_SEQ")
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "FK_USUARIO_PERFIL", nullable = false)
    private UsuarioPerfil usuarioPerfil;
	
	@OneToOne
    @JoinColumn(name = "FK_ROL", nullable = false)
	private Rol rol;
	 
	@Size(min = 1, max = 120, message = "{validation.min1max120}")
	private String nombreApellidos;
	
}
