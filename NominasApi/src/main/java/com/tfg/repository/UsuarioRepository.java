package com.tfg.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.model.Usuario;
import com.tfg.utils.dto.UsuarioDto;

@Transactional(readOnly = true)
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{

	Usuario findByUsername(String usuario);
	
	Usuario findByIdAndEmpresaId(Long id, Long empresaId);
	
	@Query("select new com.tfg.utils.dto.UsuarioDto (u.id, u.username, u.password, e.id,"
			+ "u.fechaAlta, u.esAdmin,u.esComercial,e.nombre) from Usuario u join u.empresa e where u.username =:username")
	UsuarioDto findUsuarioForLoginByUsername(@Param("username") String username);
}

