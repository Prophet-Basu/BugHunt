package com.srv;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.TeamBean;
import com.dao.TeamDao;
import com.dao.TeamDaoImpl;

/**
 * Servlet implementation class LoginSrv
 */
@WebServlet("/LoginSrv")
public class LoginSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user=Integer.parseInt(request.getParameter("user"));
		String pass=request.getParameter("pass");
		boolean flag=false;
		HttpSession ses=request.getSession();
		
		TeamDao tdao = new TeamDaoImpl("bughunt", "team");
		
		TeamBean tb = tdao.returnTeam(user, pass);
		if(tb!=null)
		{
			ses.setAttribute("tbean", tb);
			ses.setAttribute("duration", tb.getRemtime());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			tdao.loginTeam(tb.getTeamid());
			tb.setLogintime(timestamp);
			System.out.println(tb.getName()+" logged in at: "+timestamp+" from ip: "+request.getRemoteAddr());
			response.sendRedirect("instruct.jsp");
		}
		else
		{
			ses.setAttribute("flag", flag);
			response.sendRedirect("index.jsp");
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
