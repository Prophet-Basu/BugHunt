package resultgen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.utility.DBUtil;

public class LoginDaoImpl implements LoginDao {

	Connection conn=null;
	String db,table;
	
	
	
	public LoginDaoImpl(String db, String table) {
		super();
		this.db = db;
		this.table = table;
	}



	@Override
	public Timestamp getLoginTimeForTeam(int teamID) {
		Timestamp ts = null;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select logintime from "+table+" where teamid=?");
				
				ps.setInt(1, teamID);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					ts = rs.getTimestamp(1);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		return ts;
	}


	@Override
	public int getLangForTeam(int teamID) {
		int l=-1;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select lang from "+table+" where teamid=?");
				
				ps.setInt(1, teamID);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					l = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		return l;
	}

}
