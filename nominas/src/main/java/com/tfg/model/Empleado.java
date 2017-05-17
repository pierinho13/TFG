package com.tfg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Empleado implements Serializable{

	private Long id;
	private String nombre;
	private String apellidos;
	private Empresa empresa;
	private Date fechaNacimiento;
	private Date fechaAlta;
	private TipoEmpleado TipoEmpleado;
	private String cargo;
	private Boolean puedeVerOtrosEmpleados;
	private Boolean puedeVerOtrasEmpresas; 


	@Id
	@SequenceGenerator(name = "Empleado_pk_sequence", sequenceName = "Empleado_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Empleado_pk_sequence")
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
	@ManyToOne(fetch = FetchType.LAZY)
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
		return TipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		TipoEmpleado = tipoEmpleado;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Boolean getPuedeVerOtrosEmpleados() {
		return puedeVerOtrosEmpleados;
	}

	public void setPuedeVerOtrosEmpleados(Boolean puedeVerOtrosEmpleados) {
		this.puedeVerOtrosEmpleados = puedeVerOtrosEmpleados;
	}

	public Boolean getPuedeVerOtrasEmpresas() {
		return puedeVerOtrasEmpresas;
	}

	public void setPuedeVerOtrasEmpresas(Boolean puedeVerOtrasEmpresas) {
		this.puedeVerOtrasEmpresas = puedeVerOtrasEmpresas;
	}
	
	@Transient
	public String getNombreCompleto() {
		return nombre + " " + apellidos;
	}
	private static final long serialVersionUID = -5000835371908661289L;
}
