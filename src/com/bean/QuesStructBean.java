package com.bean;

import java.io.Serializable;

public class QuesStructBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int slno;
	private int qno;
	private int givenans;

	public QuesStructBean() {
		super();
	}

	public QuesStructBean(int slno, int qno, int givenans) {
		super();
		this.slno = slno;
		this.qno = qno;
		this.givenans = givenans;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public int getGivenans() {
		return givenans;
	}

	public void setGivenans(int givenans) {
		this.givenans = givenans;
	}

	public String toString(){
		return "slno: "+slno+" qno: "+qno+" gvnans: "+givenans;
	}

}
