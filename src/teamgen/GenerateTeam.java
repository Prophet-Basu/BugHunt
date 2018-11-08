package teamgen;

public class GenerateTeam {

	public static void Generate(int team, int sets, int anstables, int dur) {
		

		
		if(CreateTable.createTable2()){
			FillTable.fillTable2(team,sets,anstables,dur);
		}
	}

}
