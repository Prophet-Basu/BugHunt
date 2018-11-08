<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="full">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Online Examination Portal V1.0 Alpha</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript">
	function breakout()
	{
	if (window.top != window.self) 
	 {
	 window.top.location="index.jsp"
	 }
	}
	</script>
</head>
<body onLoad="javascript:breakout()">
<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		Object tbean = session.getAttribute("tbean");
		if (tbean != null) {
			response.sendRedirect("instruct.jsp");
		}else{
%>
	
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a class="navbar-brand" href="#">Online Examination Portal V1.0 Alpha</a>
                </div>
                <div class="collapse navbar-collapse navbar-right" id="menu">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="javascript:void(0)" id="about" >About</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
        </nav>
		<form action="LoginSrv" method="post">
			<div class="jumbotron push-to-bottom" id="login">
	            <div class="row">
	                <div class="col-sm-12" style="text-align: center;">
	                	<p class="heading" id="head">Team Login</p>
	                	<b>Team ID: </b>
	                	&nbsp;&nbsp;&nbsp;&nbsp;
	                	<input type="number" name="user" placeholder="Enter TeamID" required="required">
	                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                	<b>Password:</b>
	                	&nbsp;&nbsp;&nbsp;&nbsp;
	                	<input type="password" name="pass" placeholder="Enter Password" required="required">
	                </div>
	            </div>
	            
	             <div class="row" id="btnrow">
	                <div class="col-sm-12" style="text-align: center;">
	                <input class="btn btn-success" type="submit" value="Login">
	                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    <a href="register.jsp" class="btn btn-info" >Register</a>
	            	</div>
	            </div>
	            
	           <center>
	            <div class="row" style="margin-top:30px;">
	                <div class="col-sm-12 " style="">
	
	                
	                  <%if(session.getAttribute("flag")!=null){ %>
						<span style="color: #92052B;font-size: 18px;
    						font-weight: bold;">Invalid Credentials</span>

							<%session.removeAttribute("flag");}
	                  else if (session.getAttribute("regflag")!=null){
	                  int tid = (Integer)session.getAttribute("regflag");
	                  %>
	                	  
	                	  <span style="color:#053500;;font-size: 18px;
    						font-weight: bold;">Team registered with ID = <%=tid %></span>
	                	  
	                  <% session.removeAttribute("regflag");}
	                  else if(session.getAttribute("regflagfail")!=null){%>
	                	
	                	<span style="color: #92052B;font-size: 18px;
    						font-weight: bold;">Error in Registration.Contact Staff.</span>
	                	
	                	  
	                <%   }
		} %>	
	                </div>
	            </div>
	            </center>
	        </div>
        </form>

	
		<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
		
		var b = document.getElementById('about');
        b.onclick = function () {

            // Your code here...
			alert ("This is experimental exam portal!");

            return true;
        }
        
    
	</script>
	

	

</body>
</html>