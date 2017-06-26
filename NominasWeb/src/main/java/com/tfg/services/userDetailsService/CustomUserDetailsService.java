package com.tfg.services.userDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tfg.model.AppUser;
import com.tfg.services.rest.RestService;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	private final static String USUARIO_URL = "/users/byUsername/";
	
	@Autowired
	private RestService restService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if( username == null || username.trim().equals( "" ) ){
			
			throw new UsernameNotFoundException( "Se intenta hacer login sin usuario." );
		}
		
		JsonObject userData = this.obtieneJsonUsuario( username );
		
		AppUser appUser = this.obtieneAppUserFromJsonObject( userData );
		
		logger.debug( "created a new AppUser: {}.", appUser.getUsername() );
		
		return appUser;
	}
	
	private AppUser obtieneAppUserFromJsonObject( JsonObject json ) throws UsernameNotFoundException{
		Long userId = json.get( "id" ).getAsLong();
		String username = json.get( "username" ).getAsString();
		String password = json.get( "password" ).getAsString();
		String empresa = json.get( "empresa" ).getAsString();
		Long empresaId = json.get( "empresaId" ).getAsLong();
		Long empleadoId = json.get( "empleadoId" ).getAsLong();
		String empleado = json.get( "empleado" ).getAsString();
		
		if( userId == null || username == null || username.trim().equals( "" ) || password == null || password.trim().equals( "" ) ||  empresaId == null )
			
			throw new UsernameNotFoundException( "Los datos del usuario no son correctos." );
		
		
		JsonArray jsonRoles = json.get( "roles" ).getAsJsonArray();
		
		Set<SimpleGrantedAuthority> roles = new HashSet<>(); 
				
		for( Object obj : jsonRoles )
		{
			if( ! ( obj instanceof JsonObject ) )
				
				continue;
			
			SimpleGrantedAuthority rol = this.obtieneRolFromJsonObject( ( JsonObject )obj );
			
			if( rol != null )
				
				roles.add( rol );
		}
		
		if( roles == null || roles.size() <= 0 )
			
			throw new UsernameNotFoundException( "No se han podido recuperar los roles del usuario " + username );
		
		AppUser user = new AppUser( username, password, roles );
		
		user.setEmpresa(empresa);
		
		user.setEmpresaId(empresaId);
		
		user.setNombre(username);
		
		user.setEmpleadoId(empleadoId);
		
		user.setEmpleado(empleado);
		
		return user;
	}
	
	private SimpleGrantedAuthority obtieneRolFromJsonObject( JsonObject json )
	{
		String nombre = json.get( "nombre" ).getAsString();
		
		if( nombre == null || nombre.trim().equals( "" ) )
			
			throw new UsernameNotFoundException( "Uno de los roles del usuario no es válido" );
		
		return new SimpleGrantedAuthority( nombre );
	}
	
	private JsonObject obtieneJsonUsuario( String username ) throws UsernameNotFoundException{
		logger.info( "Vamos a recuperar los datos del usuario " + username );
		
		String response = ( String )this.restService.get( this.restService.obtieneUrlApi( CustomUserDetailsService.USUARIO_URL + username ), null, String.class );
		
		if( response == null || response.trim().equals( "" ) ){
			
			throw new UsernameNotFoundException( "No se ha recibido respuesta del api al intentar recuperar el usuario." );
		}
		
		JsonElement json = ( new JsonParser() ).parse( response );
		
		if( json == null ){
			
			throw new UsernameNotFoundException( "No se ha podido interpretar la respuesta del api al recuperar el usuario." );
		}
			
		
		JsonObject data = json.getAsJsonObject().getAsJsonObject();
		
		if( data == null )
			
			throw new UsernameNotFoundException( "No se han podido recuperar los datos del usuario" + username );
		
		return data;
	}
}
