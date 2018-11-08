package com.srv;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.QuesStructBean;
import com.bean.QuoAnsBean;
import com.bean.TeamBean;
import com.dao.AnswerDao;
import com.dao.AnswerDaoImpl;

/**
 * Servlet implementation class RecordAnswerSrv
 */
@WebServlet("/RecordAnswerSrv")
public class RecordAnswerSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession ses = request.getSession();
		 TeamBean team = (TeamBean)ses.getAttribute("tbean");
		 Timestamp ts = (Timestamp)ses.getAttribute("logintime");
		 
		 Date dt = new Date();
		 Timestamp ts2 = new Timestamp(dt.getTime());
		 long diff= ts2.getTime()-ts.getTime();
		 //System.out.println("dur before: "+diff);
		 diff = diff / 1000;
		 long timerdur = (long)(int)ses.getAttribute("duration");
		 //System.out.println(diff);
		 if(diff<=timerdur){
		 
		 
			 AnswerDao adao = new AnswerDaoImpl("bughunt", "answer"+team.getTableid());
			 int n = (int)ses.getAttribute("slno");
			 if(n>0 && n<=QuoAnsBean.QUESTIONS){
				 ArrayList<QuesStructBean> qsblist=null;
				 Object qsblistobj = ses.getAttribute("qsblist");
					if(qsblistobj!=null){
					
						 qsblist = (ArrayList<QuesStructBean>)qsblistobj;
					}
					
					
				 int qno=Integer.parseInt((String)request.getParameter("qno"));
				// System.out.println(qno);
				 int givenAns=0;
				 Object oans=request.getParameter("ans");
				 if(oans!=null){
					 givenAns = Integer.parseInt((String)oans);
					 QuesStructBean qsb = qsblist.get(n-1);
					 //System.out.println("givenans: "+givenAns+"\t getgiven ans: "+qsb.getGivenans());
					 if(givenAns!=qsb.getGivenans()){
	
	
						 if(qsb.getGivenans()==0)
							 	adao.recordAnswer(team.getTeamid(),team.getName(), qno, givenAns, team.getLang(),ts2,diff);
							 else
								 adao.updateAnswer(team.getTeamid(),team.getName(), qno,  givenAns, team.getLang(),ts2,diff);
						 
						 /*changed here
						  * qsb.setGivenans(givenAns);
						  * qsblist.remove(n-1);
						 	qsblist.add(n-1, qsb);
						  */
						 qsblist.get(n-1).setGivenans(givenAns);
						 
						 
						 /*for(QuesStructBean x:qsblist)
							 System.out.println(x);*/
						 ses.setAttribute("qsblist", qsblist);
					 }
				 }
				 
				 if(request.getParameter("next")!=null && n<=QuoAnsBean.QUESTIONS){
					 ses.setAttribute("slno", n+1);
					 response.sendRedirect("QuestionGeneratorSrv");
				 }
				 else if(request.getParameter("previous")!=null && n>0){
					 ses.setAttribute("slno", n-1);
					 response.sendRedirect("QuestionGeneratorSrv");
				 }
				 else if(request.getParameter("submit")!=null){
					 ses.setAttribute("time", true);
					 //System.out.println("Time set to true in recordansSrv");
					 response.sendRedirect("summary.jsp");
				 }
				 else if( request.getParameter("jumpToQues")!=null){
					 int jslno = Integer.parseInt(request.getParameter("jumpToQues"));
					 //System.out.println(jslno);
					 ses.setAttribute("slno", jslno);
					 response.sendRedirect("QuestionGeneratorSrv");
				 }
				
				 
				 
			 }
			 else{
				 ses.setAttribute("slno", n);
				 response.sendRedirect("QuestionGeneratorSrv?");
			 }
		 }else{
			 System.out.println("Times up for "+team.getName()+" !!");
			 ses.setAttribute("time", false);
			 //System.out.println("Time set to false in recordansSrv");
			 response.sendRedirect("summary.jsp");
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
