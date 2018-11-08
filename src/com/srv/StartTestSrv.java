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
import com.dao.QuoAnsDao;
import com.dao.QuoAnsDaoImpl;
import com.dao.TeamDao;
import com.dao.TeamDaoImpl;

/**
 * Servlet implementation class StartTestSrv
 */
@WebServlet("/StartTestSrv")
public class StartTestSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession ses = request.getSession();
		
		TeamBean tb = (TeamBean) ses.getAttribute("tbean");
		TeamDao tdao = new TeamDaoImpl("bughunt", "team");
		
		TeamBean newtb = tdao.returnTeam(tb.getTeamid(), tb.getPass());
		if(newtb==null){
			
			//ses.setAttribute("duration",new Time(0));
			//System.out.println("in logout part of starttest");
			response.sendRedirect("LogoutSrv");
		}else{
		tdao.lockTeam(tb.getTeamid());
		QuoAnsDao qadao;
		if(tb.getLang()==1)
			qadao = new QuoAnsDaoImpl("bughunt", "cquesans");
		else if(tb.getLang()==2)
			qadao = new QuoAnsDaoImpl("bughunt", "cppquesans");
		else
			qadao = new QuoAnsDaoImpl("bughunt", "javaquesans");
		
		ArrayList<QuoAnsBean> qlist = qadao.fetchAll();
		
		ArrayList<Integer> setlist;
		if(tb.getLang()==1)
			setlist = tdao.getSet(tb.getTeamid(),"csets");
		else if(tb.getLang()==2)
			setlist = tdao.getSet(tb.getTeamid(), "cppsets");
		else
			setlist = tdao.getSet(tb.getTeamid(),"javasets");
		
		//System.out.println("setlist size: "+setlist.size());
		if(setlist!=null){
			ArrayList<QuesStructBean> qsblist = new ArrayList<>();
			int i=1;
			for(int x:setlist)
				qsblist.add(new QuesStructBean(i++, x,0));
			
			/*for(QuesStructBean x:qsblist)	
			{
				System.out.println("slno:"+x.getSlno()+"  quesno: "+x.getQno());
			}*/
			
			Date dt = new Date();
			Timestamp ts = new Timestamp(dt.getTime());
			
			ses.setAttribute("queslist", qlist);
			ses.setAttribute("qsblist", qsblist);
			ses.setAttribute("slno", 1);
			ses.setAttribute("logintime",ts);
			ses.setAttribute("startedtest", true);
			System.out.println(tb.getName()+" started test at: "+tb.getLogintime());
		response.sendRedirect("QuestionGeneratorSrv");
		}
		}
		
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
