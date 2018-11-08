package resultgen;

import java.io.Serializable;
import java.sql.Timestamp;

public class TeamAnswerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int teamID;
	private String t_name;
	private int lang;
	private int qno;
	private int givenAns;
	private Timestamp rectime;
	private int correctAns;

	public TeamAnswerBean() {
		super();
	}

	public TeamAnswerBean(int teamID, String t_name, int lang, int qno, int givenAns, Timestamp rectime,
			int correctAns) {
		super();
		this.teamID = teamID;
		this.t_name = t_name;
		this.lang = lang;
		this.qno = qno;
		this.givenAns = givenAns;
		this.rectime = rectime;
		this.correctAns = correctAns;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public int getLang() {
		return lang;
	}

	public void setLang(int lang) {
		this.lang = lang;
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public int getGivenAns() {
		return givenAns;
	}

	public void setGivenAns(int givenAns) {
		this.givenAns = givenAns;
	}

	public Timestamp getRectime() {
		return rectime;
	}

	public void setRectime(Timestamp rectime) {
		this.rectime = rectime;
	}

	public int getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}

}
