package com.tfg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tfg.utils.dto.EmpleadoDto;
import com.tfg.utils.commands.search.FilterEmpleadoCommand;

public interface EmpleadoRepositoryCustom {
	
	Page<EmpleadoDto> listadoEmpleados(Long empresaId, FilterEmpleadoCommand command, Pageable pageable);
}
