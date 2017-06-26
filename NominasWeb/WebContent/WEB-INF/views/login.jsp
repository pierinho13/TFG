<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio sesion</title>
<link rel="stylesheet" href="/nominas/assets/estilos-comunes/style.css" media="screen" type="text/css" />
</head>
<body data-login="PAGINA_DE_LOGIN">
<header class="header-login">TFG  Piero Rospigliosi </header>
<div class="login-card">
    <h1>Acceso <img class="candado" src="/nominas/assets/img/candado.png"></h1><br>
  <form name='f' action="j_spring_security_check" method='POST'>
      <table>
         <tr>
            <td>Usuario:</td>
            <td><input type='text' name='j_username' value=''></td>
         </tr>
         <tr>
            <td>Contraseña:</td>
            <td><input type='password' name='j_password' /></td>
         </tr>
         <tr>
            <td>Recordar sesión:</td>
            <td><input type='checkbox' name='_spring_security_remember_me' checked="checked"/></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" class="login login-submit" value="Iniciar" /></td>
         </tr>
      </table>
  </form>
<!--   <form> -->
<!--     <input type="text" name="user" placeholder="Username"> -->
<!--     <input type="password" name="pass" placeholder="Password"> -->
<!--     <input type="submit" name="login" class="login login-submit" value="Acceder"> -->
<!--   </form> -->

<!--   <div class="login-help"> -->
<!--     <a href="#">¿Olvidaste la contraseña?</a> -->
<!--   </div> -->
	<c:if test="${param.error != null}">
		<span style="color:red;"> Usuario y/o contraseña no válidos</span>
   </c:if>  
</div>
</body>
</html>
<script type="text/javascript">
</script>