package com.tfg.config.argumentresolvers;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.tfg.model.AppUser;

public class AppUserResolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {

		if(methodParameter.getParameterType().equals(AppUser.class)) {
			
			AppUser appUser = ( AppUser ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if (appUser==null) {
				
			    throw new IllegalAccessException("No se ha podido recuperar el AppUser");
			}
			return appUser;
			
		}else {
			
			return WebArgumentResolver.UNRESOLVED;
		}
	}

}
