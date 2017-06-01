package com.geoplagas.providers.infrastructure.web.argumentresolvers;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.geoplagas.providers.domain.model.AppUser;


public class AppUserResolver implements WebArgumentResolver
{
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		
		if( methodParameter.getParameterType().equals(AppUser.class)) 
		{
			try
			{
				AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				if (appUser==null) {
					
				    throw new IllegalAccessException("No se ha podido recuperar el AppUser");
				}
				
				return appUser;
			}
			catch( Exception _ex )
			{
				_ex.printStackTrace();
				
				throw new IllegalStateException( "Se ha producido un error al recuperar el appUser" );
			}
			
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}
}
