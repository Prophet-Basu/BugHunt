<%@page import="com.dao.TeamDaoImpl"%>
<%@page import="com.dao.TeamDao"%>
<%@page import="com.bean.TeamBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		Object otbean = session.getAttribute("tbean");
		if (otbean != null) {
			
%>
<frameset rows=15%,85% frameborder="no" >
	<frame name="f1" src="timer.jsp" noresize="noresize" scrolling="no">
	<frame name="f2" src="StartTestSrv" noresize="noresize">
</frameset>
<title>Online Examination Portal V1.0 Alpha - Exam</title>

<%/* }else{response.sendRedirect("LogoutSrv"); }*/
			}else{ response.sendRedirect("LogoutSrv"); } %>
</head>
</html>