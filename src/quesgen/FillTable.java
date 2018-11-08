package quesgen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.QuoAnsBean;
import com.utility.DBUtil;

public class FillTable {

	static Connection conn=null;
	
	public static boolean fillTable(ArrayList<QuoAnsBean> alist){
		boolean status = false;
		
		String query3 = "insert into "+CreateTable.TBNAME+" values ( ?,?,?,?,?,?,? )";
		System.out.println(query3);
		
		conn=DBUtil.getConnection(CreateTable.DBNAME);
		if(conn!=null){
		
			try {
				PreparedStatement ps=conn.prepareStatement(query3);
				int n=1;
				for(QuoAnsBean qb:alist){
					ps.setInt(1,n++);
					ps.setString(2,qb.getQuestion());
					ps.setString(3,qb.getOpt1());
					ps.setString(4,qb.getOpt2());
					ps.setString(5,qb.getOpt3());
					ps.setString(6,qb.getOpt4());
					ps.setInt(7, qb.getAnswer());
					
					if(ps.executeUpdate()>0)
						System.out.println("Ques#"+(n-1)+" inserted !!!");
				}
				status=true;
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		
		}
		return status;
	}
	
}
