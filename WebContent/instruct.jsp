<%@page import="com.bean.TeamBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Examination Portal V1.0 Alpha - Instructions</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="css/style2.css">
</head>
<body>
<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		Object tbean = session.getAttribute("tbean");
		if (tbean != null) {
			 TeamBean tb = (TeamBean) tbean;
			 Object o=session.getAttribute("startedtest");
			 if(o!=null){
				 response.sendRedirect("framer.jsp");
			 }
	%>

	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
               
                <a class="navbar-brand" href="#">Online Examination Portal V1.0 Alpha</a>
            </div>
       </div>
    </nav>

<div class="container-fluid" id="table-container">
        <div class="jumbotron" id="instuct-table">
            <div class="row">
                <div class="col-sm-12">
                    <p id="heading">INSTRUCTIONS</p>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                 	<p>TeamID :</p>
                    <p>TeamName :</p>
                    <p>TableID : </p>
                    <p>SetID : </p>
                    <p>Language : </p>
                    <p>Member1 : </p>
                    <p>Member2 : </p>
                    <p>Institute : </p>
                    <p>LoginTime : </p>
                    <p>Remaining Time : </p>
                    <p>IP : </p>
                </div>
                <div class="col-sm-6">
                	<p><%=tb.getTeamid() %></p>
                    <p><%=tb.getName().toUpperCase() %></p>
                    <p><%=tb.getTableid() %></p>
                    <p><%=tb.getSetid() %></p>
                    <p>
                    <%if(tb.getLang()==1){ %>
                    GNU C
                    <%}else if(tb.getLang()==2){ %>
                    GNU C++
                    <%}else{ %>
                    JAVA
                    <%} %>
                    </p>
                    <p><%=tb.getMem1() %></p>
                    <p><%=tb.getMem2() %></p>
                    <p><%=tb.getInst() %></p>
                    <p><%=tb.getLogintime() %></p>
                    <p><%=tb.getRemtime()/60 %> mins</p>
                    <p><%=request.getRemoteAddr() %></p>
                </div>
            </div>
            <div class="container-fluid box">
                <div class="row">
                    <p id="small-heading">General Instructions to Students :-</p>
                
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                    <p><span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>this is an instruction</p>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                    </div>
                    <div class="col-sm-3">
                        <a href="LogoutSrv" class="btn btn-danger">Logout</a>
                    </div>
                    <div class="col-sm-3">
                        <a href="framer.jsp" class="btn btn-success">Take a Test</a>
                    </div>
                    <div class="col-sm-3">
                    </div>
                </div>
            </div>
        </div>
    </div>

        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>



<%}else{ response.sendRedirect("LogoutSrv"); }%>
</body>
</html>