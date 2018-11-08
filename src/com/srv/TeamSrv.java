package com.srv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anstablegen.GenerateAnsTables;
import setgen.GenerateSet;
import teamgen.GenerateTeam;

/**
 * Servlet implementation class TeamSrv
 */
@WebServlet("/settings/TeamSrv")
public class TeamSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static class Parameters{
		public static final String TEAM = "teams";
		public static final String SET = "sets";
		public static final String QUES_COUNT = "qcount";
		public static final String ANS_TABLE = "anstable";
		public static final String DURATION = "duration";
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int team  = Integer.parseInt(request.getParameter(Parameters.TEAM));
		int set  = Integer.parseInt(request.getParameter(Parameters.SET));
		int qno  = Integer.parseInt(request.getParameter(Parameters.QUES_COUNT));
		int anstable  = Integer.parseInt(request.getParameter(Parameters.ANS_TABLE));
		int duration  = Integer.parseInt(request.getParameter(Parameters.DURATION));
		
		GenerateTeam.Generate(team, set, anstable, duration);
		GenerateSet.Generate(1, qno, set);
		GenerateSet.Generate(2, qno, set);
		GenerateSet.Generate(3, qno, set);
		GenerateAnsTables.Generate(anstable);
		
		response.sendRedirect("admin.jsp");
	}

}
