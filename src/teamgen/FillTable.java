package teamgen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utility.DBUtil;

public class FillTable {

	static Connection conn=null;
	
	
	public static boolean fillTable2(int t,int s,int atc,int dur){
		boolean status = false;
		String query1 = "insert into "+CreateTable.TBNAME+" (setid,tableid,lgstatus,remaining) values ( ?,?,?,? )";
		System.out.println(query1);
		
		conn=DBUtil.getConnection(CreateTable.DBNAME);
		if(conn!=null){
		
			try {
				PreparedStatement ps=conn.prepareStatement(query1);
				int n=1;
				int c=1;
				int tc=1;
				while(n<=t){
					
					if(c>s)
						c=1;
					ps.setInt(1, c++);
					if(tc>atc)
						tc=1;
					ps.setInt(2, tc++);
					ps.setBoolean(3, false);
					ps.setInt(4, dur*60);
					//ps2.setString(1, "team"+(n++));
					
					if(ps.executeUpdate()>0)
						System.out.println("Team#"+(n++)+" inserted in "+CreateTable.TBNAME+" !!!");
					/*if(ps2.executeUpdate()>0)
						System.out.println("Team#"+(n-1)+" inserted in "+CreateTable.LOGTBNAME+" !!!");*/
				}
				status=true;
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		}		
		
		return status;
	
	}
	
}
