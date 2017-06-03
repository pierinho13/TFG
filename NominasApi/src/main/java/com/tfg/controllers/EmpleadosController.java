package com.tfg.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tfg.model.Empleado;
import com.tfg.model.Empresa;
import com.tfg.repository.EmpleadoRepository;
import com.tfg.repository.EmpresaRepository;
@Controller
public class EmpleadosController {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/nominasApi/{empresaId}/empleados/{listado}.json", method = RequestMethod.GET)
	public @ResponseBody List<Empleado> getListadoEmpleados(@PathVariable Long empresaId,@PathVariable String listado) {
		logger.debug("metodo getListadoEmpleados solicita todas los empleados ");
		List<Empleado> empleados= empleadoRepository.findByEmpresaId(empresaId);
		return empleados;
	}
	

}
