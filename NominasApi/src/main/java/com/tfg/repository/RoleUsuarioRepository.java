package com.tfg.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.model.RoleUsuario;
import com.tfg.model.Usuario;

@Transactional(readOnly = true)
public interface RoleUsuarioRepository extends PagingAndSortingRepository<RoleUsuario, Long>{

	
	
}

