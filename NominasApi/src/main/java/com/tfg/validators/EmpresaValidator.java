package com.tfg.validators;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tfg.model.Empresa;
import com.tfg.repository.EmpresaRepository;
import com.tfg.utils.commands.EmpresaCommand;

@Component
public class EmpresaValidator implements Validator {
	@Autowired 
	private EmpresaRepository empresaRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EmpresaCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validate(target, errors, "");
	}
	public void validate(Object target, Errors errors, String prefijo) {
		
		EmpresaCommand command = (EmpresaCommand) target;
		
		if(StringUtils.isBlank(command.getNombre())){
			errors.rejectValue("nombre","El nombre es obligatorio");
		}
		
		if(StringUtils.isBlank(command.getNombreComercial())){
			errors.rejectValue("nombreComercial","El nombre comercial es obligatorio");
		}
		
		if(StringUtils.isBlank(command.getSector())){
			errors.rejectValue("sector","El sector es obligatorio");
		}
		
		if(StringUtils.isBlank(command.getActividad())){
			errors.rejectValue("actividad","La actividad es obligatoria");
		}
		
		if(!BooleanUtils.isTrue(command.isNew())){
			
			Empresa empresa = empresaRepository.findById(command.getId());
			
			if(empresa == null){
				errors.rejectValue("","No se encuentra la empresa a editar con id: "+ command.getId());
			}
		}
	}

}

