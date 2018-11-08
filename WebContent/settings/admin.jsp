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
	<a href="team.jsp">Enter Team Info</a><br/><br/>
	<a href="ques.jsp">Enter Questions</a><br/><br/>
	<a href="ConfigSrv">Change Config</a><br/><br/>
	<a href="GenResultSrv">Generate Result</a><br/><br/><br/><br/>
	<a href="AdminLogoutSrv">Logout</a><br/><br/><br/><br/>
	
	<%
	if(session.getAttribute(ConfigSrv.Parameters.FLAG)!=null){
		
	%>
	<span style="color: green;font-size: 18px;font-weight: bold;">File Updated!!</span><br/>
	<%
		session.removeAttribute(ConfigSrv.Parameters.FLAG);
	}
	%>

</center>

</body>
</html>

<%}else{response.sendRedirect("index.jsp");}%>