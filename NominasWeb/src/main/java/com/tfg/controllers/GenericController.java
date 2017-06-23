package com.tfg.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.tfg.model.AppUser;
import com.tfg.services.rest.RestService;


@Controller
public class GenericController
{
	
	@Autowired
	private RestService restService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = { "/app", "/app/**" }, method = RequestMethod.GET)
	public @ResponseBody Object get( HttpServletRequest request, AppUser user )
	{
	    String url = ( String )request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE );
		
	    url = this.obtieneUrl( url, user );
	    
	    return restService.get( url, this.obtieneParametrosRequest( request ) );
	}
	
	@RequestMapping(value = { "/app", "/app/**" }, method = RequestMethod.POST)
	public @ResponseBody Object post( HttpServletRequest request, AppUser user )
	{
	    String url = ( String )request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE );
		
	    url = this.obtieneUrl( url, user );
	    
	    return restService.post( url, this.obtieneParametrosRequest( request ) );
	}
	
	@RequestMapping(value = { "/app", "/app/**" }, method = RequestMethod.PUT)
	public @ResponseBody Object put( HttpServletRequest request, AppUser user )
	{
	    String url = ( String ) request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE );
		
	    url = this.obtieneUrl( url, user );
	    
	    return restService.put( url, this.obtieneParametrosRequest( request ) );
	}
	
	@RequestMapping(value = { "/app", "/app/**" }, method = RequestMethod.DELETE)
	public @ResponseBody Object delete( HttpServletRequest request, AppUser user )
	{
	    String url = ( String ) request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE );
		
	    url = this.obtieneUrl( url, user );
	    
	    return restService.delete( url, this.obtieneParametrosRequest( request ) );
	}
	
	private String obtieneUrl( String url, AppUser user )
	{
		return this.restService.obtieneUrlApi( url.replaceAll( "^\\/app", "" ), user.getEmpresaId() );
	}
	
	private Map<String, Object> obtieneParametrosRequest( HttpServletRequest request )
	{
		try {
			request.getParts();
		} catch( Exception _ex ) {} // no me has mandado nada 
		
		if( request == null || request.getParameterNames() == null || ! request.getParameterNames().hasMoreElements() ){
			return null;
		}
			

		Map<String, Object> ret = new HashMap<>();

		Enumeration<String> params = request.getParameterNames();
		
		while( params.hasMoreElements() )  {
			String param = params.nextElement();
			
			logger.trace( "Param: " + param + " value: " + request.getParameter( param ) );
			
			ret.put( param, request.getParameter( param ) );
		}
		
		return ret;
	}
}
