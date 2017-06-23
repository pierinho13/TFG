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
	
	//@Value("${url.api}")// si lo tuviera metido en el propierties q no tengo
	private String apiHost = "http://localhost:8020";
	
	private final RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public RestService(){
		this.restTemplate = new RestTemplate();
	}
	
	public Object get( String url ){
		return this.get( url, null );
	}
	
	public Object get( String url, Map<String, ?> params ){
		return this.get( url, params, Object.class );
	}
	
	public Object get( String url, Map<String, ?> params, Class<?>  clazz ){
		if( params == null ){
			
			params = new HashMap<>();
		}
			
		
		
		logger.info( "GET: Se va a llamar a la url: " + url );
		
		UriComponentsBuilder  builder = UriComponentsBuilder.fromUriString( url );
		
		for( String  key : params.keySet() ){
			
			builder =  builder.queryParam(  key, params.get(  key ) );
		}
		
		
		URI  uri =  builder.build().encode().toUri();
		
		return this.restTemplate.getForObject(  uri,  clazz );
	}
	
	public Object post( String url, Map<String, ?> params ){
		if( params == null ){
			
			params = new HashMap<>();
		}
			

		logger.info( "POST: Se va a llamar a la url: " + url );
		
		return this.restTemplate.postForObject( url, params, Object.class );
	}
	
	public Object put( String url, Map<String, ?> params ){
		return this.deletePut(url, params, HttpMethod.PUT );
	}
	
	public Object delete( String url, Map<String, ?> params ){
		return this.deletePut(url, params, HttpMethod.DELETE );
	}
	
	private Object deletePut( String url, Map<String, ?> params, HttpMethod  method ) {
		if( params == null ){
			
			params = new HashMap<>();
		}
			

		logger.info(  method + ": Se va a llamar a la url: " + url );
		
		HttpHeaders  headers = new HttpHeaders();
		
		 headers.setContentType( MediaType.APPLICATION_JSON );
		
		HttpEntity<Map<String, ?>> requestUpdate = new HttpEntity<>( params,  headers );
		
		HttpEntity<Object>  response = this.restTemplate.exchange( url,  method, requestUpdate, Object.class, params );
				
		return  response.getBody();
	}
	
	public String obtieneUrlApi( String url ) {
		return this.obtieneUrlApi( url, null );
	}
	
	public String obtieneUrlApi( String url, Long empresaId ) {
		String  ret = this.apiHost.trim().replaceAll( "\\/$+", "" ) + 
				  "/" + 
				  ( ( empresaId != null ) ? ( empresaId + "/" ) : "" ) + 
				  ( ( url != null ) ? url.trim().replaceAll( "^\\/+", "" ) : "" );
	
		logger.debug("La url obtenida es {}", ret);
	
		return  ret;
	}
	
}

