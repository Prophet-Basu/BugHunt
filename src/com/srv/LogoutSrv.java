package com.srv;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.TeamBean;
import com.utility.DBUtil;

/**
 * Servlet implementation class LogoutSrv
 */
@WebServlet("/LogoutSrv")
public class LogoutSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession();

		Date dt = new Date();
		Timestamp ts = new Timestamp(dt.getTime());
		
		Object tbean = ses.getAttribute("tbean");
		if (tbean != null) {
			TeamBean tb = (TeamBean)tbean;
			System.out.println(tb.getName()+" logged out at :"+ts);
		}
		
		ses.invalidate();
		DBUtil.closeConnection();
		response.setContentType("text/html");
		Cookie cookie = new Cookie("remtime", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
