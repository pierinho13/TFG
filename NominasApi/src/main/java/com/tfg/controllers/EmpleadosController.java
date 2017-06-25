package com.tfg.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tfg.manager.EmpleadoManager;
import com.tfg.model.Empleado;
import com.tfg.model.Empresa;
import com.tfg.repository.EmpleadoRepository;
import com.tfg.repository.EmpresaRepository;
import com.tfg.utils.commands.EmpleadoCommand;
import com.tfg.utils.response.MensajeRespuesta;
import com.tfg.utils.response.ResponseError;
import com.tfg.validators.EmpleadoValidator;
@Controller
public class EmpleadosController {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private EmpleadoValidator empleadoValidator;
	@Autowired
	private EmpleadoManager empleadoManager;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/nominasApi/{empresaId}/empleados/{listado}.json", method = RequestMethod.GET)
	public @ResponseBody List<Empleado> getListadoEmpleados(@PathVariable Long empresaId,@PathVariable String listado) {
		
		logger.debug("metodo getListadoEmpleados solicita todas los empleados ");
		List<Empleado> empleados= empleadoRepository.findByEmpresaId(empresaId);
		return empleados;
	}
	
	@RequestMapping(value = "/nominasApi/{empresaId}/empleados/{empleadoId}", method = RequestMethod.GET)
	public @ResponseBody Empleado getEmpleado(@PathVariable Long empresaId,
			@PathVariable Long empleadoId,@PathVariable String listado) {
		
		logger.debug("metodo getEmpleado ");
		Empleado empleado= empleadoRepository.findByIdAndEmpresaId(empleadoId,empresaId);
		return empleado;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/nominasApi/{empresaId}/empleados/crearEmpleado")
	@ResponseBody MensajeRespuesta postCrearEmpleado(@PathVariable Long empresaId,
			@RequestBody EmpleadoCommand empleadoCommand,BindingResult result) {
		
		empleadoCommand.setEmpresaId(empresaId);;
		empleadoValidator.validate(empleadoCommand, result);
		
		if(result.hasErrors()){
			
			ResponseError responseError = new ResponseError();
			responseError.setErrors(result.getAllErrors());
			return new MensajeRespuesta("-2", "Error de validacion", null, responseError);
		}
		
		MensajeRespuesta mensajeRespuesta = empleadoManager.persistCommandComoEmpleado(empleadoCommand, empresaId);
		
		return mensajeRespuesta;
	}
	
}
