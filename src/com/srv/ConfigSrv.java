package com.srv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigSrv
 */
@WebServlet("/settings/ConfigSrv")
public class ConfigSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String PASS;
	private static String USER;
	private static String CONN_STRING;
	
	public static class Parameters{
		public static final String PASS = "pass";
		public static final String USER = "user";
		public static final String CONN_STRING = "conn";
		public static final String FLAG = "changeSuccess";
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object o = request.getParameter(Parameters.CONN_STRING);
		if(o==null){
			try {
				FileInputStream fstream = new FileInputStream("config.ini");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				
				String line;
				line = br.readLine(); 
				
				CONN_STRING=line.substring(line.indexOf('=')+1);
				line = br.readLine(); 
				
				USER=line.substring(line.indexOf('=')+1);
				line = br.readLine(); 
					
				PASS=line.substring(line.indexOf('=')+1);
				br.close();
				
				request.getSession().setAttribute(Parameters.CONN_STRING, CONN_STRING);
				request.getSession().setAttribute(Parameters.PASS, PASS);
				request.getSession().setAttribute(Parameters.USER, USER);
				response.sendRedirect("config.jsp");
				
			} catch (FileNotFoundException e) {
				System.out.println("Please check config.ini file");
				response.sendRedirect("config.jsp");
			}
		}else{
			CONN_STRING = (String)o;
			USER = request.getParameter(Parameters.USER);
			PASS = request.getParameter(Parameters.PASS);
			
			File fout = new File("config.ini");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(fout);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("CONN_STRING="+CONN_STRING);
				bw.newLine();
				bw.write("USER="+USER);
				bw.newLine();
				bw.write("PASS="+PASS);
				bw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			request.getSession().setAttribute(Parameters.FLAG, true);
			response.sendRedirect("admin.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
