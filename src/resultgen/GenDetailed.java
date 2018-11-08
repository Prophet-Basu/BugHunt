package resultgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.poi.hssf.record.CFRuleBase.ComparisonOperator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class GenDetailed {

	public static void generate(int t, int s,String filePath) {

		int sc = 1;
		TeamAnswerDao tadao = new TeamAnswerDaoImpl("bughunt");
		LoginDao ldao = new LoginDaoImpl("bughunt", "team");
		ArrayList<TeamAnsDurBean> tlist = new ArrayList<>();

		// int marksArray[] = new int[t];
		// long durationArray[] = new long[t];

		XSSFWorkbook workbook = new XSSFWorkbook();

		for (int i = 1; i <= t; i++) {
			Timestamp ts = ldao.getLoginTimeForTeam(i);
			int l = ldao.getLangForTeam(i);
			ArrayList<TeamAnswerBean> list = tadao.getAnsweredQuestionByTeam(i, sc++, l);

			if (list.size() > 0) {

				// Create Excel Sheet
				// -------------------------------------------------------------------------------------------
				XSSFSheet sheet = workbook.createSheet("Team" + i);
				SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();

				XSSFCellStyle headerstyle = workbook.createCellStyle();
				XSSFFont headerfont = workbook.createFont();
				headerfont.setBold(true);
				headerstyle.setFont(headerfont);
				headerstyle.setFillForegroundColor(IndexedColors.AQUA.index);
				headerstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

				XSSFCellStyle marks = workbook.createCellStyle();
				XSSFFont marksfont = workbook.createFont();
				marksfont.setColor(IndexedColors.WHITE.index);
				marksfont.setBold(true);
				marks.setFont(marksfont);

				int mrk = 0;
				long dur = 0;
				int rn = 0;
				Row headerRow = sheet.createRow(rn++);
				int cn = 9;
				Cell cell[] = new Cell[cn];

				// Header Row -------------------------------------------
				for (int k = 0; k < cn; k++) {
					cell[k] = headerRow.createCell(k);
					cell[k].setCellStyle(headerstyle);
				}
				cell[0].setCellValue("TeamID");
				cell[1].setCellValue("Team Name");
				cell[2].setCellValue("Lang");
				cell[3].setCellValue("Question No.");
				cell[4].setCellValue("Rec Time");
				cell[5].setCellValue("Given Answer");
				cell[6].setCellValue("Correct Answer");
				cell[7].setCellValue("Marks");
				// -------------------------------------------------------

				for (TeamAnswerBean x : list) {

					Row row = sheet.createRow(rn++);

					for (int k = 0; k < cn; k++)
						cell[k] = row.createCell(k);
					cell[0].setCellValue(x.getTeamID());
					cell[1].setCellValue(x.getT_name());
					cell[2].setCellValue(x.getLang());
					cell[3].setCellValue(x.getQno());
					dur += (x.getRectime().getTime() - ts.getTime()) / 1000;
					cell[4].setCellValue(dur + " s");
					cell[5].setCellValue(x.getGivenAns());
					cell[6].setCellValue(x.getCorrectAns());
					if (x.getCorrectAns() == x.getGivenAns()) {
						cell[7].setCellValue(+3);
						cell[7].setCellStyle(marks);
						mrk += 3;
					} else {
						cell[7].setCellValue(-1);
						cell[7].setCellStyle(marks);
						mrk -= 1;
					}
					rn = rn + 1;

				}

				rn = rn + 3;

				Row lastRow = sheet.createRow(rn++);
				// Last Row -------------------------------------------
				for (int k = 0; k < cn; k++)
					cell[k] = lastRow.createCell(k);

				cell[5].setCellValue("Total Marks");
				cell[7].setCellFormula("SUM(H2:H" + (rn - 1) + ")");
				cell[7].setCellStyle(headerstyle);

				TeamAnswerBean temp = list.get(0);
				if (temp.getLang() == 1) {
					tlist.add(new TeamAnsDurBean(temp.getTeamID(), temp.getT_name(), mrk, dur, "GNU C"));
				} else {
					tlist.add(new TeamAnsDurBean(temp.getTeamID(), temp.getT_name(), mrk, dur, "JAVA"));
				}

				// -------------------------------------------------------
				rn = rn + 2;

				Row loginRow = sheet.createRow(rn++);
				// Login Row -------------------------------------------
				for (int k = 0; k < cn; k++) {
					cell[k] = loginRow.createCell(k);
					cell[k].setCellStyle(headerstyle);
				}
				cell[2].setCellValue("Logged In At: ");
				if (ts != null)
					cell[4].setCellValue(ts.toString());

				// -------------------------------------------------------

				// Condition 1: Cell Value Is +3 (Green Fill)
				ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule(ComparisonOperator.EQUAL,
						"+3");
				PatternFormatting fill1 = rule1.createPatternFormatting();
				fill1.setFillBackgroundColor(IndexedColors.DARK_GREEN.index);
				fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

				// Condition 1: Cell Value Is -1 (Red Fill)
				ConditionalFormattingRule rule2 = sheetCF.createConditionalFormattingRule(ComparisonOperator.EQUAL,
						"-1");
				PatternFormatting fill2 = rule2.createPatternFormatting();
				fill2.setFillBackgroundColor(IndexedColors.DARK_RED.index);
				fill2.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

				System.out.println("Team" + i + " inserted with " + rn + " rows");
				CellRangeAddress[] regions = { CellRangeAddress.valueOf("H2:H" + (rn - 4)) };
				sheetCF.addConditionalFormatting(regions, rule1, rule2);

				// --------------------------------------------------------------------------------------------------------------
			} // if (list.size()>0)
			if (sc > s)
				sc = 1;
		}

		try {
			FileOutputStream fos = new FileOutputStream(new File(filePath+"\\ReportDetailed.xlsx"));
			workbook.write(fos);
			workbook.close();
			fos.close();
			System.out.println("ReportDetailed.xlsx written successfully");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// Create ReportSummary.xlsx -------------------------------------------

		createSummarySheet(tlist,filePath);

		// ----------------------------------------------------------------------

	}

	private static void createSummarySheet(ArrayList<TeamAnsDurBean> tlist,String filePath) {

		TeamAnswerDao tadao = new TeamAnswerDaoImpl("bughunt");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Summary");

		XSSFCellStyle headerstyle = workbook.createCellStyle();
		XSSFFont headerfont = workbook.createFont();
		headerfont.setBold(true);
		headerstyle.setFont(headerfont);
		headerstyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.index);
		headerstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		// Sort arraylist by desc marks (if marks equal then by shortest
		// (asc)duration)------------

		Collections.sort(tlist, new Comparator<TeamAnsDurBean>() {
			public int compare(TeamAnsDurBean tb1, TeamAnsDurBean tb2) {

				if (tb1.getMarks() < tb2.getMarks()) {
					return 1;
				} else if (tb1.getMarks() > tb2.getMarks()) {
					return -1;
				} else {

					if (tb1.getDuration() > tb2.getDuration()) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});

		// -------------------------------------------------------------------------------

		int rn = 0;
		Row headerRow = sheet.createRow(rn++);
		int cn = 21;
		Cell cell[] = new Cell[cn];
		// Header Row -------------------------------------------
		for (int k = 0; k < cn; k++) {
			cell[k] = headerRow.createCell(k);
			cell[k].setCellStyle(headerstyle);
		}
		cell[1].setCellValue("Team ID");
		cell[3].setCellValue("Team Name");
		cell[5].setCellValue("Marks");
		cell[7].setCellValue("Duration");
		cell[9].setCellValue("Language");
		cell[11].setCellValue("Member1");
		cell[13].setCellValue("Member2");
		cell[15].setCellValue("Institute");
		cell[17].setCellValue("Phone");
		cell[19].setCellValue("Email");
		// -------------------------------------------------------
	
		for (TeamAnsDurBean tb : tlist) {

			if (tb.getDuration() != 0) {

				Row row = sheet.createRow(rn++);
				for (int k = 0; k < cn; k++)
					cell[k] = row.createCell(k);

				TeamContactDetails tcd = tadao.getTeamContactDetailsById(tb.getTeamID());

				cell[1].setCellValue(tb.getTeamID());
				cell[3].setCellValue(tb.getTeamName());
				cell[5].setCellValue(tb.getMarks());
				cell[7].setCellValue(tb.getDuration() + " ms");
				cell[9].setCellValue(tb.getLang());
				cell[11].setCellValue(tcd.getMem1());
				cell[13].setCellValue(tcd.getMem2());
				cell[15].setCellValue(tcd.getInst());
				cell[17].setCellValue(tcd.getPhn());
				cell[19].setCellValue(tcd.getEmail());

				rn = rn + 1;
			}

		}

		try {
			FileOutputStream fos = new FileOutputStream(new File(filePath+"\\ReportSummary.xlsx"));
			workbook.write(fos);
			workbook.close();
			fos.close();
			System.out.println("ReportSummary.xlsx written successfully");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
