package setgen;

import com.dao.QuoAnsDao;
import com.dao.QuoAnsDaoImpl;

public class GenerateSet {

	public static void Generate(int lang,int quescount, int setnumber) {
		
		QuoAnsDao qadao;
		if(lang==1){
			qadao = new QuoAnsDaoImpl("bughunt", "cquesans");
			if(CreateTable.createTable(setnumber, quescount ,"csets")){
				FillTable.fillTable(setnumber,qadao.getTotalQues(), quescount);
			}
			
		}else if(lang == 2){
			qadao = new QuoAnsDaoImpl("bughunt", "cppquesans");
			if(CreateTable.createTable(setnumber, quescount ,"cppsets")){
				FillTable.fillTable(setnumber,qadao.getTotalQues(), quescount);
			}
		}else{
			qadao = new QuoAnsDaoImpl("bughunt", "javaquesans");
			if(CreateTable.createTable(setnumber, quescount ,"javasets")){
				FillTable.fillTable(setnumber,qadao.getTotalQues(), quescount);
			}
		}
	}
		
}


