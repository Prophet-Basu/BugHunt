package com.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class TeamBean implements Serializable {

	public static final String COMP_NAME = "EDGX BugHunt Prelims";
	private static final long serialVersionUID = 1L;
	private int teamid;
	private String name;
	private String pass;
	private int setid;
	private int lang;
	private int tableid;
	private Timestamp logintime;
	private boolean lgstatus;
	private int remtime;
	private String mem1;
	private String mem2;
	private String inst;

	public TeamBean() {
		super();
	}

	public TeamBean(int teamid, String name, String pass, int setid, int lang, int tableid, Timestamp logintime,
			boolean lgstatus, int remtime, String mem1, String mem2, String inst) {
		super();
		this.teamid = teamid;
		this.name = name;
		this.pass = pass;
		this.setid = setid;
		this.lang = lang;
		this.tableid = tableid;
		this.logintime = logintime;
		this.lgstatus = lgstatus;
		this.remtime = remtime;
		this.mem1 = mem1;
		this.mem2 = mem2;
		this.inst = inst;
	}

	public int getTeamid() {
		return teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getSetid() {
		return setid;
	}

	public void setSetid(int setid) {
		this.setid = setid;
	}

	public int getLang() {
		return lang;
	}

	public void setLang(int lang) {
		this.lang = lang;
	}

	public int getTableid() {
		return tableid;
	}

	public void setTableid(int tableid) {
		this.tableid = tableid;
	}

	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

	public boolean isLgstatus() {
		return lgstatus;
	}

	public void setLgstatus(boolean lgstatus) {
		this.lgstatus = lgstatus;
	}

	public int getRemtime() {
		return remtime;
	}

	public void setRemtime(int remtime) {
		this.remtime = remtime;
	}

	public String getMem1() {
		return mem1;
	}

	public void setMem1(String mem1) {
		this.mem1 = mem1;
	}

	public String getMem2() {
		return mem2;
	}

	public void setMem2(String mem2) {
		this.mem2 = mem2;
	}

	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
	}

}
