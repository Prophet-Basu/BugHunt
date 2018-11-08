package setgen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utility.DBUtil;

public class CreateTable {
	
	
	private static final String DBNAME = "bughunt";
	public static String TBNAME2; 
	static Connection conn=null;
	
	
	
	public static boolean createTable(int n,int q,String TBNAME){
		boolean status = false;
		boolean flag = false;
		TBNAME2 = TBNAME;
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
				
				//-------------------------------------------------------------
				StringBuffer sb = new StringBuffer();
				
				sb.append("create table "+TBNAME+" ( setno int primary key,");
				for(int i=0;i<q;i++)
				{
					sb.append("q"+(i+1)+" int not null,");
				}
				sb.deleteCharAt(sb.lastIndexOf(","));
				sb.append(")");
				//-------------------------------------------------------------
				
				
				String query3 = sb.toString();
				System.out.println(query3);
				if(flag){
						st.executeUpdate(query2);
						System.out.println("Dropped existing table");
				}
				
				st.executeUpdate(query3);
				System.out.println("Created new "+TBNAME+" table with "+n+" sets each having "+q+" questions");
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
