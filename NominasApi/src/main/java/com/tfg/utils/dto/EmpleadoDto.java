package com.tfg.utils.dto;

import java.util.Date;

import com.tfg.model.TipoEmpleado;

public class EmpleadoDto {

	private Long id;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private Date fechaAlta;
	private TipoEmpleado tipoEmpleado;
	
	
	public EmpleadoDto(Long id, String nombre, String apellidos, Date fechaNacimiento, Date fechaAlta,
			com.tfg.model.TipoEmpleado tipoEmpleado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaAlta = fechaAlta;
		this.tipoEmpleado = tipoEmpleado;
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
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	
}
