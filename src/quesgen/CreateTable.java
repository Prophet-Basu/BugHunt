package quesgen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utility.DBUtil;

public class CreateTable {
	
	
	public static final String DBNAME = "bughunt";
	public static String TBNAME = "cquesans";
	static Connection conn=null;
	
	
	
	public static boolean createTable(int n){
		boolean status = false;
		boolean flag = false;
		if(n==2)
			TBNAME = "cppquesans";
		else if(n==3)
			TBNAME = "javaquesans";
		
		conn=DBUtil.getConnection(DBNAME);
		
		
		if(conn!=null){
			
//----------------------------------------------------------------------------------------------------			
			try {
				Statement st = conn.createStatement();
				String query1 = "show tables like '"+TBNAME+"'";
				ResultSet rs = st.executeQuery(query1);
				if(rs.next()){
					flag = true;
					System.out.println("Existing Table Found");
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
//----------------------------------------------------------------------------------------------------		
			
			try {
				Statement st = conn.createStatement();
				String query2 = "drop table "+TBNAME;
				
				String query3 = "create table "+TBNAME+" ( "
						+ "qno int primary key,"
						+ "quo nvarchar(1200) not null,"
						+ "opt1 nvarchar(200) not null,"
						+ "opt2 nvarchar(200) not null,"
						+ "opt3 nvarchar(200) not null,"
						+ "opt4 nvarchar(200) not null,"
						+ "ans int not null"
						+ ")";
				
				
				System.out.println(query3);
				if(flag){
						st.executeUpdate(query2);
						System.out.println("Dropped existing table");
				}
				
				st.executeUpdate(query3);
				System.out.println("Created new "+TBNAME+" table");
				status = true;
				
				DBUtil.closeConnection();
				
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		
//----------------------------------------------------------------------------------------------------
		}
		return status;
	}

}
