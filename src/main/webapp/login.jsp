<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Login</title>
<%@include file="includes/header.jsp"%>
</head>
<body class="text-center">
	<%
	String mensaje = (String) request.getAttribute("mensaje");
	if (mensaje != null) {
		out.println(mensaje);
	}
	%>
	<div
		class="container d-flex m-0 p-0 mx-auto justify-content-center align-items-center"
		style="height: 95vh">
		<form method="post" action="login">
			<div class="mb-3">
				<label class="form-label">Usuario</label> <input type="text"
					class="form-control" id="formUsuario" name="formUsuario"
					aria-describedby="usuarioHelp">
				<div id="usuarioHelp" class="form-text">Recuerda acceder con
					tu usuario de administrador para entrar al perfil de gestión.</div>
			</div>
			<div class="mb-3">
				<label class="form-label">Contraseña</label> <input type="password"
					class="form-control" id="formPassword" name="formPassword">
			</div>
			<button type="submit" class="btn btn-primary" data-bs-target="#modal">LOGIN</button>
		</form>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>