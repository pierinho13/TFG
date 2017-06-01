package com.geoplagas.providers.web.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.geoplagas.providers.domain.model.AppUser;
import com.geoplagas.providers.domain.service.rest.RestService;


@Controller
public class GenericController
{
	@Autowired
	private RestService restService;
	
	@RequestMapping(value = { "/app", "/app/**" }, method = RequestMethod.GET)
	public @ResponseBody Object get( HttpServletRequest _request, AppUser _user )
	{
	    String _url = ( String )_request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE );
		
	    _url = this.obtieneUrl( _url, _user );
	    
	    return restService.get( _url, this.obtieneParametrosRequest( _request ) );
	}
	
	@RequestMapping(value = { "/app", "/app/**" }, method = RequestMethod.POST)
	public @ResponseBody Object post( HttpServletRequest _request, AppUser _user )
	{
	    String _url = ( String )_request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE );
		
	    _url = this.obtieneUrl( _url, _user );
	    
	    return restService.post( _url, this.obtieneParametrosRequest( _request ) );
	}
	
	private String obtieneUrl( String _url, AppUser _user )
	{
		return this.restService.obtieneUrlApi( _url.replaceAll( "^\\/app", "" ), _user.getProviderId() );
		//return "http://localhost:8020/providers/" + _user.getProviderId() + _url.replaceAll( "^\\/app", "" );
	}
	
	private Map<String, Object> obtieneParametrosRequest( HttpServletRequest _request )
	{
		if( _request == null || _request.getParameterNames() == null || ! _request.getParameterNames().hasMoreElements() )
			
			return null;

		Map<String, Object> _ret = new HashMap<>();

		Enumeration<String> _params = _request.getParameterNames();
		
		while( _params.hasMoreElements() )
		{
			String _param = _params.nextElement();
			
			System.out.println( "Param: " + _param + " value: " + _request.getParameter( _param ) );
			
			_ret.put( _param, _request.getParameter( _param ) );
		}
		
		return _ret;
	}
}
