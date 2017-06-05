package com.tfg.web.services.rest;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService
{
	private final static String API_PREFIX = "nominas";
	
	private String apiHost = "http://localhost:8020";
	
	private final RestTemplate restTemplate;
	

	public RestService()
	{
		this.restTemplate = new RestTemplate();
	}
	
	public Object get( String url )
	{
		return this.get( url, null );
	}
	
	public Object get( String url, Map<String, ?> params )
	{
		return this.get( url, params, Object.class );
	}
	
	public Object get( String url, Map<String, ?> params, Class<?> clazz )
	{
		if( params == null )
			
			params = new HashMap<>();
		
		return this.restTemplate.getForObject( url, clazz, params );
	}
	
	public Object post( String url )
	{
		return this.post( url, null );
	}
	
	public Object post( String url, Map<String, ?> params )
	{
		return this.post( url, params, Object.class );
	}
	
	public Object post( String url, Map<String, ?> params, Class<?> clazz )
	{
		if( params == null )
			
			params = new HashMap<>();

		return this.restTemplate.postForObject( url, params, Object.class );
	}
	
	public String obtieneUrlApi( String url )
	{
		return this.obtieneUrlApi( url, null );
	}
	
	public String obtieneUrlApi( String url, Long _providerId )
	{
		String _ret = this.apiHost.trim().replaceAll( "\\/$+", "" ) + 
					  "/" + RestService.API_PREFIX + "/" + 
					  ( ( _providerId != null ) ? ( _providerId + "/" ) : "" ) + 
					  ( ( url != null ) ? url.trim().replaceAll( "^\\/+", "" ) : "" );
		
		return _ret;
	}
}
