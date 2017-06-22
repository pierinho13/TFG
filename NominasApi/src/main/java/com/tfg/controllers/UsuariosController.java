package com.tfg.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tfg.service.UsuarioManager;
import com.tfg.utils.dto.UsuarioDto;


@Controller
public class UsuariosController {
	
	@Autowired
	private UsuarioManager usuarioManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method = RequestMethod.GET, value="/users/byUsername/{username}")
	@ResponseBody UsuarioDto getUserProviderByUsername(@PathVariable String username) {
		
		logger.debug("Estoy en método getUserProviderByUsername()");
		
		UsuarioDto usuarioDto = usuarioManager.getUserForLogin(username);
		
		return usuarioDto;
	}
	

}
