package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.bean.QuoAnsBean;
import com.bean.TeamBean;
import com.utility.DBUtil;

public class TeamDaoImpl implements TeamDao {

	Connection conn=null;
	String db,table;
	private int MAXQUES = QuoAnsBean.QUESTIONS;
	
	public TeamDaoImpl(String db, String table) {
		super();
		this.db = db;
		this.table = table;
	}
	
	
	@Override
	public boolean validateTeam(String teamName, String teamPass) {
		boolean status = false;
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps= conn.prepareStatement("select * from "+table+" where name=? and pass=?");
				
				ps.setString(1, teamName);
				ps.setString(2, teamPass);
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					status = true;
				}
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		return status;
	}


	@Override
	public ArrayList<Integer> getSet(int teamID,String setTable) {
		ArrayList<Integer> alist = new ArrayList<>();
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				String query = "select s.* from "+setTable+" s, "+table+" t where t.setid=s.setno"
						+" and t.teamid=?";
				PreparedStatement ps= conn.prepareStatement(query);
				
				ps.setInt(1,teamID);
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					for(int i=2;i<=MAXQUES+1;i++)
						alist.add(rs.getInt(i));
				}
				
				//System.out.println("setlist in dao is: "+alist);
			
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!! in getSet"+e.getMessage());
			}
		}
		
		return alist;
	}


	@Override
	public TeamBean returnTeam(int teamID, String teamPass) {
		TeamBean tb = null;
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps= conn.prepareStatement("select * from "+table+" where teamid=? and pass=?"
						+ " and lgstatus=0");
				
				ps.setInt(1, teamID);
				ps.setString(2, teamPass);
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					
					tb = new TeamBean();
					tb.setTeamid(rs.getInt(1));
					tb.setName(rs.getString(2));
					tb.setPass(rs.getString(3));
					tb.setSetid(rs.getInt(4));
					tb.setLang(rs.getInt(5));
					tb.setTableid(rs.getInt(6));
					tb.setLogintime(rs.getTimestamp(7));
					tb.setLgstatus(rs.getBoolean(8));
					tb.setRemtime(rs.getInt(9));
					tb.setMem1(rs.getString(10));
					tb.setMem2(rs.getString(11));
					tb.setInst(rs.getString(12));
				}
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		
		return tb;
	}


	@Override
	public boolean lockTeam(int teamID) {
		boolean status=false;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			try {
				PreparedStatement ps2 = conn.prepareStatement("update "+table+" set lgstatus=1, logintime=? where teamid=?");
				ps2.setTimestamp(1, timestamp);
				ps2.setInt(2, teamID); 
				if(ps2.executeUpdate()>0)
					status=true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		
		return status;
	}
	
	@Override
	public boolean loginTeam(int teamID) {
		boolean status=false;
		conn=DBUtil.getConnection(db);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if(conn!=null)
		{
			try {
				PreparedStatement ps2 = conn.prepareStatement("update "+table+" set logintime=? where teamid=?");
				ps2.setTimestamp(1, timestamp);
				ps2.setInt(2, teamID); 
				if(ps2.executeUpdate()>0)
					status=true;
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		
		return status;
	}


	@Override
	public int registerTeam(String mem1, String mem2, String inst, int lang, String name, String pass,String phn,String email) {
		int id=0;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				Statement st = conn.createStatement();
				String query1 = "select teamid from "+table+" where name is null limit 1";
				
				ResultSet rs = st.executeQuery(query1);
				if(rs.next()){
					id = rs.getInt(1);
				}
				
				if(id!=0){
					String query2 = "update "+table+" set mem1=?, mem2=?, inst=? , lang=?,name=?,pass=? ,phn=?,email=? where teamid="+id;
					PreparedStatement ps = conn.prepareStatement(query2);
					ps.setString(1, mem1);
					ps.setString(2, mem2);
					ps.setString(3, inst);
					ps.setInt(4, lang);
					ps.setString(5, name);
					ps.setString(6, pass);
					ps.setString(7, phn);
					ps.setString(8, email);
					if(ps.executeUpdate()==0){
						id=-1;
					}
				}
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		return id;
	}

}
