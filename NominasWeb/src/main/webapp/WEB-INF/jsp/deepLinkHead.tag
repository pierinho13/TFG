<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>

<%@ attribute name="title" required="false" type="java.lang.String" %>
<%@ attribute name="modulo" required="true" type="java.lang.String" %>
<%@ attribute name="incluirSidebarEnAjax" required="false" type="java.lang.Boolean" %>
<%@ attribute name="incluirContentDiv" required="false" type="java.lang.Boolean" %>

<c:set var="nombreModulo" value="${nombreModulos[modulo]}"/>
<c:set var="iconoModulo" value="${iconoModulos[modulo]}"/>
<c:set var="modulo" value="${modulo}" scope="request" />
<c:set var="incluirSidebarEnAjax" value="${incluirSidebarEnAjax != null ? incluirSidebarEnAjax : false}" scope="request" />
<c:set var="incluirContentDiv" value="${incluirContentDiv != null ? incluirContentDiv : true}" scope="request" />

<c:choose>
<c:when test="${!isAjax}">	
<!--  del deep link -->
<!DOCTYPE html>
<html lang="es">
<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<%@ include file="/jsp/includes/js_and_css.jsp"%>
</head>
<body>
	<jsp:include page="/jsp/includes/header.jsp">
		<jsp:param name="nombreModuloHtml" value="<i class='igeo igeo-${iconoModulo}'></i><span>${nombreModulo}</span>"/>
	</jsp:include>
	<div class="container">
		<div class="row" id="mainContent">

			<jsp:include page="/jsp/${modulo}/${modulo}Sidebar.jsp" ></jsp:include>
			<c:if test="${incluirContentDiv}">
			<div id="content" class="<c:if test="${modulo eq 'avisos'}">content-modulo-avisos</c:if> col-lg-10 col-sm-11">
			</c:if>
<!-- fin del deep link -->	
</c:when>
<c:otherwise> <%-- Llamada Ajax standar --%>
<title>${title}</title>
<c:if test="${incluirSidebarEnAjax}">
		<jsp:include page="/jsp/${modulo}/${modulo}Sidebar.jsp"></jsp:include>
</c:if>			
</c:otherwise>
</c:choose>	