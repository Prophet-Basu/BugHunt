package resultgen;

import java.io.Serializable;

public class TeamContactDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int teamID;
	private String mem1;
	private String mem2;
	private String inst;
	private String phn;
	private String email;

	public TeamContactDetails() {
		super();
	}

	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
	}

	public TeamContactDetails(int teamID, String mem1, String mem2, String inst, String phn, String email) {
		super();
		this.teamID = teamID;
		this.mem1 = mem1;
		this.mem2 = mem2;
		this.inst = inst;
		this.phn = phn;
		this.email = email;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
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

	public String getPhn() {
		return phn;
	}

	public void setPhn(String phn) {
		this.phn = phn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
