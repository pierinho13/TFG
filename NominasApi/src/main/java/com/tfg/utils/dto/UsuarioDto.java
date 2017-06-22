package com.tfg.utils.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDto {
	private Long id;
	private String username;
	private String password;
	private Long empresaId;
	private String empresa;
	private Date fechaAlta;
	private Boolean esAdmin;
	private Boolean esComercial;
	private Set<RoleUsuarioDto> roles = new HashSet<RoleUsuarioDto>();
	
	
	public UsuarioDto(Long id, String username, String password, Long empresaId, Date fechaAlta, Boolean esAdmin,
			Boolean esComercial, String empresa) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.empresaId = empresaId;
		this.fechaAlta = fechaAlta;
		this.esAdmin = esAdmin;
		this.esComercial = esComercial;
		this.empresa  = empresa;
	}
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
	public Long getEmpresaId() {
		return empresaId;
	}
	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
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
	public Set<RoleUsuarioDto> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleUsuarioDto> roles) {
		this.roles = roles;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	

}
