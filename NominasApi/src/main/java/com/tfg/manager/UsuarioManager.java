package com.tfg.manager;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tfg.model.RoleUsuario;
import com.tfg.model.Usuario;
import com.tfg.repository.UsuarioRepository;
import com.tfg.utils.dto.RoleUsuarioDto;
import com.tfg.utils.dto.UsuarioDto;

@Service
public class UsuarioManager {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public UsuarioDto getUserForLogin(String username) {
		
		UsuarioDto usuarioDto = usuarioRepository.findUsuarioForLoginByUsername(username);
		Usuario usuario = usuarioRepository.findByIdAndEmpresaId(usuarioDto.getId(), usuarioDto.getEmpresaId());
	    
		Set<RoleUsuario> roles = usuario.getRoles();
	    Set<RoleUsuarioDto> rolesDto = new HashSet<>();
	    
		if(!CollectionUtils.isEmpty(roles)){
			
			for(RoleUsuario r : roles){
				
				RoleUsuarioDto roleDto = new RoleUsuarioDto(r.getId(), r.getNombre(), r.getIsSuperAdmin());
				rolesDto.add(roleDto);
			}
		}
		usuarioDto.setRoles(rolesDto);
		
		return usuarioDto;
	}
}
