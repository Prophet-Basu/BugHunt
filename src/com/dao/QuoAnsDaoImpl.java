package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.QuoAnsBean;
import com.utility.DBUtil;

public class QuoAnsDaoImpl implements QuoAnsDao{

	Connection conn=null;
	String db,table;
	
	
	public QuoAnsDaoImpl(String db, String table) {
		super();
		this.db = db;
		this.table = table;
	}

	@Override
	public void insertQandA(QuoAnsBean qabean) {
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("insert into "+table+"(quo,opt1,opt2,opt3,opt4,ans) values(?,?,?,?,?,?)");
				
				ps.setString(1, qabean.getQuestion());
				ps.setString(2, qabean.getOpt1());
				ps.setString(3, qabean.getOpt2());
				ps.setString(4, qabean.getOpt3());
				ps.setString(5, qabean.getOpt4());
				ps.setInt(6, qabean.getAnswer());
				
				if(ps.executeUpdate()>0)
					System.out.println("Q&A inserted!!!");
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
	}

	@Override
	public QuoAnsBean fetchQandA(int qno) {
		QuoAnsBean qabean=null;
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps= conn.prepareStatement("select * from "+table+" where qno=?");
				
				ps.setInt(1, qno);
				
				ResultSet rs =ps.executeQuery();
				if(rs.next()){
					int qnumber=qno;
					String question=rs.getString(2);
					String opt1=rs.getString(3);
					String opt2=rs.getString(4);
					String opt3=rs.getString(5);
					String opt4=rs.getString(6);
					int answer = rs.getInt(7);
					
					qabean=new QuoAnsBean(qnumber, question, answer, opt1, opt2, opt3, opt4);
				}
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		return qabean;
	}

	@Override
	public ArrayList<QuoAnsBean> fetchAll() {
		ArrayList<QuoAnsBean> qalist=new ArrayList<>();
		
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select * from "+table);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					int qnumber=rs.getInt(1);
					String question=rs.getString(2);
					String opt1=rs.getString(3);
					String opt2=rs.getString(4);
					String opt3=rs.getString(5);
					String opt4=rs.getString(6);
					int answer = rs.getInt(7);
					
					qalist.add(new QuoAnsBean(qnumber, question, answer, opt1, opt2, opt3, opt4));
				}
				
			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
			}
		}
		
		return qalist;
	}

	@Override
	public int getTotalQues() {
		int n=-1;
		conn=DBUtil.getConnection(db);
		
		if(conn!=null)
		{
			try {
				PreparedStatement ps=conn.prepareStatement("select count(*) from "+table);
				
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					n = rs.getInt(1);
				}
			
			
		} catch (SQLException e) {
			System.out.println("Incorrect SQL Query!!!\n"+e.getMessage());
		}
			
		}
		return n;
	}

}
