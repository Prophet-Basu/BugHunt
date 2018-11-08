package resultgen;

public class GenerateResult {
	
	public static void Generate(String filePath) {
		
		int t=-1;
		int s=-1;
		
//----------------------------------------------------------------------------------------------------
		
		TeamAnswerDao tadao = new TeamAnswerDaoImpl("bughunt");
		t = tadao.countTeam();
		
		s = tadao.countAnsTables();
//----------------------------------------------------------------------------------------------------		
		
		
		GenDetailed.generate(t,s,filePath);
		
		
		
		
	}

}
