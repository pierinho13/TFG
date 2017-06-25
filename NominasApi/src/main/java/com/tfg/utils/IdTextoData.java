package com.tfg.utils;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class IdTextoData implements Serializable,Comparable<IdTextoData> {

	
	/**
	 * 
	 */
	private Long id;
	private String texto;
	private Object data;

	public IdTextoData() {
		super();
	}

	public IdTextoData(Long id, String texto, Object data) {
		super();
		this.id = id;
		this.data = data;
		this.texto = texto;
	}

	public IdTextoData(Long id, String texto) {
		super();
		this.id = id;
		this.texto = texto;
	}
	
	public Long getId() {
		return id;
	}
	
	public Object getData() {
		return data;
	}

	public String getTexto() {
		return texto;
	}

	public String getNombre() { // es un alias de getTexto
		return texto;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "(" + this.id + ") " + this.texto + ". ";
	}
	
	@Override
	public int compareTo(IdTextoData idTD) {
		CompareToBuilder cb = new CompareToBuilder();
		if (this.getId() == null || idTD.getId() == null) {
			return 0;
		} else {
			cb.append(this.getTexto(), idTD.getTexto());
			cb.append(this.getId(), idTD.getId());
			return cb.toComparison();
		}
	}

	private static final long serialVersionUID = 1543776174563444297L;
}

