package com.geoplagas.providers.domain.service.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService
{
	private final static String API_PREFIX = "providers";
	
	private String apiHost = "http://localhost:8020";
	
	private final RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public RestService()
	{
		this.restTemplate = new RestTemplate();
	}
	
	public Object get( String _url )
	{
		return this.get( _url, null );
	}
	
	public Object get( String _url, Map<String, ?> _params )
	{
		return this.get( _url, _params, Object.class );
	}
	
	public Object get( String _url, Map<String, ?> _params, Class<?> _clazz )
	{
		if( _params == null )
			
			_params = new HashMap<>();
		
		logger.info( "GET: Se va a llamar a la url: " + _url );
		
		return this.restTemplate.getForObject( _url, _clazz, _params );
	}
	
	public Object post( String _url )
	{
		return this.post( _url, null );
	}
	
	public Object post( String _url, Map<String, ?> _params )
	{
		return this.post( _url, _params, Object.class );
	}
	
	public Object post( String _url, Map<String, ?> _params, Class<?> _clazz )
	{
		if( _params == null )
			
			_params = new HashMap<>();

		logger.info( "POST: Se va a llamar a la url: " + _url );
		
		return this.restTemplate.postForObject( _url, _params, Object.class );
	}
	
	public String obtieneUrlApi( String _url )
	{
		return this.obtieneUrlApi( _url, null );
	}
	
	public String obtieneUrlApi( String _url, Long _providerId )
	{
		String _ret = this.apiHost.trim().replaceAll( "\\/$+", "" ) + 
					  "/" + RestService.API_PREFIX + "/" + 
					  ( ( _providerId != null ) ? ( _providerId + "/" ) : "" ) + 
					  ( ( _url != null ) ? _url.trim().replaceAll( "^\\/+", "" ) : "" );
		
		return _ret;
	}
}
