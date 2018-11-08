package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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
	public Timestamp logInTeam(String teamName,int teamID) {
		
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("update "+table+" set logintime = ? where teamid=?");
				ps.setTimestamp(1, ts);
				ps.setInt(2, teamID);
				
				if(ps.executeUpdate()>0)
					System.out.println(teamName+" logged in at: "+ts+" !!");
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		return ts;
	}

}
