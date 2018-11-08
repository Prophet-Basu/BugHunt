package resultgen;

import java.util.ArrayList;


public interface TeamAnswerDao {
	
	public ArrayList<TeamAnswerBean> getAnsweredQuestionByTeam(int teamID,int i,int l);
	
	public int countTeam();
	
	public int countAnsTables();
	
	public TeamContactDetails getTeamContactDetailsById(int id);

}
