package quesgen;

import java.util.ArrayList;

import com.bean.QuoAnsBean;

public class GenerateQues {

	public static void Generate(String filename, int lang) {
		
		
		
		ArrayList<QuoAnsBean> alist = ExtractQuesArray.extractQuesArray(filename);
		if(alist.size()>0){
			if(CreateTable.createTable(lang)){
				FillTable.fillTable(alist);
			}
			
			
		}else{
			System.out.println("List is null");
		}
	}

}
