package com.srv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/settings/AdminLoginSrv")
public class AdminLoginSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ADMINUSERNAME = "lolwa";
	private static final String ADMINPASSWORD = "pass";
	
	public static class Parameters{
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String TITLE= "BugHunt Prelims Settings";
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession ses=request.getSession();
		
		String username = request.getParameter(Parameters.USERNAME);
		String password = request.getParameter(Parameters.PASSWORD);
		
		if(username.equals(ADMINUSERNAME)&&password.equals(ADMINPASSWORD)){
			ses.setAttribute("adminbean", "adminbean");
			response.sendRedirect("admin.jsp");
		}else{
			ses.setAttribute("wrongpassflag", "flag");
			response.sendRedirect("index.jsp");
		}
		
	}

}
