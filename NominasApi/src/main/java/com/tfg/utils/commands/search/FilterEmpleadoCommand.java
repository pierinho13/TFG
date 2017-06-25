package com.tfg.utils.commands.search;

import java.io.Serializable;
import java.util.Date;


import com.tfg.model.TipoEmpleado;

public class FilterEmpleadoCommand implements Serializable {

	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private Date fechaAlta;
	private TipoEmpleado TipoEmpleado;
	
	
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
		return TipoEmpleado;
	}


	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		TipoEmpleado = tipoEmpleado;
	}


	private static final long serialVersionUID = 1031985844908060840L;

}
