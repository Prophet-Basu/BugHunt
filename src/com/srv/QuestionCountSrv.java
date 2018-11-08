package com.srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.QuoAnsDao;
import com.dao.QuoAnsDaoImpl;

/**
 * Servlet implementation class QuestionCountSrv
 */
@WebServlet("/settings/QuestionCountSrv")
public class QuestionCountSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static class Parameters{
		public static final String SUBJ = "subj";
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int language = Integer.parseInt(request.getParameter(Parameters.SUBJ));
		
		QuoAnsDao qadao;
		if(language == 1)
			qadao = new QuoAnsDaoImpl("bughunt", "cquesans");
		else if(language == 2)
			qadao = new QuoAnsDaoImpl("bughunt", "cppquesans");
		else
			qadao = new QuoAnsDaoImpl("bughunt", "javaquesans");
		
		System.out.println("Request here for : "+language);
		response.getWriter().write(qadao.getTotalQues()+"");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
