package quesgen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.QuoAnsBean;

public class ExtractQuesArray {
	
	public static ArrayList<QuoAnsBean> extractQuesArray(String fileName){
		ArrayList<QuoAnsBean> alist = new ArrayList<>();
		
//-------------------------------------------------------------------------------------------------------		
		

		
		try {
			
			FileInputStream fis = new FileInputStream(new File(fileName));
			
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet firstSheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = firstSheet.rowIterator();
			while(rowIterator.hasNext()){
				Row nextrow = rowIterator.next();
				Iterator<Cell> cellIterator = nextrow.cellIterator();
				QuoAnsBean qb = new QuoAnsBean();
				Cell cell=null;
				cell = cellIterator.next();
				qb.setQnumber((int)cell.getNumericCellValue());
				cell = cellIterator.next();
				/*< - &lt
				 *> - &gt */
				String s = cell.getStringCellValue();
				s=s.replace("<", "&lt");
				s=s.replace(">", "&gt");
				qb.setQuestion(s);
				
				cell = cellIterator.next();
				s = cell.getStringCellValue();
				s=s.replace("<", "&lt");
				s=s.replace(">", "&gt");
				qb.setOpt1(s);
				
				cell = cellIterator.next();
				s = cell.getStringCellValue();
				s=s.replace("<", "&lt");
				s=s.replace(">", "&gt");
				qb.setOpt2(s);
				
				cell = cellIterator.next();
				s = cell.getStringCellValue();
				s=s.replace("<", "&lt");
				s=s.replace(">", "&gt");
				qb.setOpt3(s);
				
				cell = cellIterator.next();
				s = cell.getStringCellValue();
				s=s.replace("<", "&lt");
				s=s.replace(">", "&gt");
				qb.setOpt4(s);
				
				cell = cellIterator.next();
				qb.setAnswer((int)cell.getNumericCellValue());
				
				alist.add(qb);
				
			}
			workbook.close();
	        fis.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
//-------------------------------------------------------------------------------------------------------		
		return alist;
	}

}
