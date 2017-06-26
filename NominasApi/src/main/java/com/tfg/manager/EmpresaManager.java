package com.tfg.manager;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.model.Empleado;
import com.tfg.model.Empresa;
import com.tfg.repository.EmpresaRepository;
import com.tfg.utils.commands.EmpleadoCommand;
import com.tfg.utils.commands.EmpresaCommand;
import com.tfg.utils.response.MensajeRespuesta;

@Service
public class EmpresaManager {
	
	@Autowired private EmpresaRepository empresaRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
public MensajeRespuesta persistCommandComoEmpresa(EmpresaCommand command) {
		
		logger.debug("Estoy en método persistCommandComoEmpresa ()");
		Empresa empresa = null;
		
		if(command.isNew()) {
			empresa = new Empresa();
			empresa.setFechaAlta(new Date());
		}else {
			empresa = empresaRepository.findById(command.getId());
		}
		
		empresa.setNombre(command.getNombre());
		empresa.setNombreComercial(command.getNombreComercial());
		empresa.setDescripcion(command.getDescripcion());
		empresa.setActividad(command.getActividad());
		empresa.setFicticia(command.getFicticia());
		empresa.setNumeroEmpleados(command.getNumeroEmpleados());
		empresa.setSector(command.getSector());
		
		
		
		Empresa persisted = empresaRepository.save(empresa);
		
		MensajeRespuesta mensajeRespuesta =  null;
		
		if(persisted!=null){
			mensajeRespuesta = new MensajeRespuesta("1", "Empresa guardada correctamente", persisted);
		}else{
			mensajeRespuesta = new MensajeRespuesta("-1", "Error al guardar empresa");
		}
		
		return mensajeRespuesta;
	}

}
