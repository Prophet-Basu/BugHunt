<%@page import="com.srv.QuestionCountSrv"%>
<%@page import="com.srv.TeamSrv"%>
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
<script src="../js/jquery-2.1.4.min.js"></script>
</head>
<body>
<center>

<br/><br/>

<script type="text/javascript">
	function getQuestionCount(lang) {
		
		$.ajax({
		    type: "GET",
		    url: "QuestionCountSrv",
		    data: "<%=QuestionCountSrv.Parameters.SUBJ%>="+lang,
		    success: function(data) {
				
		       	$('#insertionpoint').html("<h3>Existing Number of Questions: "+data+"</h3>");
	        }									
		        
	    });
	}

	</script>
	
	<h1>Enter Team Details</h1><br/><br/>
	
	<form action="TeamSrv" method="post">
		Number of Teams: 
		<input type="number" name="<%=TeamSrv.Parameters.TEAM %>" required="required"><br/><br/>
		Number of Answer Tables: 
		<input type="number" name="<%=TeamSrv.Parameters.ANS_TABLE %>" required="required"><br/><br/>
		Number of Sets: 
		<input type="number" name="<%=TeamSrv.Parameters.SET %>" required="required"><br/><br/>
		Number of Ques in each Set: 
		<input type="number" name="<%=TeamSrv.Parameters.QUES_COUNT %>" required="required"><br/><br/>
		
		Duration of Test in Minutes: 
		<input type="number" name="<%=TeamSrv.Parameters.DURATION %>" required="required"><br/><br/>
		<input type="submit" value="Create">
		
	</form>
	<br/><br/><br/><br/>
	
	<button onclick="getQuestionCount(1)">C Ques</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="getQuestionCount(2)">Cpp Ques</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="getQuestionCount(3)">Java Ques</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div id="insertionpoint"></div><br/><br/>


</center>
</body>
</html>
<%}else{response.sendRedirect("index.jsp");}%>