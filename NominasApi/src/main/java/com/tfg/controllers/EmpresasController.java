package com.tfg.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tfg.manager.EmpresaManager;
import com.tfg.model.Empresa;
import com.tfg.repository.EmpresaRepository;
import com.tfg.utils.commands.EmpresaCommand;
import com.tfg.utils.response.ApiError;
import com.tfg.utils.response.MensajeRespuesta;
import com.tfg.utils.response.ResponseError;
import com.tfg.validators.EmpresaValidator;
@Controller
public class EmpresasController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private EmpresaValidator empresaValidator;
	@Autowired
	private EmpresaManager empresaManager;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/nominasApi/empresas", method = RequestMethod.GET)
	public @ResponseBody List<Empresa> getListadoEmpresas() {
		
		logger.debug("metodo getListadoEmpresas solicita todas las empresas ");
		List<Empresa> empresas= empresaRepository.obtieneTodasLasEmpresas();
		return empresas;
	}
	
	@RequestMapping(value = "/nominasApi/{empresaId}/empresa", method = RequestMethod.GET)
	public @ResponseBody MensajeRespuesta getEmpresa(@PathVariable Long empresaId) {
		
		logger.debug("metodo getEmpresa ");
		
		MensajeRespuesta mensajeRespuesta = null;
		
		try {
			
			Empresa empresa= empresaRepository.findById(empresaId);
			
			if(empresa==null){
				
				throw new Exception("No se encuentra la empresa con id especificado: " + empresaId);
			}
			
			mensajeRespuesta = new MensajeRespuesta("1", "Empresa especifica", empresa);
			
		} catch (Exception e) {
			
			ResponseError responseError = new ResponseError();
			ApiError apiError = new ApiError("", ""+ e.getMessage());
			List<ObjectError> errors = new ArrayList<>();
			errors.add(apiError);
			responseError.setErrors(errors);
			mensajeRespuesta = new MensajeRespuesta("-1", "Se ha producido un error al traer a la empresa con id: "+empresaId, null,responseError);
		}
		
		return mensajeRespuesta;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/nominasApi/{empresaId}/update")
	@ResponseBody MensajeRespuesta actualizaEmpresa(@PathVariable Long empresaId,
			@RequestBody EmpresaCommand empresaCommand,BindingResult result) {
		
		logger.debug("metodo actualizaEmpresa ");
		
		empresaCommand.setId(empresaId);
		
		empresaValidator.validate(empresaCommand, result);
		
		if(result.hasErrors()){
			
			ResponseError responseError = new ResponseError();
			responseError.setErrors(result.getAllErrors());
			return new MensajeRespuesta("-2", "Error de validacion", null, responseError);
		}
		MensajeRespuesta mensajeRespuesta = empresaManager.persistCommandComoEmpresa(empresaCommand);
		
		return mensajeRespuesta;
	}

}
