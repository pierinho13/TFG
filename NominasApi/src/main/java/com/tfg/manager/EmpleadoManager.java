package com.tfg.manager;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.model.Empleado;
import com.tfg.model.TipoEmpleado;
import com.tfg.repository.EmpleadoRepository;
import com.tfg.utils.commands.EmpleadoCommand;
import com.tfg.utils.response.MensajeRespuesta;

@Service
public class EmpleadoManager {
	
	@Autowired private EmpleadoRepository empleadoRepository ;
//	@Autowired private EmpresaRepository empresaRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public MensajeRespuesta persistCommandComoEmpleado(EmpleadoCommand command, Long empresaId) {
		
		logger.debug("Estoy en método persistCommandComoEmpleado ()");
		Empleado empleado = null;
		
		if(command.isNew()) {
			empleado = new Empleado();
			empleado.setEmpresa(command.getEmpresa());
			empleado.setFechaAlta(new Date());
		}else {
			empleado = empleadoRepository.findByIdAndEmpresaId(command.getId(), empresaId);
		}
		
		empleado.setNombre(command.getNombre());
		empleado.setApellidos(command.getApellidos());
		empleado.setCargo(command.getCargo());
		empleado.setPuedeVerOtrasEmpresas(command.getPuedeVerOtrasEmpresas());
		empleado.setPuedeVerOtrosEmpleados(command.getPuedeVerOtrosEmpleados());
		empleado.setFechaNacimiento(command.getFechaNacimiento());
		
		
		Empleado persisted = empleadoRepository.save(empleado);
		
		MensajeRespuesta mensajeRespuesta =  null;
		
		if(persisted!=null){
			mensajeRespuesta = new MensajeRespuesta("1", "Empleado guardado correctamente", persisted);
		}else{
			mensajeRespuesta = new MensajeRespuesta("-1", "Error al guardar empleado");
		}
		
		return mensajeRespuesta;
	}
	
}
