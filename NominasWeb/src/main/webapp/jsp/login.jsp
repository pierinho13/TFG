<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="/cdncomun/img/icons/igeoapp-icon-192x192.png">
<link rel="icon" sizes="128x128" href="/cdncomun/img/icons/igeoapp-icon-128x128.png">
<link rel="apple-touch-icon" sizes="128x128" href="/cdncomun/img/icons/igeoapp-icon-128x128.png">
<link rel="apple-touch-icon-precomposed" sizes="128x128" href="/cdncomun/img/icons/igeoapp-icon-128x128.png">
<link href="/cdnproviders/assets/css/proveedores-login.css" rel="stylesheet" type="text/css"/>
<head>
<title>Login en Providers</title>
</head>
<body id="providers-web" data-login="PAGINA_DE_LOGIN">
	<div class="container">
	
		<c:if test="${not empty errorMessage and errorMessage ne ''}">
			<div class="row">
				<div class="col-xs-12">
					<div class="alert alert-danger">
						<p>${errorMessage}</p>
					</div>		
				</div>
			</div>
		</c:if>
		<c:if test="${not empty successMessage and successMessage ne ''}">
			<div class="row">
				<div class="col-xs-12">
					<div class="alert alert-success">
						<p>${successMessage}</p>
					</div>		
				</div>
			</div>
		</c:if>
		<header class="header-login">
			<h1 class="header-login__titulo">Portal proveedores</h1>
		</header>					
					
		<section class="login-box">
			<header class="login-box__cabecera">
				<img class="login-box__cabecera__logo" src="/cdnproviders/assets/img/logo-igeo-cp.png" alt="iGEO Cloud Platform." />
			</header>
			
			<form id="login-form" class="form-horizontal" action="/providersweb/login/authenticate" method="post">
				<fieldset>
					
					<legend class="login-box__titulo">Accede con tu cuenta</legend>
					
					<div class="login-form__contenido">
						
						<label for="usuario" class="login-form__label">usuario</label>
						<input class="login-form__input" name="username" type="text"  onkeyup="$('#badCredentialsDiv').hide()"/>

						<label for="password" class="login-form__label">contraseña</label>
						<input class="login-form__input" name="password" id="password" type="password"  onkeyup="$('#badCredentialsDiv').hide()"/>

						<div class="clearfix"></div>
						<c:if test="${param.error eq 'bad_credentials'}">
							<div class="row" id="badCredentialsDiv">
								<div class="col-xs-12">
									<div class="alert alert-danger">
										<p>Usuario o contraseña incorrectos</p>
									</div>		
								</div>
							</div>
						</c:if>
						<c:if test="${param.error eq 'not_allowed_schedule'}">
							<div class="row" id="badCredentialsDiv">
								<div class="col-xs-12">
									<div class="alert alert-danger">
										<p>No puedes usar la aplicación fuera de tu horario permitido</p>
									</div>		
								</div>
							</div>
						</c:if>
						
						<c:if test="${not empty param.sigeouser and param.sigeouser eq 'true'}">
							<input type="hidden" name="sigeouser" value="true" />
						</c:if>
						
						<div>
							<input type="checkbox" class="login-form__checkbox" id="remember-me" name="remember-me" checked="checked"/> 
							<label class="login-form__checkbox__label" for="remember-me">No cerrar sesión</label>
						</div>
						
						
						<div class="login-form__botonera">
							<button type="submit" class="login-form__btn-entrar " id="loginButton">Entrar</button>
						</div>
					</div>
				
				</fieldset>	

			</form>
		</section>	
		
	</div><!--/container-->
	

</body>
</html>