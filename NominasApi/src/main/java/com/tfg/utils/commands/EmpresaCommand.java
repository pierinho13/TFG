package com.tfg.utils.commands;

import java.io.Serializable;
import java.util.Date;

public class EmpresaCommand implements Serializable {
	
	private Long id;
	private String nombre;
	private String nombreComercial;
	private String descripcion;
	private String actividad;
	private Boolean ficticia=Boolean.TRUE;
	private Integer numeroEmpleados;
	private String sector;
	private Date fechaAlta;
	
	
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


	public String getNombreComercial() {
		return nombreComercial;
	}


	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getActividad() {
		return actividad;
	}


	public void setActividad(String actividad) {
		this.actividad = actividad;
	}


	public Boolean getFicticia() {
		return ficticia;
	}


	public void setFicticia(Boolean ficticia) {
		this.ficticia = ficticia;
	}


	public Integer getNumeroEmpleados() {
		return numeroEmpleados;
	}


	public void setNumeroEmpleados(Integer numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public Date getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isNew() {
		return id==null;
	}
	private static final long serialVersionUID = 4291712464024236894L;

}
