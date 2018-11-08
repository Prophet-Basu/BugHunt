package com.srv;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.QuesStructBean;
import com.bean.QuoAnsBean;

/**
 * Servlet implementation class QuestionGeneratorSrv
 */
@WebServlet("/QuestionGeneratorSrv")
public class QuestionGeneratorSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//----------------------------------------------------------------------------------------------------		  
		HttpSession ses = request.getSession(); 
		ArrayList<QuoAnsBean> qlist = null;
		ArrayList<QuesStructBean> qsblist = null;
		
		boolean end = false;
		Object qlistobj = ses.getAttribute("queslist");
		if(qlistobj!=null){
		
			 qlist = (ArrayList<QuoAnsBean>)qlistobj;
		}
		Object qsblistobj = ses.getAttribute("qsblist");
		if(qsblistobj!=null){
		
			 qsblist = (ArrayList<QuesStructBean>)qsblistobj;
		}
		
		int lastQues = QuoAnsBean.QUESTIONS;
		ses.setAttribute("end", lastQues);
//----------------------------------------------------------------------------------------------------
		  
		  int slno=1;
		  Object oslno = ses.getAttribute("slno");
		  if(oslno !=null){
			  slno = (Integer)oslno;
		  ses.setAttribute("slno", slno);
			  
		  if(slno<1)
			  slno=1;
		  else if(slno>lastQues){
			  slno=lastQues;
			  end=true;
		  }
		  ses.setAttribute("slno", slno);
		  //System.out.println("genQues slno: "+slno);
		  }else{
			  System.out.println("slno is null");
		  }
		  //System.out.println(slno);
		  int qno = qsblist.get(slno-1).getQno();
		  //System.out.println("genQues quesno: "+qno);
		  QuoAnsBean qabean= qlist.get(qno-1);
		  
		  ses.setAttribute("qbean", qabean);
		  ses.setAttribute("givenans",qsblist.get(slno-1).getGivenans());
		  if(!end){
			  response.sendRedirect("bugtest.jsp");
		  }
		  else{
			  //ses.setAttribute("time", true);
			  response.sendRedirect("summary.jsp");
			  //System.out.println("Redirected to summary.jsp from quesGenSrv");
		  }
		 
		  //System.out.println("in quesgen");
		  //response.sendRedirect("bugtest.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
