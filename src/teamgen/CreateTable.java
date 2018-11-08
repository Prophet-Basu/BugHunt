package teamgen;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.utility.DBUtil;

public class CreateTable {
	
	
	public static final String DBNAME = "bughunt";
	public static final String TBNAME = "team";
	public static final String LOGTBNAME = "login";
	static Connection conn=null;
	
	
	
	
	public static boolean createTable2(){
		boolean status = false;
		
		conn=DBUtil.getConnection(DBNAME);
		
		
		if(conn!=null){
			
			try {
				Statement st = conn.createStatement();
				String query = "create table "+TBNAME+" (teamid int primary key auto_increment,"
						+ "name nvarchar(50),"
						+ "pass nvarchar(50) ,"
						+ "setid int not null, "
						+ "lang int ,"
						+ "tableid int not null,"
						+ "logintime timestamp,"
						+ "lgstatus boolean,"
						+ "remaining int, "
						+ "mem1 nvarchar(150),"
						+ "mem2 nvarchar(150),"
						+ "inst nvarchar(100),"
						+ "phn nvarchar(20),"
						+ "email nvarchar(100))";
				
				System.out.println(query);
				st.executeUpdate(query);
				System.out.println("Created new "+TBNAME);
				status=true;
				
				DBUtil.closeConnection();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		return status;
	}
}
