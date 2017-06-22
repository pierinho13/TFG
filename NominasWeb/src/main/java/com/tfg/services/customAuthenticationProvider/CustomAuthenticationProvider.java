package com.tfg.services.customAuthenticationProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import com.tfg.services.userDetailsService.CustomUserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//Son los datos del formulario de login
		
		String principal = authentication.getName(); //sera el username 
		String credentials = (String)authentication.getCredentials();
		
		User user = (User) customUserDetailsService.loadUserByUsername(principal);
		
		if(user != null){
			
			if(credentials.equals(user.getPassword())){ // se compara lo que hay en el login con lo que hay almacenado en base de datos
				
				logger.debug("Usuario autenticado correctamente");
				return new UsernamePasswordAuthenticationToken(principal, credentials,user.getAuthorities());
			}else{
				logger.error("Error de login");
				throw new BadCredentialsException("No coincide usuario o contraseña, o no existe");
			}
		}else{
			logger.error("Error de login");
			throw new BadCredentialsException("No coincide usuario o contraseña, o no existe");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
