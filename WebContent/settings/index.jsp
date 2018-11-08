<%@page import="com.srv.AdminLoginSrv"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=AdminLoginSrv.Parameters.TITLE %></title>
</head>
<body>
<center>
	<br/><br/>

	<h1>Login</h1><br/><br/>
	
	<form action="AdminLoginSrv" method="post">
		<input type="text" name="<%=AdminLoginSrv.Parameters.USERNAME %>" placeholder="Enter Username" required="required"><br/><br/>
		<input type="password" name="<%=AdminLoginSrv.Parameters.PASSWORD %>" placeholder="Enter Password" required="required"><br/><br/>
		<input type="submit" value="Login">
		
	</form>


</center>

</body>
</html>