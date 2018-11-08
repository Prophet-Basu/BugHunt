package anstablegen;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.utility.DBUtil;

public class CreateTable {
	
	
	public static final String DBNAME = "bughunt";
	public static final String TBNAME = "answer";
	static Connection conn=null;
	
	
	
	public static boolean createTable(int atc){
		boolean status = false;
		
		conn=DBUtil.getConnection(DBNAME);
		
		
		if(conn!=null){
			
			try {
				Statement st = conn.createStatement();
				
				int tc=1;
				while(tc<=atc){
				String query = "create table "+TBNAME+(tc++)+" ("
						+ "teamid int not null,"
						+ "t_name nvarchar(20) not null, "
						+ "lang int not null,"
						+ "qno int not null,"
						+ "givenans int not null,"
						+ "rectime timestamp not null)";
				
				System.out.println(query);
				
				
				st.executeUpdate(query);
				System.out.println("Created new "+TBNAME+(tc-1)+" table");
				
				}
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
