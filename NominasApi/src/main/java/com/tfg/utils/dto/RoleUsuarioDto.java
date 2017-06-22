package com.tfg.utils.dto;

public class RoleUsuarioDto {
	private Long id;
	private String nombre;
	private Boolean isSuperAdmin=false;
	
	
	
	public RoleUsuarioDto(Long id, String nombre, Boolean isSuperAdmin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.isSuperAdmin = isSuperAdmin;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}
	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	
	
}
