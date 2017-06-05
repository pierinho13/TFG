package com.tfg.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class RoleUsuario implements Serializable{
	
	private Long id;
	private String nombre;
	private Boolean isSuperAdmin=false;
	
	@Id
	@SequenceGenerator(name="RoleUsuario_pk_sequence",sequenceName="RoleUsuario_sequence",allocationSize=1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="RoleUsuario_pk_sequence")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	@Transient
	public String getAuthority() {
		return nombre;
	}
	@Override
	public String toString() {
		return nombre;
	}
	private static final long serialVersionUID = -5293631287251979879L;
}
