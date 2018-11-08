<%@page import="com.bean.TeamBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.dao.QuoAnsDao"%>
<%@page import="com.dao.QuoAnsDaoImpl"%>
<%@page import="com.bean.QuoAnsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.QuesStructBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Examination Portal V1.0 Alpha - Summary</title>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="css/style5.css" />
</head>
<body>
<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		Object tbean = session.getAttribute("tbean");
		if (tbean != null) {
			TeamBean tb = (TeamBean)tbean;
			int n = (int)session.getAttribute("slno");
%>


<div class="container-fluid" id="top-bar">
        <div class="col-sm-12">
	         <p id="heading" style="text-align: center;">Summary</p>          
	    </div>       
</div>

<div class="container-fluid" id="main-container" >
<% 
	ArrayList<QuesStructBean> qsblist=(ArrayList<QuesStructBean>)session.getAttribute("qsblist");
	QuoAnsDao qadao;
	if(tb.getLang()==1)
		qadao = new QuoAnsDaoImpl("bughunt", "cquesans");
	else
		qadao = new QuoAnsDaoImpl("bughunt", "javaquesans");
	
	QuoAnsBean qabean;
	int slno,qno,givenans;
	String ques,opt1,opt2,opt3,opt4;
	String ans="";
	for(QuesStructBean qsbean:qsblist){
		slno=qsbean.getSlno();
		qno=qsbean.getQno();
		qabean=qadao.fetchQandA(qno);
		ques=qabean.getQuestion();
		opt1=qabean.getOpt1();
		opt2=qabean.getOpt2();
		opt3=qabean.getOpt3();
		opt4=qabean.getOpt4();
		givenans=qsbean.getGivenans();
	

%>
	<div class="col-sm-12 ques" >
	<span class="quesspan">Q<%=slno %>. <pre> <%=ques %></pre></span><br><br>
	<span ><%=opt1 %>&nbsp;&nbsp;<%=opt2 %>&nbsp;&nbsp;<%=opt3 %>&nbsp;&nbsp;<%=opt4 %></span>
	<br><br>
	<span class="givenansspan">Your Answer: <%
						switch(givenans){
						
						case 0: ans="       ";
								break;
						
						case 1: ans=opt1;
								break;
								
						case 2: ans=opt2;
								break;
								
						case 3: ans=opt3;
								break;
						
						case 4: ans=opt4;
								break;
						}
						
						%><%=ans %>
	
						
	</span>
	</div>
	
<% 

	if(qsbean.getSlno()==QuoAnsBean.QUESTIONS)
		break;}
%>
</div>







<footer>
<%

Timestamp ts = (Timestamp)session.getAttribute("logintime");

Date dt = new Date();
Timestamp ts2 = new Timestamp(dt.getTime());
long diff= ts2.getTime()-ts.getTime();
//System.out.println("dur before: "+diff);
diff = diff / 1000;
long timerdur = (long)(int)session.getAttribute("duration");
//System.out.println(diff);
if(diff<=timerdur){


%>
<div class="col-sm-6 btncol">
<a href="QuestionGeneratorSrv?slno=<%=n%>" class="btn btn-info">Back</a>
</div>
<div class="col-sm-6 btncol">
<a href="LogoutSrv" target="_top" class="btn btn-danger">Logout</a>
</div>
<%}else{ //System.out.println("time is up in summary "); %>
<div class="col-sm-12 btncol">
<a href="LogoutSrv" target="_top" class="btn btn-danger">Logout</a>
</div>
<%} %>
</footer>
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
<%}else{
	response.sendRedirect("LogoutSrv");
}%>
	
</body>
</html>