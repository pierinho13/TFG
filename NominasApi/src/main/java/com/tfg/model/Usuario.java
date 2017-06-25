package com.tfg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable{

	private Long id;
	private String username;
	private String password;
//	private Empleado empleado;
	@JsonIgnore
	private Empresa empresa;
	private Date fechaAlta;
	private Boolean esAdmin;
	private Boolean esComercial;
	private Set<RoleUsuario> roles = new HashSet<RoleUsuario>();


	@Id
	@SequenceGenerator(name = "Usuario_pk_sequence", sequenceName = "Usuario_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario_pk_sequence")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
//	@ManyToOne(fetch=FetchType.LAZY,optional=false)
//	public Empleado getEmpleado() {
//		return empleado;
//	}
//
//	public void setEmpleado(Empleado empleado) {
//		this.empleado = empleado;
//	}
	
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
	public Boolean getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(Boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public Boolean getEsComercial() {
		return esComercial;
	}

	public void setEsComercial(Boolean esComercial) {
		this.esComercial = esComercial;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<RoleUsuario> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleUsuario> roles) {
		this.roles = roles;
	}
	
	@Transient
	public Long getMiEmpresaId() {
		return this.getEmpresa().getId();
	}
	private static final long serialVersionUID = -8480201021225643322L;
}
