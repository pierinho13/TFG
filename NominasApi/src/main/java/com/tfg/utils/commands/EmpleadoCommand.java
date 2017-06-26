package com.tfg.utils.commands;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tfg.model.Empresa;
import com.tfg.model.TipoEmpleado;

public class EmpleadoCommand implements Serializable {

	private Long id;
	private String nombre;
	private String apellidos;
	private Long empresaId;
	private Empresa empresa;
	private Date fechaNacimiento;
	private Date fechaAlta;
	private TipoEmpleado tipoEmpleado;
	private String cargo;
	private BigDecimal salario;
	private BigDecimal rentabilidad;
	
	
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


	public Long getEmpresaId() {
		return empresaId;
	}


	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
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


	public boolean isNew() {
		return id==null;
	}
	private static final long serialVersionUID = -3863252356558972770L;
}
