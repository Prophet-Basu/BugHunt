package com.dao;

import java.util.ArrayList;

import com.bean.TeamBean;

public interface TeamDao {

	public boolean validateTeam(String teamName, String teamPass);
	
	public TeamBean returnTeam(int teamID, String teamPass);
	
	public ArrayList<Integer> getSet(int teamID,String setTable);
	
	public boolean lockTeam(int teamID);
	
	public boolean loginTeam(int teamID);
	
	public int registerTeam(String mem1,String mem2,String inst,int lang,String name,String pass,String phn,String email);
}
