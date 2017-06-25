package com.tfg.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaManager {
	
//	@Autowired private ClasificacionClienteRepository clasificacionClienteRepository ;
//	@Autowired private EmpresaRepository empresaRepository;

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
//	public List<ClasificacionCliente> obtieneTodasClasificacionesDeUnaEmpresa(Long empresaId){
//
//		List<ClasificacionCliente> clasificaciones = clasificacionClienteRepository.obtieneTodasLasClasificacionesDeUnaEmpresa(empresaId);
//		
//		return clasificaciones;
//	}
//	public ClasificacionClienteCommand obtieneCommandDesdeClasificacionCliente(ClasificacionCliente clasificacionCliente, AppUser user) {
//		ClasificacionClienteCommand command = new ClasificacionClienteCommand();
//		command.setId(clasificacionCliente.getId());
//		command.setNombre(clasificacionCliente.getNombre());
//		command.setCodigo(clasificacionCliente.getCodigo());
//		return command;
//	}
//	
//	@Transactional
//	public ClasificacionCliente persisteCommandComoClasificacionCliente(ClasificacionClienteCommand clasificacionClienteCommand,
//			DatosAuditoria audit, AppUser user) {
//		
//		logger.trace("Solicitado persistir una clasificacion con nombre {} e ID {}", clasificacionClienteCommand.getNombre(), clasificacionClienteCommand.getId());
//		ClasificacionCliente clasificacionCliente;
//		Empresa empresa = null;
//		if (clasificacionClienteCommand.isNew()) {	// si es un nueva clasificacion, crea nueva instancia y asigna la empresa
//			logger.debug("El usuario {} va a persistir una clasificacion nueva",user.getUsername());
//			clasificacionCliente = new ClasificacionCliente();
//			empresa = empresaRepository.findOne(user.getEmpresaId());
//			if (empresa == null) {		// verifica si la empresa existe y la setea
//				throw new IllegalStateException("ERROR. La Empresa asociada a la clasificacion creada no existe. ID de empresa solicitado: "
//						+ clasificacionClienteCommand.getEmpresaId());
//			}
//			clasificacionCliente.setEmpresa(empresa);
//		} else {	// si se actualiza una clasificacion, lo buscamos por su id e idEmpresa
//			clasificacionCliente = clasificacionClienteRepository.findByIdAndEmpresaId(clasificacionClienteCommand.getId(), user.getEmpresaId());
//			logger.debug("El usuario {} actualiza los datos de la clasificacion cliente {}",user.getUsername(),clasificacionClienteCommand.getNombre());
//		}
//		clasificacionCliente.setNombre(clasificacionClienteCommand.getNombre());
//		clasificacionCliente.setCodigo(clasificacionClienteCommand.getCodigo());
//		
//		ClasificacionCliente persistido = clasificacionClienteRepository.save(clasificacionCliente);	// guarda en BBDD
//		if (persistido == null) {
//			throw new IllegalStateException(
//					"ERROR. No se ha persistido correctamente la clasificacion: " + clasificacionCliente.getNombre());
//		}
//		logger.trace("Persistido la clasificacion cliente '{}' con id: {}", clasificacionCliente.getNombre(), clasificacionCliente.getId());
//		
//		return persistido;
//	}
//	
//	@Transactional
//	public void elimina(ClasificacionCliente clasificacionCliente, AppUser user) {
//		if (clasificacionCliente.getEmpresa().getId().longValue() != user.getEmpresaId().longValue()) {
//			logger.error(
//					"PELIGRO:  La clasificacion a eliminar (id:{}) no pertenece a la empresa del usuario {}. Empresa de la clasificacion: {}. Empresa del usuario: {}",
//					clasificacionCliente.getId(), user.getUsername(), clasificacionCliente.getEmpresa().getId(), user.getEmpresaId());
//			throw new IllegalStateException("ERROR. La clasificación a eliminar (id: " + clasificacionCliente.getId() + ") no es de la empresa correcta.");
//		}
//		clasificacionClienteRepository.delete(clasificacionCliente);		
//		
//	}
//	public List<IdTextoData> obtieneTodosParaDesplegable(Long empresaId) {
//		IdTextoData idTextoData = new IdTextoData(0L, "Elige clasificación");
//		List<IdTextoData> clasificacionesCliente = clasificacionClienteRepository.obtieneTodosParaDesplegable(empresaId);
//		clasificacionesCliente.add(0, idTextoData);
//	    return clasificacionesCliente;
//	}
}
