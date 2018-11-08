package resultgen;

import java.io.Serializable;

public class TeamAnsDurBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int teamID;
	private String teamName;
	private int marks;
	private long duration;
	private String lang;

	public TeamAnsDurBean() {
		super();
	}

	public TeamAnsDurBean(int teamID, String teamName, int marks, long duration, String lang) {

		this.teamID = teamID;
		this.teamName = teamName;
		this.marks = marks;
		this.duration = duration;
		this.lang = lang;

	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
