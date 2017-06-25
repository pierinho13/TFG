package com.tfg.utils.response;

public class MensajeRespuesta {
	
	public static final String CODE_OK = "1";
	private String codigo;
	private String mensaje;
	private Object data;
	private ResponseError responseError;

	public MensajeRespuesta() {
		super();
	}
	
	public MensajeRespuesta(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public MensajeRespuesta(String codigo, String mensaje, Object data) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.data = data;
	}

	public MensajeRespuesta(String codigo, String mensaje, Object data, ResponseError responseError) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.data = data;
		this.responseError = responseError;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Object getData() {
		return data;
	}

	public ResponseError getResponseError() {
		return responseError;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setResponseError(ResponseError responseError) {
		this.responseError = responseError;
	}

	public boolean isOK() {
		return this.codigo.equals(CODE_OK);
	}
}
