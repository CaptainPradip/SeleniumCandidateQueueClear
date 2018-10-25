package com.citrix.elearning.candidatemerge.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static String filePath = PropertyUtil.getProperty("filePath");;

	// This method is just for creating 4 results files in a folder based on
	// sheet name
	public static String createResultFileAndGetPath() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();

		FileOutputStream out = new FileOutputStream(filePath);

		workbook.write(out);
		workbook.close();
		out.close();
		return filePath;
	}

	private static Workbook readExcel() throws IOException {
		File file = new File(filePath);
		Workbook Workbook = null;
		try {

			InputStream is = new FileInputStream(file);
			Workbook = new XSSFWorkbook(is);

		} catch (Exception e) {

		}
		return Workbook;
	}

	public static String writeDataIntoExcel(List<CandidateProfile> candidateProfile) {
		try {
			createResultFileAndGetPath();
			Workbook workbook = readExcel();

			Sheet spreadSheet = workbook.getSheetAt(0);
			Row row = spreadSheet.createRow(0);
			// logger.info(header.getStringCellValue());
			Cell cell;

			cell = row.createCell(0);
			// Writing value into cell
			cell.setCellValue("Sr No.");
			cell = row.createCell(1);
			// Writing value into cell
			cell.setCellValue("Candidate Id");
			cell = row.createCell(2);
			// Writing value into cell
			cell.setCellValue("First Name");
			cell = row.createCell(3);
			// Writing value into cell
			cell.setCellValue("Last Name");
			cell = row.createCell(4);
			// Writing value into cell

			// Writing value into cell
			cell.setCellValue("Reason");
			cell = row.createCell(5);
			// Writing value into cell
			cell.setCellValue("Test Result");
			cell = row.createCell(6);
			// Writing value into cell
			cell.setCellValue("Excution Time");

			for (int i = 0; i < candidateProfile.size(); i++) {

				CandidateProfile profile = candidateProfile.get(i);
				row = spreadSheet.createRow(i + 1);

				cell = row.createCell(0);
				cell.setCellValue(i + 1);

				cell = row.createCell(1);
				cell.setCellValue(profile.getCandidateId().toString().trim());

				cell = row.createCell(2);
				cell.setCellValue(profile.getFirstName().toString().trim());

				cell = row.createCell(3);
				cell.setCellValue(profile.getLastName().toString().trim());

				cell = row.createCell(4);
				cell.setCellValue(profile.getReason());

				cell = row.createCell(5);
				if (profile.getTestResult().equals("Pass")) {
					CellStyle style = workbook.createCellStyle();
					style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					cell.setCellStyle(style);
					cell.setCellValue(profile.getTestResult().toString().trim());
					cell = row.createCell(6);
				} else {
					CellStyle style = workbook.createCellStyle();
					style.setFillForegroundColor(IndexedColors.RED.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					cell.setCellStyle(style);
					cell.setCellValue(profile.getTestResult().toString().trim());
					cell = row.createCell(6);
				}

				cell.setCellValue(profile.getExcutionTime().toString().trim());

			}

			FileOutputStream output_file = new FileOutputStream(filePath);
			workbook.write(output_file);
			workbook.close();
			output_file.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return filePath;

	}
}
