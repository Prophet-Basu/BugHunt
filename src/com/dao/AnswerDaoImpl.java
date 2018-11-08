package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.utility.DBUtil;

public class AnswerDaoImpl implements AnswerDao {

	Connection conn=null;
	String db,table;
	
	
	public AnswerDaoImpl(String db, String table) {
		super();
		this.db = db;
		this.table = table;
	}
	
	@Override
	public boolean recordAnswer(int teamID, String teamname, int qno, int givenAns,int lang, Timestamp ts,long diff) {
		boolean status = false;
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("insert into "+table+" values(?,?,?,?,?,?)");
				
				
				ps.setInt(1, teamID);
				ps.setString(2, teamname);
				ps.setInt(3,lang);
				ps.setInt(4, qno);
				ps.setInt(5,givenAns);
				ps.setTimestamp(6, ts);
				
				if(ps.executeUpdate()>0)
					System.out.println(teamname+" Answer recorded dur: "+diff+" !!");
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		
		return status;
	}


	@Override
	public boolean updateAnswer(int teamID, String teamname, int qno, int givenAns,int lang, Timestamp ts,long diff) {
		boolean status = false;
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("update "+table+" set givenans=?,rectime=?"
						+ " where teamid = ? "
						+ " and qno=? "
						+ "limit 1");
				
				
				
				ps.setInt(1,givenAns);
				ps.setTimestamp(2, ts);
				ps.setInt(3, teamID);
				ps.setInt(4,qno);
				
				if(ps.executeUpdate()>0)
					System.out.println(teamname+" Answer updated dur: "+diff+" !!");
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		
		return status;
	}

}
