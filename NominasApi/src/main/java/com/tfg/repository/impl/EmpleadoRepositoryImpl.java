package com.tfg.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.tfg.model.TipoEmpleado;
import com.tfg.repository.EmpleadoRepositoryCustom;
import com.tfg.utils.commands.search.FilterEmpleadoCommand;
import com.tfg.utils.dto.EmpleadoDto;
import com.tfg.utils.services.QueryUtils;

public class EmpleadoRepositoryImpl implements EmpleadoRepositoryCustom{
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private QueryUtils queryUtils;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<EmpleadoDto> listadoEmpleados(Long empresaId, FilterEmpleadoCommand command, Pageable pageable) {

		String fromJpql = " from Empleado e  ";
		String jpql = "select new com.tfg.utils.dto.EmpleadoDto (e.id, e.nombre, e.apellidos,e.fechaNacimiento, "
				+ "e.fechaAlta,e.tipoEmpleado) " + fromJpql;
		String sujetoJpqlCount = "select count(e) " + fromJpql;
		String predicadoJpql = " where e.empresa.id=:empresaId ";
		
		Map<String, Object> parametrosQuery = new HashMap<String, Object>();
		parametrosQuery.put("empresaId", empresaId);
		
		predicadoJpql = queryUtils.likeSiEsNecesario(command.getNombre(), " and FUNCTION('unaccent',lower(e.nombre)) like FUNCTION('unaccent',lower(:nombre)) ", predicadoJpql, parametrosQuery,
				StringUtils.isNotBlank(command.getNombre()));
		predicadoJpql = queryUtils.likeSiEsNecesario(command.getApellidos(), " and FUNCTION('unaccent',lower(e.apellidos)) like FUNCTION('unaccent',lower(:apellidos)) ", predicadoJpql, parametrosQuery,
				StringUtils.isNotBlank(command.getApellidos()));
		predicadoJpql = queryUtils.concatenaSiEsNecesario(command.getTipoEmpleado(), " and e.tipoEmpleado = :tipoEmpleado", predicadoJpql, parametrosQuery);
		predicadoJpql = queryUtils.concatenaSiEsNecesario(command.getFechaNacimiento(), " and e.fechaNacimiento <= :fechaNacimiento ", predicadoJpql, parametrosQuery);
		predicadoJpql = queryUtils.concatenaSiEsNecesario(command.getFechaAlta(), " and e.fechaAlta <= :fechaAlta ", predicadoJpql, parametrosQuery);
		
		String jpqlCount = sujetoJpqlCount + predicadoJpql;
		long numRegistros = queryUtils.cuentaRegistros(em.createQuery(jpqlCount), parametrosQuery);
		Page<EmpleadoDto> empleados;
		if (numRegistros > 0) {
			Query query = queryUtils.configuraPaginacionEnJpql(jpql + predicadoJpql, pageable, em, parametrosQuery);
			empleados = new PageImpl<EmpleadoDto>(query.getResultList(), pageable, numRegistros);
		} else {
			empleados = new PageImpl<EmpleadoDto>(new ArrayList<EmpleadoDto>(0), pageable, 0);
		}
		return empleados;
	}
	

}
