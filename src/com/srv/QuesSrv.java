package com.srv;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import quesgen.GenerateQues;

/**
 * Servlet implementation class QuesSrv
 */
@WebServlet("/settings/QuesSrv")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
	maxFileSize=1024*1024*10,      // 10MB
	maxRequestSize=1024*1024*50)   // 50MB
public class QuesSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String savePath = "D:\\curbrain\\";

	public static class Parameters{
		public static final String SUBJ = "subj";
		public static final String FILE = "file";
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lang = 0;
		
		File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
		
		for (Part part : request.getParts()) {
        	if(part.getName().equals(Parameters.SUBJ)){	
        		Scanner s = new Scanner(part.getInputStream());
        		lang = s.nextInt();
        		s.close();
        	}else{
	            String fileName = extractFileName(part);
	            String fileName2 = savePath + File.separator + lang+fileName;
	           
		            part.write(fileName2);
		            GenerateQues.Generate(fileName2, lang);
	        }
        	
        }
		response.sendRedirect("admin.jsp");
	}

}
