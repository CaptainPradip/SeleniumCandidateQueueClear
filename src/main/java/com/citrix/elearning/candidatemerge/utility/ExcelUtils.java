package com.citrix.elearning.candidatemerge.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This Class for Excel Writing and Reading
 *
 * @author Pradip.Nemane
 *
 */
public class ExcelUtils {

	static String filePath = PropertyUtil.getProperty("filePath");;

	/**
	 * Method for create result file.
	 *
	 * @return file path.
	 * @throws IOException
	 */
	public static String createResultFileAndGetPath() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();

		FileOutputStream out = new FileOutputStream(ExcelUtils.filePath);

		workbook.write(out);
		workbook.close();
		out.close();
		return ExcelUtils.filePath;
	}

	/**
	 * Method for get Workbook
	 *
	 * @return {@link Workbook}
	 * @throws IOException
	 */
	private static Workbook getWorkbook() throws IOException {
		File file = new File(ExcelUtils.filePath);
		Workbook workbook = null;
		try {

			InputStream is = new FileInputStream(file);
			workbook = new XSSFWorkbook(is);

		} catch (Exception e) {

		}
		return workbook;
	}

	/**
	 * Method for Write date into Excel file.
	 *
	 * @param candidateProfile
	 * @return file path.
	 */
	public static String writeDataIntoExcel(List<CandidateProfile> candidateProfile) {
		try {
			ExcelUtils.createResultFileAndGetPath();
			Workbook workbook = ExcelUtils.getWorkbook();

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
			cell.setCellValue("Test Result");

			cell = row.createCell(5);
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
				cell.setCellValue(profile.getExecutionTime().toString().trim());

			}

			FileOutputStream output_file = new FileOutputStream(ExcelUtils.filePath);
			workbook.write(output_file);
			workbook.close();
			output_file.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ExcelUtils.filePath;

	}
}
