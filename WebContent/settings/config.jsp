<%@page import="java.util.Arrays"%>
<%@page import="com.srv.ConfigSrv"%>
<%@page import="com.srv.AdminLoginSrv"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		Object bean = session.getAttribute("adminbean");
		if (bean != null) {
			 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=AdminLoginSrv.Parameters.TITLE %></title>
</head>
<body>
<center>


<br/><br/>

	<h1>Configuration Details</h1><br/><br/>
	
	<form action="ConfigSrv" method="post">
		Connection String: 
		<input type="text" name="<%=ConfigSrv.Parameters.CONN_STRING %>" value="<%=session.getAttribute(ConfigSrv.Parameters.CONN_STRING) %>" required="required"><br/><br/>
		User: 
		<input type="text" name="<%=ConfigSrv.Parameters.USER%>" value="<%=session.getAttribute(ConfigSrv.Parameters.USER) %>" required="required"><br/><br/>
		Password: 
		<input type="password" name="<%=ConfigSrv.Parameters.PASS %>" value="<%=session.getAttribute(ConfigSrv.Parameters.PASS) %>" required="required"><br/><br/>
		
		<input type="submit" value="Update">
		
	</form>
	
	<%session.removeAttribute(ConfigSrv.Parameters.CONN_STRING);
	session.removeAttribute(ConfigSrv.Parameters.PASS);
	session.removeAttribute(ConfigSrv.Parameters.USER);%>


</center>
</body>
</html>
<%}else{response.sendRedirect("index.jsp");}%>