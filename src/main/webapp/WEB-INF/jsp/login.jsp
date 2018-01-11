<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" 
prefix = "form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de datos</title>
</head>
<body>

	<form:form action="welcome" method="post">
		<span>dni:</span> 
		<form:input  type="text" path="dni"/><br/>
		<span>nombre:</span> 
		<form:input type="text" path="nombre"/> <br/>
		<span>apellidos:</span> 
		<form:input type="text" path="apellido"/><br/>
		<input type="submit"/>
	</form:form>
	</body>
</html>