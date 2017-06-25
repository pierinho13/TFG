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
	
	//ClasificacionCliente findByIdAndEmpresaId(Long id, Long empresaId);
	
//	@Query("select new com.geoplagas.valueobjects.global.IdTextoData(c.id,c.nombre,c.codigo) "
//			+ " from ClasificacionCliente c where c.empresa.id=:empresaId order by c.nombre")
//	List<IdTextoData> obtieneTodosParaDesplegable(@Param("empresaId") Long empresaId);
//	
//	@Query("select c from ClasificacionCliente c where c.empresa.id=:empresaId order by c.nombre")
//	List<ClasificacionCliente> obtieneTodasLasClasificacionesDeUnaEmpresa(@Param("empresaId")Long empresaId);
	
}

