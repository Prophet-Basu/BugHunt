package resultgen;

import java.sql.Timestamp;

public interface LoginDao {

		
		public Timestamp getLoginTimeForTeam(int teamID);
		public int getLangForTeam(int teamID); 
}
