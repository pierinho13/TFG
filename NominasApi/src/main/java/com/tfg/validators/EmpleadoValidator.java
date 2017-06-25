package com.tfg.validators;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tfg.model.Empleado;
import com.tfg.model.Empresa;
import com.tfg.repository.EmpleadoRepository;
import com.tfg.repository.EmpresaRepository;
import com.tfg.utils.commands.EmpleadoCommand;

@Component
public class EmpleadoValidator implements Validator {
	@Autowired 
	private EmpresaRepository empresaRepository;
	@Autowired 
	private EmpleadoRepository empleadoRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EmpleadoCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validate(target, errors, "");
	}
	public void validate(Object target, Errors errors, String prefijo) {
		
		EmpleadoCommand command = (EmpleadoCommand) target;
		Empresa empresa = empresaRepository.findById(command.getEmpresaId());
		
		if(empresa!=null){
			command.setEmpresa(empresa);
		}else{
			errors.rejectValue("empresaId","No se encuentra ninguna empresa con id: " +command.getEmpresaId()+" para este empleado");
		}
		
		if(StringUtils.isBlank(command.getNombre())){
			errors.rejectValue("name","El nombre es obligatorio");
		}
		
		if(StringUtils.isBlank(command.getCargo())){
			errors.rejectValue("name","El cargo es obligatorio");
		}
		
		if(!BooleanUtils.isTrue(command.isNew())){
			
			Empleado empleado = empleadoRepository.findByIdAndEmpresaId(command.getId(), command.getEmpresaId());
			
			if(empleado == null){
				errors.rejectValue("","No se encuentra el empleado a editar con id: "+ command.getId()+ " y de la empresa id: "+ command.getEmpresaId());
			}
		}
	}

}

