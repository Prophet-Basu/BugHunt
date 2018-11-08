<%@page import="com.srv.QuestionCountSrv"%>
<%@page import="com.srv.QuesSrv"%>
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

	<h1>Enter Questions</h1><br/><br/>
	
	<script type="text/javascript">
	function getQuestionCount() {
		var subj = $('#subjselector').val();

		$.ajax({
		    type: "GET",
		    url: "QuestionCountSrv",
		    data: "<%=QuestionCountSrv.Parameters.SUBJ%>="+subj,
		    success: function(data) {
				
		       	$('#insertionpoint').html("<h3>Existing Number of Questions: "+data+"</h3>");
	        }									
		        
	    });
	}

	</script>
	
	<form action="QuesSrv" method="post" enctype="multipart/form-data">
		Subject :
		<select name="<%=QuesSrv.Parameters.SUBJ%>" id="subjselector" onchange="getQuestionCount()">
			<option value="0">Select Language</option>
	        <option value="1">GNU C</option>
	        <option value="2">GNU C++</option>
	        <option value="3">JAVA</option>
        </select> <br/><br/>
        Browse Excel File: <input type="file" name="file" size="60" accept=".xls,.xlsx"/><br/><br/>
		<input type="submit" value="Upload">
		
		<br/><br/><br/><br/>
		
		<div id="insertionpoint"></div>
		
	</form>


</center>

</body>
</html>

<%}else{response.sendRedirect("index.jsp");}%>