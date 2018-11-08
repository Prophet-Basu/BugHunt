<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="full">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Online Examination Portal V1.0 Alpha</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="css/style3.css">

</head>
<body>
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
        <form action="RegisterSrv" method="post">
        <div class="container-fluid" id="table-container">
        <div class="jumbotron" id="instuct-table">
            <div class="row">
                <div class="col-sm-12">
                    <p id="heading">Team Registration</p>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-5">
                	<p class="border">Name of Member1 :</p>
                	<p class="border">Name of Member2 :</p>
                	<p class="border">Institute :</p>
                    <p class="border">Enter Team Name :</p>
                    <p class="border">Enter Phone Number :</p>
                    <p class="border">Enter Email :</p>
                    <p class="border">Password :</p>
                    <p class="border">Confirm Password :</p>
                    <p class="border">Language :</p>
                </div>
                <div class="col-sm-7">
                    <p><input type="text" name="mem1" placeholder="Member1 (Max 120 character)" required="required" maxlength="120"></p>
                    <p><input type="text" name="mem2" placeholder="Member2 (Max 120 character)" required="required" maxlength="120"></p>
                    <p><input type="text" name="inst" placeholder="Institute/College (Max 90 character)" required="required" maxlength="90"></p>
                    <p><input type="text" name="tname" placeholder="Team Name (Max 30 character)" required="required" maxlength="30"></p>
                    <p><input type="number" name="phn" placeholder="Phone Number (Max 10 character)" required="required" min="7000000000" max="9999999999"></p>
                    <p><input type="email" name="email" placeholder="Email (Max 90 character)" required="required" maxlength="90"></p>
                    <p><input type="password" name="tpass" placeholder="Password (Max 20 character)" required="required" id="password" maxlength="20"></p>
                    <p><input type="Password" name="tpass2" placeholder="Confirm Password " required="required" maxlength="20"
                    id="password_confirm" onchange="check(this)"></p>
                    
                    <script language='javascript' type='text/javascript'>
					    function check(input) {
					        if (input.value != document.getElementById('password').value) {
					            input.setCustomValidity('Password Must be Matching.');
					        } else {
					            // input is valid -- reset the error message
					            input.setCustomValidity('');
					        }
					    }
					</script>
                    
                    <p><select name="lang">
                    <option value="1">GNU C</option>
                    <option value="2">GNU C++</option>
                    <option value="3">JAVA</option>
                    </select></p>
                    
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                </div>
                <div class="col-sm-3">
                    <a href="index.jsp" class="btn btn-danger">Cancel</a>
                </div>
                <div class="col-sm-3">
                    <input type="submit" value="Register" class="btn btn-success">
                </div>
                <div class="col-sm-3">
                </div>
            </div>
           
                
        </div>
    </div>
        </form>
        
        
        <%} %>
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