package com.tfg.services.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestService
{
	private final static String API_PREFIX = "providers";
	
	//@Value("${url.api}")// si lo tuviera metido en el propierties q no tengo
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
		
		UriComponentsBuilder _builder = UriComponentsBuilder.fromUriString( _url );
		
		for( String _key : _params.keySet() )
		
			_builder = _builder.queryParam( _key, _params.get( _key ) );
		
		URI _uri = _builder.build().encode().toUri();
		
		return this.restTemplate.getForObject( _uri, _clazz );
	}
	
	public Object post( String _url, Map<String, ?> _params )
	{
		if( _params == null )
			
			_params = new HashMap<>();

		logger.info( "POST: Se va a llamar a la url: " + _url );
		
		return this.restTemplate.postForObject( _url, _params, Object.class );
	}
	
	public Object put( String _url, Map<String, ?> _params )
	{
		return this.deletePut(_url, _params, HttpMethod.PUT );
	}
	
	public Object delete( String _url, Map<String, ?> _params )
	{
		return this.deletePut(_url, _params, HttpMethod.DELETE );
	}
	
	private Object deletePut( String _url, Map<String, ?> _params, HttpMethod _method )
	{
		if( _params == null )
			
			_params = new HashMap<>();

		logger.info( _method + ": Se va a llamar a la url: " + _url );
		
		HttpHeaders _headers = new HttpHeaders();
		
		_headers.setContentType( MediaType.APPLICATION_JSON );
		
		HttpEntity<Map<String, ?>> _requestUpdate = new HttpEntity<>( _params, _headers );
		
		HttpEntity<Object> _response = this.restTemplate.exchange( _url, _method, _requestUpdate, Object.class, _params );
				
		return _response.getBody();
	}
	
	public String obtieneUrlApi( String _url )
	{
		return this.obtieneUrlApi( _url, null );
	}
	
	public String obtieneUrlApi( String _url, Long _providerId )
	{
		return this.obtieneUrlApi( _url, _providerId, null );
	}
	
	public String obtieneUrlApi( String _url, Long _providerId, String _countryCode )
	{
		String _ret = this.apiHost.trim().replaceAll( "\\/$+", "" ) + 
					  "/" + RestService.API_PREFIX + "/" + 
					  ( ( _countryCode != null ) ? ( _countryCode + "/" ) : "" ) +
					  ( ( _providerId != null ) ? ( _providerId + "/" ) : "" ) + 
					  ( ( _url != null ) ? _url.trim().replaceAll( "^\\/+", "" ) : "" );
		
		logger.debug("La url obtenida es {}",_ret);
		
		return _ret;
	}
}

