package com.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static String PASS;
	private static String USER;
	private static String CONN_STRING;
	
	static{
		try {
			FileInputStream fstream = new FileInputStream("config.ini");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			String line;
			line = br.readLine(); 
			//System.out.println("conn String : "+line.substring(line.indexOf('=')+1));
			CONN_STRING=line.substring(line.indexOf('=')+1);
			line = br.readLine(); 
			//System.out.println("user : "+line.substring(line.indexOf('=')+1));
			USER=line.substring(line.indexOf('=')+1);
			line = br.readLine(); 
			//System.out.println("pass : "+line.substring(line.indexOf('=')+1));	
			PASS=line.substring(line.indexOf('=')+1);
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Please check config.ini file");
			
			
			File fout = new File("config.ini");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(fout);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("CONN_STRING=");
				bw.newLine();
				bw.write("USER=");
				bw.newLine();
				bw.write("PASS=");
				bw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		 
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection conn = null;

	private DBUtil() {
	}

	public static Connection getConnection(String db) {
		try {
			FileInputStream fstream = new FileInputStream("config.ini");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			String line;
			line = br.readLine(); 
			//System.out.println("conn String : "+line.substring(line.indexOf('=')+1));
			CONN_STRING=line.substring(line.indexOf('=')+1);
			line = br.readLine(); 
			//System.out.println("user : "+line.substring(line.indexOf('=')+1));
			USER=line.substring(line.indexOf('=')+1);
			line = br.readLine(); 
			//System.out.println("pass : "+line.substring(line.indexOf('=')+1));	
			PASS=line.substring(line.indexOf('=')+1);
			br.close();
			
		} catch (FileNotFoundException e) {} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (conn == null) {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot find Driver class...");
			}

			String cs = CONN_STRING+db;

			try {
				conn = DriverManager.getConnection(cs, USER, PASS);
			} catch (SQLException e) {
				System.out.println("Connection failed" + e.getMessage()+"Check config.ini file");
			}
		}

		return conn;
	}
	
	public static void closeConnection()
	{
		if(conn!=null)
		{
			try {
				conn.close();
				conn=null;
			} 
			catch (SQLException e) {
				System.out.println("Unable to close connection...");
			}
		}
	}

}
