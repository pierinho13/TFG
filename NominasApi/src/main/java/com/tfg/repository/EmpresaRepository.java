package com.tfg.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.tfg.model.Empresa;

@Transactional(readOnly = true)
public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {

	//ClasificacionCliente findByIdAndEmpresaId(Long id, Long empresaId);
	
//	@Query("select new com.geoplagas.valueobjects.global.IdTextoData(c.id,c.nombre,c.codigo) "
//			+ " from ClasificacionCliente c where c.empresa.id=:empresaId order by c.nombre")
//	List<IdTextoData> obtieneTodosParaDesplegable(@Param("empresaId") Long empresaId);
//	
	@Query("select e from Empresa e order by e.nombre")
	List<Empresa> obtieneTodasLasEmpresas();
	
	Empresa findById(Long id );
}

