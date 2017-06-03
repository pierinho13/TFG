<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../includes/tlds.jsp"%>

<geo:historial url="${historyUrl}" />
<geo:deepLinkHead title="Cliente ${cliente.razonSocial}" modulo="crm" />

<c:set var="conAcciones" value="${ puedeEditarLaEntidad }"/>

<button data-url-back="/crm/clientes/listados/todos" class="bandaBack btn"><i class="fa fa-arrow-left"></i> <fmt:message key="EMPLEADO.VOLVER"/></button>
<sec:authentication property="principal.tieneFacturacionElectronica" var="tieneFacturacionElectronica"/>

<c:if test="${ !puedeConsultarLaEntidad }">

	<div class="alert alert-warning m-t-10" data-ng-if="orden.orden.avisos.length">
		<i class="fa fa-warning"></i><strong> <fmt:message key="PERMISOS.NO_TIENES_PERMISO_PARA_VISUALIZAR_EL_CLIENTE_COMPLETO"/></strong>
	</div>

</c:if>

<c:if test="${ puedeConsultarLaEntidad }">

	<div class="quicklinks clearfix">
		<sec:authorize access="hasAnyRole('COMERCIAL')">
		<div>
			<div class="quick-button">
				<a title="Listado de presupuestos"
					href="/crm/clientes/${cliente.id}/presupuestos">
					<i class="igeo igeo-presupuestos"></i>
					<p><fmt:message key="CLIENTE.PRESUPUESTOS"/></p> <span class="notification green">${presupuestos == null ? 0 : presupuestos}</span>
				</a>
				<fmt:message key="CRM_SIDEBAR.NUEVO_PRESUPUESTO" var="msg_nuevo_presupuesto"/>
				<sec:authorize access="hasAnyRole('COMERCIAL')">
					<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
						<span class="newOne" title="${msg_nuevo_presupuesto}"
						onclick="loadPage('/crm/presupuestos/crearPresupuesto/cliente/${cliente.id}')"><i
						class="fa fa-pencil"></i></span>
					</c:if>
				</sec:authorize>
			</div>
		</div>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('COMERCIAL')">
		<div>
			<div class="quick-button">
				<a title="Listado de Contratos realizados a este cliente"
					href="/crm/clientes/${cliente.id}/contratos">
					<i class="igeo igeo-contratos"></i>
					<p><fmt:message key="CLIENTE.CONTRATOS"/></p> <span class="notification green">${contratosTotales == null ? 0 : contratosTotales}</span>
				</a>
				<sec:authorize access="hasAnyRole('COMERCIAL')">
					<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
						<span class="newOne" title="Nuevo contrato"
						onclick="loadPage('/crm/contratos/crearContratoCliente_p1/${cliente.id}?new=true')"><i
						class="fa fa-pencil"></i></span>
					</c:if>
				</sec:authorize>
			</div>
		</div>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('COMERCIAL')">
		<div>
			<div class="quick-button">
				<a title="Listado de Ventas realizadas a este Cliente"
					href="/crm/clientes/${cliente.id}/ventas">
					<i class="igeo igeo-venta"></i>
					<p><fmt:message key="CLIENTE.VENTAS_TRAT_PUNTUALES"/></p> <span class="notification green">${ventas == null ? 0 : ventas}</span>
				</a>
				<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
					<span class="newOne" title="Nueva venta a este Cliente"
					onclick="loadPage('/crm/ventas/crearVenta_p1/${cliente.id}?new=true')"><i
					class="fa fa-pencil"></i></span>
				</c:if>
			</div>
		</div>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('COMERCIAL','AGENDADOR')">
		<div>
			<a class="quick-button" title="Ordenes de trabajo con este cliente"
				href="/crm/clientes/${cliente.id}/ordenDeTrabajos">
				<i class="igeo igeo-trabajos-pendientes"></i>
				<p><fmt:message key="CLIENTE.TRABAJOS"/></p> <span class="notification orange">${odts == null ? 0 : odts}</span>
			</a>
		</div>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('FACTURADOR, COMERCIAL')">
		<div>
			<a class="quick-button" title="Facturas con este cliente"
				href="/crm/clientes/${cliente.id}/facturas">
				<i class="igeo igeo-facturas"></i>
				<p><fmt:message key="CONTRATO.FACTURAS_C"/></p> <span class="notification green">${facturas == null ? 0 : facturas}</span>
			</a>
		</div>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('COMERCIAL','AGENDADOR')">
		<div>
			<a class="quick-button" title="Recibos de este cliente"
				href="/crm/clientes/${cliente.id}/recibos">
				<i class="fa fa-eur"></i>
				<p>Recibos</p> <span class="notification orange">${recibos == null ? 0 : recibos}</span>
			</a>
		</div>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('COMERCIAL','AGENDADOR')">
		<div>
			<a class="quick-button" title="Accesos para el Portal Cliente"
				href="/crm/clientes/${cliente.id}/accesosPortalCliente">
				<i class="igeo igeo-accesos-portal-cliente"></i>
				<p><fmt:message key="CLIENTE.ACCESOS"/><br/><fmt:message key="CONFIGURACIONINDEX.PORTAL_CLIENTE"/></p> <span class="notification green">${accesos == null ? 0 : accesos}</span>
			</a>
		</div>
		</sec:authorize>
		<div>
			<a class="quick-button" title="Avisos"
				href="/avisos/avisos/listados/abiertos/${cliente.id}/cliente">
				<i class="igeo igeo-accesos-portal-cliente"></i>
				<p>Avisos</p> <span class="notification orange">${avisos == null ? 0 : avisos}</span>
			</a>
		</div>
		<sec:authorize access="hasAnyRole('ADMIN','DIRECTOR','FACTURADOR','DIRECTOR_FACTURADOR')">
		<div>
			<a class="quick-button" title="Costes del cliente"
				href="/crm/clientes/${cliente.id}/costes">
				<i class="fa fa-money""></i>
				<p>Costes</p> <c:if test="${rentabilidadDelCliente !=null}"><span class="notification green">${rentabilidadDelCliente}%</span></c:if>
			</a>
		</div>
		</sec:authorize>
	</div>
	
</c:if>

<div class="clearfix"></div>
<c:set var="labelInactivo" value="" />
<c:set var="classInactivo" value="" />
<c:if test="${not empty cliente.estado and cliente.estado eq 'INACTIVO'}">
	<c:set var="labelInactivo">
		<span class="label label-warning"><fmt:message key="EMPLEADO.INACTIVO_MAYUS"/></span>
	</c:set>
	<c:set var="classInactivo" value="tituloClienteInactivo" />
</c:if>
<c:choose>
	<c:when test="${cliente.tipoCliente eq 'PARTICULAR'}">
	<c:choose>
		<c:when test="${cliente.nombreCompleto eq cliente.razonSocial}">
			<h1 class="${classInactivo}"><fmt:message key="CONTRATO.CLIENTE"/>: [${cliente.numeroDeCliente}]	${cliente.nombreCompleto} ${labelInactivo}</h1>
		</c:when>
		<c:otherwise>
			<h1 class="${classInactivo}"><fmt:message key="CONTRATO.CLIENTE"/>: [${cliente.numeroDeCliente}]	${cliente.nombreCompleto}  (${cliente.razonSocial}) ${labelInactivo}</h1>
		</c:otherwise>
	</c:choose>
	</c:when>
	<c:otherwise>
		<h1 class="${classInactivo}"><fmt:message key="CONTRATO.CLIENTE"/>: [${cliente.numeroDeCliente}]	${cliente.nombre}  (${cliente.razonSocial}) ${labelInactivo}</h1>
	</c:otherwise>
</c:choose>

<div class="box">
	<div class="box-header">
		<strong><i class="fa fa-building-o"></i> <fmt:message key="CLIENTE.SEDES_DIRECCIONES_SERVICIO"/></strong>
		<div class="box-icon">
			<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
		</div>
	</div>

	<div class="box-content container">
		<div class="checkbox m-b-5">
			<input type="checkbox" id="ocultaInactivas" onclick="ocultaSedesInactivas()"/>
			<label for="ocultaInactivas"> Ocultar sedes inactivas </label>
		</div>
		<div id="etiquetasCliente">
			<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
				<div class="pull-right">
					<fmt:message key="CLIENTE.GESTIONAR_ETOQUETAS_ESTE_CLIENTE" var="msg_gestionar_etiquetas"/>
					<button type="button pull-right" class="btn btn-warning editarEntityModalBtn" data-modal-title="${msg_gestionar_etiquetas}" title="${msg_gestionar_etiquetas}" data-en="etiquetasCliente" data-entity-id="${cliente.id}"><i class="fa fa-tag"></i> <fmt:message key="CLIENTE.GESTIONAR_ETIQUETAS"/></button>
				</div>
			</c:if>
			<c:forEach items="${etiquetas}" var="etiqueta">
				<span id="etiquetaCliente_${etiqueta.id}" onclick="filtrarSedesPorEtiqueta(${etiqueta.id})">
					<span class="btn btn-xs" data-id="${etiqueta.id}" style="background-color: ${etiqueta.colorBg}; color: ${etiqueta.colorFg}"><i class="fa fa-tag"></i> ${etiqueta.nombre}</span> 
				</span>
			</c:forEach>
			<span id="eliminarFiltros" style="display: none" onclick="eliminarFiltros()">
				<span class="btn btn-xs" style="color: red"><i class="fa fa-times"></i> <fmt:message key="CLIENTE.ELIMINAR_FILTROS"/></span> 
			</span>
		</div>
		<table class="table table-striped tabla-movil">
			<thead>
				<tr>
					<th class="ordenable"><fmt:message key="GLOBAL.NOMBRE"/></th>
					<th class="ordenable"><fmt:message key="CONFIG_EMPRESA.DELEGACION"/></th>
					<th class="ordenable"><fmt:message key="BUSCAR_POTENCIALES_MODULO.ACTIVIDAD"/></th>
					
					<c:if test="${ puedeConsultarLaEntidad }">
						<th class="ordenable"><fmt:message key="GLOBAL.TELEFONOS.MINUS"/></th>
					</c:if>
					
					<th class="ordenable"><fmt:message key="GLOBAL.DIRECCION_MINUS"/></th>
					<th class="ordenable"><fmt:message key="LEGIONELA.LOCALIDAD"/></th>
					<th class="ordenable"><fmt:message key="EMPLEADO.PROVINCIA"/></th>
					<sec:authorize access="hasAnyRole('COMERCIAL', 'AGENDADOR')">
						<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
							<th class="text-right"><fmt:message key="EMPLEADO.ACCIONES"/></th>
						</c:if>
					</sec:authorize>
				</tr>
			</thead>
			<tbody id="sedeList" class="sedeList">
				<c:forEach items="${sedesCliente}" var="sede"> <%-- TODO optimizar esto: usar una query con DTOs --%>
					<%@ include file="/jsp/crm/sedes/sede.jsp"%>
				</c:forEach>
			</tbody>
		</table>
		<sec:authorize access="hasAnyRole('COMERCIAL', 'AGENDADOR')">
			<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
				<fmt:message key="CREAR_EDITAR_PRESUPUESTO.NUEVA_SEDE" var="msg_nueva_sede"/>
				<button type="button"
					data-url-suf="/${cliente.id}?tipoEntidad=CLIENTE" data-en="sede"
					class="btn btn-success crearEntityModalBtn"
					data-modal-title="${msg_nueva_sede}">
					<i class="fa fa-plus"></i> ${msg_nueva_sede}
				</button>
			</c:if>
		</sec:authorize>
		<c:set value="${ not empty cliente.sedes and fn:length( cliente.sedes ) gt 1}" var="fusionable"/>
		<sec:authorize access="hasAnyRole('ADMIN', 'DIRECTOR')">
			<button id="fusionarSedesBtn" type="button" data-entity-id="${cliente.id}" class="btn btn-primary fusionarSedesBtn ${!fusionable ? 'dispNone' : ''}"> <i class="fa fa-copy"></i> <fmt:message key="CLIENTES.FUSIONAR_SEDES"/></button>
		</sec:authorize>

	</div>
</div>
<div class="box">
	<div class="box-header">
		<strong><i class="fa fa-building"></i> <fmt:message key="CLIENTE.DATOS_DE_EMPRESA"/></strong>
		<div class="box-icon">
			<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
		</div>
	</div>
	<div class="box-content container">
		<ul class="row flexbox-horizontal">
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="BUSCAR_POTENCIALES_MODULO.TIPO_CLIENTE"/>:</dt>
					<dd><fmt:message key="TIPO_CLIENTE.${cliente.tipoCliente}" /></dd>
				</dl>
			</li>
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="BUSCAR_POTENCIALES_MODULO.DELEGACION"/>:</dt>
					<dd>${cliente.delegacion.nombre}</dd>
				</dl>
			</li>
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="GLOBAL.ACTIVIDAD_MINUS"/>:</dt>
					<dd>${cliente.actividad.nombre}</dd>
				</dl>
			</li>
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="ORIGENES_CLIENTE.ORIGEN_CLIENTE"/>:</dt>
					<dd>${cliente.captadoPor.nombre }</dd>
				</dl>
			</li>
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="BUSCAR_POTENCIALES_MODULO.GESTIONADO_POR"/>:</dt>
					<dd>${cliente.gestionadoPor.nombre}</dd>
				</dl>
			</li>
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="BUSCAR_POTENCIALES_MODULO.ZONA_COMERCIAL"/>:</dt>
					<dd>${cliente.zonaComercial.nombre}</dd>
				</dl>
			</li>
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="BUSCAR_CLIENTES.CODIGO_SECUNDARIO"/>:</dt>
					<dd>${cliente.codigoSecundario}</dd>
				</dl>
			</li>
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt>URL:</dt>
					<dd><a href="${cliente.url}" target="_top">${cliente.url}</a></dd>
				</dl>
			</li>			
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="CREAR_CLIENTE_POTENCIAL.FECHA_DE_ALTA"/>:</dt>
					<dd><fmt:formatDate value="${cliente.fechaAlta}" pattern="dd/MM/yyyy"/></dd>
				</dl>
			</li>
			
			<c:if test="${not empty cliente.fechaAltaPotencial }">
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CLIENTE.ALTA_COMO_POTENCIAL"/>:</dt>
						<dd><fmt:formatDate value="${cliente.fechaAltaPotencial}" pattern="dd/MM/yyyy"/></dd>
					</dl>
				</li>
			</c:if>
				
			<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
				<dl class="lista-datos">
					<dt><fmt:message key="CLIENTE.IDIOMA"/>:</dt>
					<dd>${cliente.idioma.nombre}</dd>
				</dl>
			</li>			
				
			<c:if test="${ puedeConsultarLaEntidad }">
			
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CLIENTE.TELEFONO_CORPORATIVO"/>:</dt>
						<dd>${cliente.datosContacto.telefono}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CLIENTE.MOVIL_CORPORATIVO"/>:</dt>
						<dd>${cliente.datosContacto.movil}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-62 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CLIENTE.EMAIL_CORPORATIVO"/>:</dt>
						<dd><a href="mailto:${cliente.datosContacto.email}">${cliente.datosContacto.email}</a></dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="GLOBAL.FAX"/>:</dt>
						<dd>${cliente.datosContacto.fax}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="PRODUCTOS.CLASIFICACION"/>:</dt>
						<dd>${cliente.clasificacionCliente.nombre}</dd>
					</dl>
				</li>			
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt>Riesgo económico:</dt>
						<dd><c:if test="${not empty cliente.riesgoEconomico}"><fmt:message key="RIESGO_ECONOMICO.${cliente.riesgoEconomico}"/></c:if></dd>
					</dl>
				</li>
				
				<li class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CONTRATO.ESTE_DOCUMENTO_REQUIERE_IMPRESO"/>:</dt>
						<dd><fmt:message key="GLOBAL.${geo:bool(cliente.requiereLlevarDocsImpresos)}"/></dd>
					</dl>
				</li>
						
				<li class="col-sm-12">
					<dl class="lista-datos">
						<dt><fmt:message key="CREAR_CLIENTE.OBSERVACIONES_COMERCIALES"/>:</dt>
						<dd>${geo:replaceNewLine(cliente.observaciones)}</dd>
					</dl>
				</li>
			
				<c:if test="${not empty cliente.importadoDesde and cliente.importadoDesde ne ''}">
					<li class="col-sm-12">
						<dl class="lista-datos">
							<dt><fmt:message key="CLIENTE.DATOS_IMPORTACION"/>:</dt>
							<dd>${geo:replaceNewLine(cliente.datosImportacion)}</dd>
						</dl>
					</li>
				</c:if>
				<c:if test="${not empty cliente.estado and cliente.estado eq 'INACTIVO' and not empty cliente.motivoInactivo and cliente.motivoInactivo ne ''}">
					<li class="col-sm-12">
						<dl class="lista-datos">
							<dt><fmt:message key="CLIENTE.MOTIVO_INACTIVIDAD"/>:</dt>
							<dd>${geo:replaceNewLine(cliente.motivoInactivo)}</dd>
						</dl>
					</li>
				</c:if>
				
				<c:if test="${ not empty clientesPuedenValidarCertificados and clientesPuedenValidarCertificados }">
				
					<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					
						<dl class="lista-datos">
							<dt>Permite validar certificados:</dt>
							<dd><fmt:message key="GLOBAL.${geo:bool(cliente.permiteValidarCertificados)}"/></dd>
						</dl>
					</li>
					
				</c:if>
				
				<li class="col-sm-12">
					<dl class="lista-datos">
						<dt>Listado de técnicos no permitidos para este cliente:</dt>
						<c:forEach items="${cliente.listaNegraTecnicos}" var="tecnico">
							<dd>- ${tecnico.nombreCompleto}</dd>
						</c:forEach>
					</dl>
				</li>
				
			</c:if>
			
		</ul>
	</div>
</div>

<c:if test="${ puedeConsultarLaEntidad }">

	<div class="box">
		<div class="box-header">
			<strong><i class="fa fa-calculator"></i> <fmt:message key="CONFIG_EMPRESA.DATOS_FACTURACION"/></strong>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content container">
			<ul class="row flexbox-horizontal">
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CREAR_CLIENTE.NOMBRE_FACTURACION_RAZON_SOCIAL"/>:</dt>
						<dd>${cliente.datosFacturacion.nombreFacturacion}</dd>
						</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="GLOBAL.CIF_NIF_ESPACIOS"/>:</dt>
						<dd>${cliente.datosFacturacion.codigoIdentificacion}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="LEGIONELA.DIRECCION"/>:</dt>
						<dd>${cliente.datosFacturacion.direccion}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="EMPLEADO.CODIGO_POSTAL"/>:</dt>
						<dd>${cliente.datosFacturacion.codigoPostal}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="EMPLEADO.LOCALIDAD"/>:</dt>
						<dd>${cliente.datosFacturacion.localidad}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="EMPLEADO.PROVINCIA"/>:</dt>
						<dd>${cliente.datosFacturacion.provincia}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="EMPLEADO.PAIS"/>:</dt>
						<dd>${cliente.datosFacturacion.pais}</dd>
					</dl>
				</li>
				<c:if test="${not empty cliente.metodoPago}">
					<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
						<dl class="lista-datos">
							<dt><fmt:message key="CONTRATO.METODO_PAGO"/>:</dt>
							<dd><fmt:message key="METODO_PAGO.${cliente.metodoPago.codigo}"/></dd>
						</dl>
					</li>
				</c:if>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CLIENTE.EMAILS_DONDE_ENVIAR_FACTURAS"/>:</dt>
						<dd>${cliente.emailsDondeEnviarFacturas}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CREAR_CLIENTE.SUBCUENTA_CONTABLE"/>:</dt>
						<dd><c:if test="${not empty cliente.subCuenta}"><b>430</b> ${cliente.subCuenta}</c:if></dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt>¿SEPA firmado?:</dt>
						<dd><c:if test="${cliente.sepaFirmado != null}">
							<fmt:message key="GLOBAL.${geo:bool(cliente.sepaFirmado)}"/>
							</c:if>
						</dd>
					</dl>
				</li>
				<c:if test="${tieneFacturacionElectronica != null && tieneFacturacionElectronica}">
					<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
						<dl class="lista-datos">
							<dt><fmt:message key="CLIENTE.FACTURACION_ELECTRONICA"/>:</dt>
							<dd>
								<c:if test="${not empty cliente.datosClienteFacturae and cliente.datosClienteFacturae.facturae_activada}">
									<fmt:message key="CLIENTE.ACTIVADA"/>
								</c:if>
								<c:if test="${empty cliente.datosClienteFacturae or !cliente.datosClienteFacturae.facturae_activada}">
									<fmt:message key="CLIENTE.DESACTIVADA"/>
								</c:if>
							</dd>
						</dl>
					</li>
				</c:if>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CREAR_CLIENTE.OBSERVACIONES_FACTURACION"/>:</dt>
						<dd>${geo:replaceNewLine(cliente.observacionesFacturacion)}</dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><fmt:message key="CREAR_CLIENTE_POTENCIAL.ZONA_FISCAL"/>:</dt>
						<dd>${cliente.zonaFiscal.nombre}</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>

	<div class="box">
		<div class="box-header">
			<strong><i class="fa fa-calculator"></i> <fmt:message key="CLIENTE.DIRECCION_ENVIO"/></strong>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content container" id="datosDeEnvioCliente">
			<jsp:include page="/jsp/crm/clientes/direccionDeEnvio.jsp"></jsp:include>
		</div>
	</div>
	<div class="box">
		<div class="box-header">
			<strong><i class="fa fa-users"></i> <fmt:message key="CREAR_EDITAR_PRESUPUESTO.PERSONAS_CONTACTO"/></strong>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content container">
				<c:if test="${empty cliente.personasContacto}" var="sinDatosPersona">
					<p class="sinpersona"><fmt:message key="CREAR_EDITAR_PRESUPUESTO.NO_PERSONAS_CONTACTO"/>
					</p>
				</c:if>
				<table class="table table-striped ${sinDatosPersona ? 'dispNone' : '' }">
					<thead>
						<tr>
							<th><fmt:message key="GLOBAL.NOMBRE"/></th>
							<th><fmt:message key="GLOBAL.TELEFONO_MINUS"/></th>
							<th><fmt:message key="GLOBAL.EMAIL_SIN_GUION"/></th>
							<th><fmt:message key="CREAR_CLIENTE_POTENCIAL.CARGO"/></th>
							<th><fmt:message key="CREAR_EDITAR_PRESUPUESTO.DATOS"/></th>
							<sec:authorize access="hasAnyRole('COMERCIAL')">
							<th class="text-right"><fmt:message key="EMPLEADO.ACCIONES"/></th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody id="personaList">
						<c:forEach items="${cliente.personasContacto}" var="persona">
							<%@ include file="/jsp/catalogo/personas/persona.jsp"%>
						</c:forEach>
					</tbody>
				</table>
			<sec:authorize access="hasAnyRole('COMERCIAL')">
				<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
					<fmt:message key="CLIENTE.NUEVA_PERSONA_CONTACTO" var="msg_nueva_persona_contacto"/>
					<button data-url-suf="/${cliente.id}?tipoEntidad=CLIENTE"
						data-en="persona" data-modal-title="${msg_nueva_persona_contacto}"
						class="btn btn-success crearEntityModalBtn">
						<i class="fa fa-plus"></i> ${msg_nueva_persona_contacto}
					</button>
				</c:if>
			</sec:authorize>
		</div>
	</div>
	
	
	<div class="box">
		<div class="box-header">
			<strong><i class="fa fa-money"></i> <fmt:message key="CUENTAS_BANCARIAS.CUENTAS_BANCARIAS"/></strong>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content container">
			<c:if test="${empty cliente.cuentasBancarias}" var="sinDatosCuentaBancariaCliente">
				<p class="sincuentaBancariaCliente"><fmt:message key="CUENTAS_BANCARIAS.NO_CUENTAS"/></p>
			</c:if>
			<table class="table table-striped ${sinDatosCuentaBancariaCliente ? 'dispNone' : '' }">
				<thead>
					<tr>
						<th><fmt:message key="CUENTAS_BANCARIAS.ALIAS"/></th>
						<th><fmt:message key="CUENTAS_BANCARIAS.IBAN"/></th>
						<th><fmt:message key="CUENTAS_BANCARIAS.BANCO"/></th>
						<sec:authorize access="hasAnyRole('COMERCIAL')">
							<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
								<th><fmt:message key="CUENTAS_BANCARIAS.ACCIONES"/></th>
							</c:if>
						</sec:authorize>
					</tr>
				</thead>
				<tbody id="cuentaBancariaClienteList">
					<c:forEach items="${cliente.cuentasBancarias}"
						var="cuentaBancariaCliente">
							<%@ include file="/jsp/crm/clientes/cuentaBancariaCliente.jsp"%>
					</c:forEach>
				</tbody>
			</table>
			<sec:authorize access="hasAnyRole('COMERCIAL')">
				<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
					<fmt:message key="CUENTAS_BANCARIAS.NUEVA_CUENTA" var="msg_nueva_cuenta_bancaria"/> 
					<button
						data-url-suf="/${cliente.id}?tipoEntidad=CLIENTE"
						data-en="cuentaBancariaCliente" class="btn btn-success crearEntityModalBtn"
						data-modal-title="${msg_nueva_cuenta_bancaria}">
						<i class="fa fa-plus"></i> ${msg_nueva_cuenta_bancaria} 
					</button>
				</c:if>
			</sec:authorize>
	
		</div>
	</div>
	
	<sec:authorize access="hasAnyRole('ADMIN', 'DIRECTOR', 'DIRECTOR_TECNICO')">
	
		<div class="box">
		
			<div class="box-header">
			
				<strong><i class="fa fa-ban"></i> <fmt:message key="PERMISOS.PERMISOS_DE_CONSULTA_Y_EDICION"/></strong>
				
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
				</div>
				
			</div>
			
			<div class="box-content container">
			
				<c:if test="${empty permisosParaElCliente}" var="sinPermisos">
					<p class="sinPermisosParaElCliente"><fmt:message key="PERMISOS.NO_HAY_EMPLEADOS_CON_PERMISOS_PARA_ESTE_CLIENTE"/></p>
				</c:if>
				
				<table class="table table-striped ${sinPermisos ? 'dispNone' : '' }">
				
					<thead>
					
						<tr>
							<th><fmt:message key="PERMISOS.NOMBRE_EMPLEADO"/></th>
							<th><fmt:message key="PERMISOS.LECTURA_Y_ESCRITURA"/></th>
							<th align="right"><fmt:message key="PERMISOS.ACCIONES"/></th>
						</tr>
						
					</thead>
					
					<tbody id="permisoEmpleadoEntidadList">
						<c:forEach items="${permisosParaElCliente}"
							var="permisoEmpleadoEntidad">
								<%@ include file="/jsp/crm/permisoEmpleadoEntidad/permisoEmpleadoEntidad.jsp"%>
						</c:forEach>
					</tbody>
					
				</table>
				
				<fmt:message key="PERMISOS.ANADIR_PERMISO_PARA_EL_EMPLEADO" var="msg_anadir_empleado"/> 
				<button
					data-url-suf="/${cliente.id}?tipoEntidad=CLIENTE"
					data-en="permisoEmpleadoEntidad" class="btn btn-success crearEntityModalBtn"
					data-modal-title="${msg_anadir_empleado}">
					<i class="fa fa-plus"></i> ${msg_anadir_empleado} 
				</button>
		
			</div>
		</div>
	
	</sec:authorize>
	
	<div class="box">
		<div class="box-header">
				<strong><i class="fa fa-comments-o"></i> <fmt:message key="BUSCAR_POTENCIALES_MODULO.COMO_NOS_CONOCISTE"/></strong>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i class="fa fa-chevron-down"></i></a>
				</div>
		</div>
		<div class="box-content container dispNone">
			<ul class="row flexbox-horizontal m-b-0">
				<li class="col-xs-12 col-sm-12 col-md-6">
					<dl class="lista-datos">
						<dt><fmt:message key="CLIENTE.MEDIO_POR_QUE_NOS_CONOCISTE"/>:</dt>
						<dd class="m-b-0">
							<c:if test="${cliente.comoNosHaConocido !=null}">
								<fmt:message key="COMONOSHACONOCIDO.${cliente.comoNosHaConocido}" />
							</c:if>
						</dd>
					</dl>
				</li>
				<li class="col-xs-12 col-sm-12 col-md-6">
					<dl class="lista-datos">
						<dt><fmt:message key="PRESUPUESTO.DETALLES"/>:</dt>
						<dd class="m-b-0">${cliente.detallesComoNosHaConocido}</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>
	<c:set var="entidadAnotaciones" value="cliente" />
	<div class="box">
		<div class="box-header">
			<strong><i class="fa fa-comments-o"></i> <fmt:message key="CLIENTE.ANOTACIONES"/></strong>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content container">
			<table id="${entidadAnotaciones}Anotaciones-grid"></table>
			<div id="${entidadAnotaciones}Anotaciones-pager"></div>
			<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
				<button type='button' data-is-mobile='${currentDevice.mobile}'
					data-grid-id='#${entidadAnotaciones}Anotaciones-grid'
					class='btn btn-success nuevaAnotBtn dispNone'>
					<i class="fa fa-plus"></i>  <fmt:message key="CLIENTE.NUEVA_ANOTACION"/>
				</button>
			</c:if>
		</div>
	</div>
	<div class="box">
		<div class="box-header">
			<strong><i class="fa fa-comment-o"></i><fmt:message key="CREAR_CLIENTE_POTENCIAL.REDES_SOCIALES"/></strong>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i class="fa fa-chevron-down"></i></a>
			</div>
		</div>
		<div class="box-content container dispNone">
			<ul class="row flexbox-horizontal">
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><i class="fa fa-facebook ico-red-social" aria-hidden="true"></i>&nbsp;Facebook: </dt>
						<dd><a href="https://facebook.com/${cliente.redSocial.userFacebook}">${cliente.redSocial.userFacebook}</a></dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><i class="fa fa-twitter ico-red-social" aria-hidden="true"></i>&nbsp;Twitter: </dt>
						<dd><a href="https://twitter.com/${cliente.redSocial.userTwitter}">${cliente.redSocial.userTwitter}</a></dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><i class="fa fa-linkedin ico-red-social" aria-hidden="true"></i>&nbsp;Linkedin: </dt>
						<dd><a href="https://es.linkedin.com/in/${cliente.redSocial.userLinkedin}">${cliente.redSocial.userLinkedin}</a></dd>
					</dl>
				</li>
				<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
					<dl class="lista-datos">
						<dt><i class="fa fa-google-plus ico-red-social" aria-hidden="true"></i>&nbsp;Google +: </dt>
						<dd><a href="https://plus.google.com/u/0/${cliente.redSocial.userGooglePlus}/posts">${cliente.redSocial.userGooglePlus}</a></dd>
					</dl>
				</li>
			</div>
		</ul>
	</div>
	<div class="botonera botonera-con-borde">
		<sec:authorize access="hasAnyRole('COMERCIAL')">
			<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
				<button type="button" class="btn btn-primary editarEntityBtn"
					data-en="cliente" data-entity-id="${cliente.id}" data-modulo="crm">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					<fmt:message key="PRESUPUESTO.EDITAR"/>
				</button>
			</c:if>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('COMERCIAL')">
			<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
				<fmt:message key="CLIENTE.SE_BORRARAN_TODOS_FICHEROS_CARPETAS" var="msg_se_borrara_todos_ficheros"/>
				<fmt:message key="DATA_ART.EL_CLIENTE" var="msg_el_cliente"/>
				<fmt:message key="DATA_ART.CLIENTE" var="msg_cliente"/>
				<button type="button" class="btn btn-danger eliminarEntityBtn"
					data-en="cliente" data-entity-id="${cliente.id}"
					data-nom="${cliente.razonSocial}" data-art="${msg_el_cliente}" data-completa-advertencia="${msg_se_borrara_todos_ficheros}" data-modulo="crm">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					<fmt:message key="EMPLEADO.ELIMINAR"/>
				</button>
			</c:if>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('COMERCIAL','AGENDADOR')">
			<fmt:message key="CLIENTE.GESTIONAR_DOCUMENTOS_DEL_CLIENTE" var="msg_gestionar_documentos"/>
			<a href="/crm/clientes/gestionarDocumentos/${cliente.id}"
				class="btn btn-warning" title="${msg_gestionar_documentos}"><i
				class="fa fa-file-text"></i> <fmt:message key="CLIENTE.DOCUMENTACION_DEL_CLIENTE"/></a>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('DIRECTOR')">
			<c:if test="${ empty puedeEditarLaEntidad or puedeEditarLaEntidad }">
				<fmt:message key="CLIENTE.DESTRUIR_POR_COMPLETO_CLIENTE" var="msg_destruir_por_completo_cliente"/>
				<button 
					type="button" 
					class="btn btn-danger loadPageOnModal" 
					data-url="/crm/clientes/destruirCliente/${cliente.id}" 
					data-modal-title="${msg_destruir_por_completo_cliente}: ${cliente.razonSocial}" ><i class='fa fa-bomb'></i> <fmt:message key="CLIENTE.DESTRUIR"/></button>
			</c:if>
		</sec:authorize>
	</div>
</c:if>

<geo:deepLinkFooter />

<!-- modal para avisar de cambio en datos fiscales -->
<div class="modal fade" id="avisarDeCambiosFiscalesModal" tabindex="-1"
	role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-toggle="modal" data-target="#avisarDeCambiosFiscalesModal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabel"><fmt:message key="CLIENTE.AVISO_SE_HAN_MODIFICADO_LOS_DATOS_FISCALES"/></h4>
			</div>
			<div class="modal-body">
				
				<div class="row">
					<p><fmt:message key="CLIENTE.SE_HAN_MODIFICADO_LOS_DATOS_FISCALES"/></p>
				</div>

				<div class="row">
					<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#avisarDeCambiosFiscalesModal"><i class="fa fa-times" aria-hidden="true"></i> <fmt:message key="CLIENTE.CERRAR"/></button>
					<button type="button" class="btn btn-primary" onclick="loadPage('/crm/clientes/${cliente.id}/contratos',true); $('body').removeClass('modal-open');"><i class="fa fa-arrow-right" aria-hidden="true"></i> Listado de contratos del cliente</button>
				</div>

				<c:if test="${not empty facturasSinEmitir && facturasSinEmitir>0}">
					</br>
					<div class="row">
						<p><fmt:message key="CLIENTE.ESTE_CLIENTE_TIENE"/> ${facturasSinEmitir} <fmt:message key="CLIENTE.FACTURAS_SIN_EMITIR"/></p>
					</div>
					<button type="button" class="btn btn-success" id="actualizarFacturasSinEmitirBtn" >Actualizar facturas sin emitir con los nuevos datos de facturación</button>
					<div id="actualizarFacturasSinEmitirResultado"></div>
				</c:if>
				
				
			</div>
			
		</div>
	</div>
</div>
<!-- fin modal -->
<!-- modal adicional para anadir costes  -->
<div class="modal fade modalModulo" id="crearCosteClienteModal" tabindex="-1"
	role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-toggle="modal" data-target="#crearCosteClienteModal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabel">...</h4>
			</div>
			<div class="modal-body">...</div>
		</div>
	</div>
</div>
<!-- fin modal -->

<script type="text/javascript">

	function filtrarSedesPorEtiqueta(idEtiqueta) {
		$('.sedeList tr').hide();
		$('.etiquetaSedeCliente_' + idEtiqueta).closest('tr').show();
		$('#eliminarFiltros').remove();
		var html = '<span id="eliminarFiltros" onclick="eliminarFiltros()">' +
						'<span class="btn btn-xs" style="color: red"><i class="fa fa-times"></i> Eliminar Filtros</span>' +
					'</span>';
		// de esta forma el botón se crea siempre al final de la lista de etiquetas
		$(html).appendTo($('#etiquetasCliente'));
	}
	function ocultaSedesInactivas(){
		if($('#ocultaInactivas').is(':checked')){
			$('.esSedeInactiva').addClass('dispNone');
		}else{
			$('.esSedeInactiva').removeClass('dispNone');
		}
	}
	function eliminarFiltros() {
		$('.sedeList tr').show();
		$('#eliminarFiltros').remove();
	}
	function importarDatosDeFacturacion(){
		
		if( confirm( "¿Estás seguro que desea reemplazar estos datos por los de facturación?"+
				"\nNombre: "+"${cliente.datosFacturacion.nombreFacturacion}"+"\nDireccion: "+"${cliente.datosFacturacion.direccion}"+
				"\nCodigoPostal:"+"${cliente.datosFacturacion.codigoPostal}"+"\nLocalidad: "+"${cliente.datosFacturacion.localidad}"+
				"\nProvincia: "+"${cliente.datosFacturacion.provincia.nombre}"+"\nTelefono: "+"${cliente.datosContacto.telefono}") )
		{
			loadPage('/crm/clientes/importarDireccionEnvioFromDatosFacturacion/${cliente.id}', false, 'datosDeEnvioCliente');
		}
	}
	function importarDatosDeOrigenCliente(){
		if( confirm( "¿Estás seguro que desea reemplazar estos datos por los de origen cliente?"+
				"\nNombre: "+"${cliente.captadoPor.nombre}"+"\nDireccion: "+"${cliente.captadoPor.datosFacturacion.direccion}"+
				"\nCodigoPostal:"+"${cliente.datosFacturacion.codigoPostal}"+
				"\nLocalidad:"+"${cliente.datosFacturacion.localidad}"+
				"\nProvincia:"+"${cliente.datosFacturacion.provincia.nombre}"+"\nTelefono: "+"${cliente.captadoPor.telefono}") )
		{
			loadPage('/crm/clientes/importarDireccionEnvioFromOrigenCliente/${cliente.id}', false, 'datosDeEnvioCliente');
		}
	}
	
	function localInit() {
		var historyUrl = ""+"${historyUrl}";
		if(historyUrl.includes('vieneDeConvertirClientePotencialEnSede')){
			var  urlSearch = window.location.search;
			urlSearch=urlSearch.replace("?vieneDeConvertirClientePotencialEnSede=","");
			$('html, body').animate({scrollTop:($('#sede_'+urlSearch).position().top)-100}, 'slow');
			var id="#sede_"+urlSearch;
			parpadearTr(id);
		}
		
		var isMobile = ${currentDevice.mobile};
		carga_anotaciones_grid(isMobile, '', '${entidadAnotaciones}',
				'${cliente.id}',
				'<sec:authentication property="principal.empleadoId"/>');
		
		var preguntaParaAvisarCambioDatosFiscales = ${ (avisarCambioDatosFiscales != null && avisarCambioDatosFiscales) ? true : false};
		
		if (preguntaParaAvisarCambioDatosFiscales) {
			$('#avisarDeCambiosFiscalesModal').modal('show');
		} else {
			$('#avisarDeCambiosFiscalesModal').modal('hide');
		}
		
		$(document).on("click", ".fusionarSedesBtn", function( event ) {
			
			var clienteId = $("#fusionarSedesBtn").attr("data-entity-id");
			
			if (clienteId) {
				
				var url = "/crm/sedes/listados/fusionar/" + clienteId;
				
				loadPage(url, true);
				
			}
			
		});	

		$(document).on("click", "#actualizarFacturasSinEmitirBtn", function( event ) {
			postPage("/crm/clientes/actualizarFacturasSinEmitir/${cliente.id}",false,"actualizarFacturasSinEmitirResultado" );
		});

	}
	//Si es ajax se carga al finalizar el DOM, si no, ya lo llamará el global.js
	${isAjax ? "$(function(){localInit();});" : ""}
	
	
</script>
