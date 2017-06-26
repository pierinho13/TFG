package com.tfg.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tfg.manager.EmpleadoManager;
import com.tfg.model.Empleado;
import com.tfg.repository.EmpleadoRepository;
import com.tfg.utils.commands.EmpleadoCommand;
import com.tfg.utils.commands.search.FilterEmpleadoCommand;
import com.tfg.utils.dto.EmpleadoDto;
import com.tfg.utils.response.ApiError;
import com.tfg.utils.response.JpqlPageable;
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
	
	@RequestMapping(value = "/nominasApi/{empresaId}/empleados/todos", method = RequestMethod.GET)
	public @ResponseBody MensajeRespuesta getListadoEmpleados(@PathVariable Long empresaId,
			@ModelAttribute FilterEmpleadoCommand filterEmpleadoCommand,@ModelAttribute JpqlPageable jpqlPageable) {
		
		logger.debug("metodo getListadoEmpleados solicita todos los empleados ");
		
		MensajeRespuesta mensajeRespuesta = null;
		
		try {
			Pageable pageable = jpqlPageable.getPageable();
			if(pageable == null){
				
				throw new Exception("Pageable no puede ser null");
			}
			
			Page<EmpleadoDto>  personsDto= empleadoRepository.listadoEmpleados(empresaId, filterEmpleadoCommand, pageable);
			
			mensajeRespuesta = new MensajeRespuesta("1", "Listado de empleados paginado de la empresa con id: "+empresaId, personsDto);
			
		} catch (Exception e) {
			
			 ResponseError responseError = new ResponseError();
			 ApiError apiError = new ApiError("", ""+ e.getMessage());
			 List<ObjectError> errors = new ArrayList<>();
			 errors.add(apiError);
			 responseError.setErrors(errors);
			 mensajeRespuesta = new MensajeRespuesta("-2", "Se ha producido un error al traer los empleados de la empresa"
			 		+ " con id: "+empresaId, null,responseError);
		}
		
		return mensajeRespuesta;
	}
	
	@RequestMapping(value = "/nominasApi/{empresaId}/empleados/{empleadoId}", method = RequestMethod.GET)
	public @ResponseBody MensajeRespuesta getEmpleado(@PathVariable Long empresaId,
			@PathVariable Long empleadoId) {
		
		logger.debug("metodo getEmpleado ");
		
		MensajeRespuesta mensajeRespuesta = null;
		
		try {
			
			Empleado empleado = empleadoRepository.findByIdAndEmpresaId(empleadoId,empresaId);
			
			if(empleado==null){
				
				throw new Exception("No se encuentra el empleado con id especificado: " + empleadoId);
			}
			
			mensajeRespuesta = new MensajeRespuesta("1", "Empleado especifico", empleado);
			
		} catch (Exception e) {
			
			ResponseError responseError = new ResponseError();
			ApiError apiError = new ApiError("", ""+ e.getMessage());
			List<ObjectError> errors = new ArrayList<>();
			errors.add(apiError);
			responseError.setErrors(errors);
			mensajeRespuesta = new MensajeRespuesta("-1", "Se ha producido un error al traer al empleado con id: "+empleadoId, null,responseError);
		}
		
		return mensajeRespuesta;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/nominasApi/{empresaId}/empleados/crearEmpleado")
	@ResponseBody MensajeRespuesta postCrearEmpleado(@PathVariable Long empresaId,
			@RequestBody EmpleadoCommand empleadoCommand,BindingResult result) {
		
		logger.debug("metodo postCrearEmpleado ");
		
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
	
	@RequestMapping(method = RequestMethod.PUT, value="/nominasApi/{empresaId}/empleados/update/{empleadoId}")
	@ResponseBody MensajeRespuesta actualizaEmpleado(@PathVariable Long empresaId, @PathVariable Long empleadoId,
			@RequestBody EmpleadoCommand empleadoCommand,BindingResult result) {
		
		logger.debug("metodo actualizaEmpleado ");
		
		empleadoCommand.setId(empleadoId);
		empleadoCommand.setEmpresaId(empresaId);
		
		empleadoValidator.validate(empleadoCommand, result);
		if(result.hasErrors()){
			
			ResponseError responseError = new ResponseError();
			responseError.setErrors(result.getAllErrors());
			return new MensajeRespuesta("-2", "Error de validacion", null, responseError);
		}
		MensajeRespuesta mensajeRespuesta = empleadoManager.persistCommandComoEmpleado(empleadoCommand, empresaId);
		
		return mensajeRespuesta;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/nominasApi/{empresaId}/empleados/elimina/{empleadoId}")
	@ResponseBody MensajeRespuesta deleteEmpleado(@PathVariable Long empleadoId,@PathVariable Long empresaId) {
		
		logger.debug("metodo deleteEmpleado ");
		
		MensajeRespuesta mensajeRespuesta = empleadoManager.eliminaEmpleado(empleadoId, empresaId);
		
		return mensajeRespuesta;
	}
}
