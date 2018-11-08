<%@page import="com.dao.TeamDaoImpl"%>
<%@page import="com.dao.TeamDao"%>
<%@page import="java.sql.Time"%>
<%@page import="com.bean.TeamBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="css/style4.css">
<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		Object tbean = session.getAttribute("tbean");
		if (tbean != null) {
			TeamBean tb = (TeamBean)tbean;
%>
<script type="text/javascript">


function setCookie(cname,cvalue,exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cname+"="+cvalue+"; "+expires;
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1);
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

	
	
function startTimer(duration, display) {
	var timer = duration, minutes, seconds;
	setInterval(function () {
		if(timer>0){
		minutes = parseInt(timer / 60, 10);
		seconds = parseInt(timer % 60, 10);

		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		display.textContent ="" + minutes + ":" + seconds;
		setCookie("remtime", timer, 1);
		timer--;
		}
		else
		{
			display.textContent = "Time Over!!!";
			alert("Your Time is Up!!!");
			document.forma.submit();	
		}

	}, 1000);
}


	<%Object ot = session.getAttribute("duration");
	long dur=300;
	if(ot!=null){
		dur = (int)ot;
	}%>
	
	
	window.onload = function () {
		
		//check if previous cookie present
			var rt = getCookie("remtime");
			
			if(rt!=""){
				var duration = rt; 
			}
			else{
				var duration = <%=dur%>;
				setCookie("remtime",duration,1);
			}
			
				display = document.querySelector('#time');
			startTimer(duration, display);
		};
	
	
	
</script>
<style type="text/css">
html{
height: 87px;
}
</style>
</head>
<body>

<div class="container-fluid" id="top-bar">
        <div class="col-sm-8">
	         <p id="heading"><span class="glyphicon glyphicon-book" aria-hidden="true"></span><%=TeamBean.COMP_NAME %></p>          
	    </div>

 	
	    <div class="col-sm-4">
	        <p style="font-weight:bold"><span class="glyphicon glyphicon-alert" aria-hidden="true"></span>Timer : 
	        <span id="time"></span>
            </p>            
	    </div>
    </div>

	<form action="summary.jsp" target="_top" name="forma">


	</form>
	
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>


</body>
<%}else{response.sendRedirect("LogoutSrv");} %>	
</html>