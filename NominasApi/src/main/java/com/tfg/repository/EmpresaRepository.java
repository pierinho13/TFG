package com.tfg.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.tfg.model.Empresa;

@Transactional(readOnly = true)
public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {


	@Query("select e from Empresa e order by e.nombre")
	List<Empresa> obtieneTodasLasEmpresas();
	
	Empresa findById(Long id );
}

