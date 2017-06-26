package com.tfg.utils.commands.search;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import com.tfg.model.TipoEmpleado;

public class FilterEmpleadoCommand implements Serializable {

	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private Date fechaAlta;
	private TipoEmpleado TipoEmpleado;
	private BigDecimal salario;
	private BigDecimal rentabilidad;
	
	
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

	
	public BigDecimal getSalario() {
		return salario;
	}


	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}


	public BigDecimal getRentabilidad() {
		return rentabilidad;
	}


	public void setRentabilidad(BigDecimal rentabilidad) {
		this.rentabilidad = rentabilidad;
	}


	private static final long serialVersionUID = 1031985844908060840L;

}
