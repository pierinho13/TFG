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

import com.tfg.model.Empresa;
import com.tfg.repository.EmpresaRepository;
@Controller
public class EmpresasController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/ajustes/empresas/{listado}.json", method = RequestMethod.GET)
	public @ResponseBody List<Empresa> getListadoEmpresas(@PathVariable String listado) {
		logger.debug(" getListadoEmpresas solicita todas las empresas ");
		List<Empresa> empresas= empresaRepository.obtieneTodasLasEmpresas();
		return empresas;
	}
	

}
