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
	public @ResponseBody Map<String, Object> getInitData( AppUser _user )
	{
		
		logger.trace("Estoy en metodo getInitData");
		
		Map<String, Object> _data = new HashMap<>();
		
		_data.put( "date", new Date() );
		
		_data.put( "username", _user.getUsername() );
		
		_data.put( "empresaNombre", _user.getEmpresa() );
		
		Map<String, Object> _ret = new HashMap<>();
		
		_ret.put( "codigo", "1" );
		
		_ret.put( "data", _data );
		
		return _ret;
	}
}
