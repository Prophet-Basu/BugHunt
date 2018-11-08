package com.srv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TeamDao;
import com.dao.TeamDaoImpl;

/**
 * Servlet implementation class RegisterSrv
 */
@WebServlet("/RegisterSrv")
public class RegisterSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mem1 = request.getParameter("mem1");
		String mem2 = request.getParameter("mem2");
		String inst = request.getParameter("inst");
		int lang  = Integer.parseInt(request.getParameter("lang"));
		String name = request.getParameter("tname");
		String pass = request.getParameter("tpass");
		String phn = request.getParameter("phn");
		String email = request.getParameter("email");
		
		HttpSession ses=request.getSession();
		
		int l=0;
		l=145<mem1.length()?145:mem1.length();
		mem1 = mem1.substring(0,l);
		
		l=145<mem2.length()?145:mem2.length();
		mem2 = mem2.substring(0, l);
		
		l=95<inst.length()?95:inst.length();
		inst = inst.substring(0, l);
		
		l=45<name.length()?45:name.length();
		name = name.substring(0, l);
		
		l=45<pass.length()?45:pass.length();
		pass = pass.substring(0, l);
		
		l=15<phn.length()?15:phn.length();
		phn = phn.substring(0,l);
		
		l=95<email.length()?95:email.length();
		email = email.substring(0, l);
		
		TeamDao tdao = new TeamDaoImpl("bughunt", "team");
		int id = tdao.registerTeam(mem1, mem2, inst, lang, name, pass,phn,email);
		if(id>0){
			ses.setAttribute("regflag",id);
			
		}else{
			ses.setAttribute("regflagfail",true);
		}
		
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
