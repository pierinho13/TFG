package com.tfg.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tfg.model.AppUser;

@Controller
public class UtilController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = { "/initial-data" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getInitData( AppUser user )
	{
		
		logger.trace("Estoy en metodo getInitData");
		
		Map<String, Object> data = new HashMap<>();
		
		data.put( "date", new Date() );
		
		data.put( "nombreEmpleado", user.getEmpleado() );
		
		data.put( "username", user.getUsername() );
		
		data.put( "empresaNombre", user.getEmpresa() );
		
		Map<String, Object> ret = new HashMap<>();
		
		ret.put( "codigo", "1" );
		
		ret.put( "data", data );
		
		return ret;
	}
}
