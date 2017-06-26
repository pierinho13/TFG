package com.tfg.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.tfg.model.Empleado;
import com.tfg.model.TipoEmpleado;
import com.tfg.repository.EmpleadoRepository;
import com.tfg.utils.commands.EmpleadoCommand;
import com.tfg.utils.response.ApiError;
import com.tfg.utils.response.MensajeRespuesta;
import com.tfg.utils.response.ResponseError;

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
		empleado.setFechaNacimiento(command.getFechaNacimiento());
		empleado.setTipoEmpleado(command.getTipoEmpleado());
		
		
		Empleado persisted = empleadoRepository.save(empleado);
		
		MensajeRespuesta mensajeRespuesta =  null;
		
		if(persisted!=null){
			mensajeRespuesta = new MensajeRespuesta("1", "Empleado guardado correctamente", persisted);
		}else{
			mensajeRespuesta = new MensajeRespuesta("-1", "Error al guardar empleado");
		}
		
		return mensajeRespuesta;
	}
	
	public MensajeRespuesta eliminaEmpleado(Long empleadoId,Long empresaId) {
		
		MensajeRespuesta respuesta= null;
		
		try {
			Empleado empleado = empleadoRepository.findByIdAndEmpresaId(empleadoId, empresaId);
			
			if(empleado==null){
				
				throw new EmptyResultDataAccessException("No eres dueño de la entidad solicitada", 0);
			}
			
			if(!empleado.getEmpresa().getId().equals(empresaId)){
				
				throw new IllegalStateException("No eres dueño de la entidad solicitada");
			}
			
			empleadoRepository.delete(empleadoId);
			
			respuesta = new MensajeRespuesta("1", "Se ha eliminado correctamente el empleado");
			
		} catch (IllegalStateException e){
			
			ResponseError responseError = new ResponseError();
			ApiError apiError = new ApiError("", ""+ e.getMessage());
			List<ObjectError> errors = new ArrayList<ObjectError>();
			errors.add(apiError);
			responseError.setErrors(errors);
			respuesta = new MensajeRespuesta("-1", "No eres dueño de la entidad con id: " + empleadoId,null,responseError);
		} catch(DataIntegrityViolationException e ){
			
			respuesta = new MensajeRespuesta("-1", "El empleado no se puede eliminar porque es utilizado en otros sitios");
		} catch (EmptyResultDataAccessException e){
			
			ResponseError responseError = new ResponseError();
			ApiError apiError = new ApiError("", ""+ e.getMessage());
			List<ObjectError> errors = new ArrayList<>();
			errors.add(apiError);
			responseError.setErrors(errors);
			respuesta = new MensajeRespuesta("-1", "No se encuentra la entidad con id: " + empleadoId,null,responseError);
		} catch (Exception e) {
			
			respuesta = new MensajeRespuesta("-1", "Se ha producido un error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return respuesta;
	}
}
