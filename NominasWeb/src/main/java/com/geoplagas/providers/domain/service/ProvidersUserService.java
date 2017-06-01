package com.geoplagas.providers.domain.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.geoplagas.providers.domain.model.AppUser;
import com.geoplagas.providers.domain.service.rest.RestService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service("providersUserDetailsService")
public class ProvidersUserService implements UserDetailsService
{
	private final static String USER_URL = "/users/byUsername/";
	
	@Autowired
	private RestService restService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserDetails loadUserByUsername( String _username ) throws UsernameNotFoundException
	{
		if( _username == null || _username.trim().equals( "" ) )
			
			throw new UsernameNotFoundException( "Se intenta hacer login sin usuario." );
		
		JsonObject _userData = this.obtieneJsonUser( _username );
		
		AppUser _user = this.obtieneAppUserFromJsonObject( _userData );
		
		logger.debug( "created a new User: {}.", _user.getUsername() );
		
		return _user;
	}
	
	private AppUser obtieneAppUserFromJsonObject( JsonObject _json ) throws UsernameNotFoundException
	{
		Long _userId = _json.get( "id" ).getAsLong();
		String _username = _json.get( "username" ).getAsString();
		String _password = _json.get( "password" ).getAsString();
		String _userStatus = _json.get( "userStatus" ).getAsString();
		Long _providerId = _json.get( "providerId" ).getAsLong();
		
		if( _userId == null || _username == null || _username.trim().equals( "" ) || _password == null || _password.trim().equals( "" ) || _userStatus == null || _providerId == null )
			
			throw new UsernameNotFoundException( "Los datos del usuario no son correctos." );
		
		if( ! _userStatus.trim().equals( "ACTIVE" ) )
			
			throw new UsernameNotFoundException( "El estado del usuario " + _username + " no es correcto. Estado: " + _userStatus );
		
		JsonArray _jsonRoles = _json.get( "roles" ).getAsJsonArray();
		
		Set<SimpleGrantedAuthority> _roles = new HashSet<>(); 
				
		for( Object _obj : _jsonRoles )
		{
			if( ! ( _obj instanceof JsonObject ) )
				
				continue;
			
			SimpleGrantedAuthority _rol = this.obtieneRolFromJsonObject( ( JsonObject )_obj );
			
			if( _rol != null )
				
				_roles.add( _rol );
		}
		
		if( _roles == null || _roles.size() <= 0 )
			
			throw new UsernameNotFoundException( "No se han podido recuperar los roles del usuario " + _username );
		
		AppUser _user = new AppUser( _username, _password, true, true, true, true, _roles );
		
		_user.setProviderId( _providerId );
		
		_user.setUserId( _userId );
		
		return _user;
	}
	
	private SimpleGrantedAuthority obtieneRolFromJsonObject( JsonObject _json )
	{
		String _name = _json.get( "name" ).getAsString();
		
		if( _name == null || _name.trim().equals( "" ) )
			
			throw new UsernameNotFoundException( "Uno de los roles del usuario no es válido" );
		
		return new SimpleGrantedAuthority( _name );
	}
	
	private JsonObject obtieneJsonUser( String _username ) throws UsernameNotFoundException
	{
		logger.info( "Vamos a recuperar los datos del usuario " + _username );
		
		String _response = ( String )this.restService.get( this.restService.obtieneUrlApi( ProvidersUserService.USER_URL + _username ), null, String.class );
		
		if( _response == null || _response.trim().equals( "" ) )
			
			throw new UsernameNotFoundException( "No se ha recibido respuesta del api al intentar recuperar el usuario." );
		
		JsonElement _json = ( new JsonParser() ).parse( _response );
		
		if( _json == null )
			
			throw new UsernameNotFoundException( "No se ha podido interpretar la respuesta del api al recuperar el usuario." );
		
		String _codigoRespuesta = _json.getAsJsonObject().get( "codigo" ).getAsString();
		
		if( _codigoRespuesta == null || ! _codigoRespuesta.trim().equals( "1" ) )
			
			throw new UsernameNotFoundException( "El código de respuesta al recuperar el usuario no es correcto. Código de respuesta: " + _codigoRespuesta + "." );
		
		JsonObject _data = _json.getAsJsonObject().get( "data" ).getAsJsonObject();
		
		if( _data == null )
			
			throw new UsernameNotFoundException( "No se han podido recuperar los datos del usuario" + _username );
		
		return _data;
	}
}
