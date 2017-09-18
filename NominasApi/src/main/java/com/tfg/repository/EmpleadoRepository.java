package com.tfg.repository;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.model.Empleado;

@Transactional(readOnly = true)
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long>, EmpleadoRepositoryCustom {

	Empleado findById(Long id);
	
	Empleado findByIdAndEmpresaId(Long id, Long empresaId);
	
	List<Empleado> findByEmpresaId(Long empresaId);
	
	
}

