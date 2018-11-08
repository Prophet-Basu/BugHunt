<%@page import="java.sql.Time"%>
<%@page import="com.bean.TeamBean"%>
<%@page import="com.bean.QuesStructBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.bean.QuoAnsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="css/style4.css">
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
			Date dt = new Date();
			/* 
			Time pt = new Time(dt.getTime());
			Time at = (Time)session.getAttribute("duration");
			System.out.println(pt.getTime());
			System.out.println(at.getTime());
			 */
			
			Timestamp ts = (Timestamp)session.getAttribute("logintime");
			//System.out.println(ts.getTime()/1000);
			 
			 Timestamp ts2 = new Timestamp(dt.getTime());
			 //System.out.println(ts2.getTime()/1000);
			 
			 long diff= ((ts2.getTime()-ts.getTime())/1000);
			 long timerdur = (long)(int)session.getAttribute("duration");
			 
			//Clock.......
			//System.out.println("timer : "+timerdur);
			//System.out.println("diff in bugtest.jsp: "+diff);
			 if(diff<=timerdur){
				Object oquesbean = session.getAttribute("qbean");
				if(oquesbean!=null){
					QuoAnsBean quesbean = (QuoAnsBean)oquesbean;
					int slno =(int)session.getAttribute("slno");
					int ga = (int)session.getAttribute("givenans");
					int lastQues=(int)session.getAttribute("end");
%>

	<div class="container-fluid" id="main-panel">
	<form action="RecordAnswerSrv" method="post">
        <div class="row">
            <div class="col-sm-9">
                <div class="jumbotron" style="rgba(213, 213, 213, 0.15);">
                    <p>
                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
						<!-- <input type="text" class="text" value="ques no" style="width:40px;"> -->
							Q<%=slno%>)
							<input type="hidden" readonly="readonly" name="qno" value="<%=quesbean.getQnumber()%>">
                        <!-- <input type="text" class="text" value="A big question will come over here like this" id="ques"> -->
                       <pre><%=quesbean.getQuestion()%></pre>
                    </p>
                    <p style="margin-top:60px;font-size:20px;font-weight:bold">Options :</p>
                    
                    <p><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
                    <input type="radio" name="ans" value="1"  <% if(ga==1)%>checked="checked"<% ;%>><%=quesbean.getOpt1() %>
                    </p>
                    
                    <p><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
                    <input type="radio" name="ans" value="2"  <% if(ga==2)%>checked="checked"<% ;%>><%=quesbean.getOpt2() %>
                    </p>
                    
                    <p><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
                    <input type="radio" name="ans" value="3"  <% if(ga==3)%>checked="checked"<% ;%>><%=quesbean.getOpt3() %>
                    </p>
                    
                    <p><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span>
                    <input type="radio" name="ans" value="4"  <% if(ga==4)%>checked="checked"<% ;%>><%=quesbean.getOpt4() %>
                    </p>
                    

                    <p style="margin-top:40px; margin-bottom:0px">
                        <input class="btn btn-danger nv " type="submit" value="Previous" name="previous" <%if(slno==1) %>disabled="true"<%; %>>
                        
                        <input class="btn btn-success nv" type="submit" value="Next" name="next" <%if(slno==lastQues) %>disabled="true"<%; %>>
                        
                        <input class="btn btn-info nv" type="submit" value="Submit" name="submit">
                    </p>
                </div>
            </div>
             
<div class="col-sm-3">
       <div class="jumbotron" style="rgba(128, 93, 150, 0.48);">
           <p style="margin-top:10px;"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>Questions: </p>
           <div class="container-fluid " id="button-panel">
               
<% 
int c=0;
ArrayList<QuesStructBean> qsblist = null;
Object qsblistobj = session.getAttribute("qsblist");
if(qsblistobj!=null){

	 qsblist = (ArrayList<QuesStructBean>)qsblistobj;
}
for(QuesStructBean x: qsblist){
              
    c++;
    if(x.getGivenans()==0 && (x.getQno()!=quesbean.getQnumber())){
    %>              
    <input type="submit" class="btn btn-danger btn-lg quesbtn" name="jumpToQues" value="<%=x.getSlno() %>"> 
    <%}else if(x.getQno()==quesbean.getQnumber()){ %>
    <input type="submit" class="btn btn-primary btn-lg quesbtn" name="jumpToQues" value="<%=x.getSlno() %>">
    <%}else{ %>
    <input type="submit" class="btn btn-success btn-lg quesbtn" name="jumpToQues" value="<%=x.getSlno() %>">
    <%} %>
    <%
    if(c==4){ c=0;%>
    <br>
<%} %>                   
        
<%} %> 
               
         </div>
     </div>
 </div>
            
	</div>
	</form>
	</div>

	<footer class="footer">
        <p>Attempting Exam</p>
    </footer>

 	

<%				  
			}else{ 

	
	%>
<h2>quesbean is null</h2>
<%
				}
			}else{
				
%>
<!----------------------------When Timed Out------------------------->
<div style="text-align: center; margin-top: 70px;">
<img alt="warning" src="img/wrntri.jpeg"width="200px">
<br><br><br>
<h2>Your Time is Up !!</h2>
<br><br><br>
<a href="LogoutSrv" target="_top" class="btn btn-danger btn-lg">Logout</a>
</div>
<!--------------------------------------------------------------------------------------->
<% 				
				 
			}
}else{ response.sendRedirect("LogoutSrv"); }%>

	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>