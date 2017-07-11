package my.edu.umk.pams.account.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.data.ProgramGenerator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class ReaderActor {
	


	private static final Logger LOG = LoggerFactory
			.getLogger(ProgramGenerator.class);
	private static final String[] COURSES = { "FKP-FULLTIME" };

	@Autowired
	private AccountService accountService;

	@Test
	@Rollback(false)
	public void loadDataInternational() throws IOException {

		try {
			// File out = new
			// File("C:/Projects/GitLab/UMK/account/data/src/site/AC_FEE_SCDL_DOMESTIC.sql");
			File out = new File(
					"C:/Users/UMK-PEJA/git/account/data/src/site/DATA CPS/Fkp-phd-test1.sql");
			// File file = new
			// File("C:/Projects/GitLab/UMK/account/data/src/site/FSB-PHD-0001-CHRT-201720181.xlsx");
			File file = new File(
			// "C:/Users/UMK-PEJA/git/account/data/src/site/DATA CPS/FSB-PHD-0001-CHRT-201720181.xlsx");
					"C:/Users/UMK-PEJA/git/account/data/src/site/DATA CPS/Fkp-phd-test1.xlsx");

			FileWriter writer = new FileWriter(out);
			Workbook workbook = WorkbookFactory
					.create(new FileInputStream(file));
			LOG.debug("number of sheets: " + workbook.getNumberOfSheets());

			for (int i = 0; i < COURSES.length; i++) {
				String course = COURSES[i];
				Sheet sheet = workbook.getSheet(course);
				writer.write("INSERT INTO AC_FEE_SCDL (ID,RESIDENCY_CODE_ID, COHORT_CODE_ID, STUDY_MODE_ID, CODE, DESCRIPTION, TOTAL_AMOUNT, M_ST, C_TS,C_ID) VALUES (\n"
						+ "nextval('SQ_AC_FEE_SCDL'),"
						+ "(SELECT ID from AC_RSCY_CODE where code ='"
						+ getCell(sheet, 3, 1)
						+ "' ),"
						+ "(SELECT ID FROM AC_CHRT_CODE WHERE CODE = '"
						+ getCell(sheet, 1, 1)
						+ "' ),"
						+ "(SELECT ID FROM AC_STDY_MODE WHERE CODE = '"
						+ getCell(sheet, 2, 1)
						+ "' ),"
						+ "'YB-"
						+ getCell(sheet, 1, 1)
						+ "',"
						+ "'"
						+ getCell(sheet, 0, 1)
						+ "',"
						+ "0.00,"
						+ "1,"
						+ "CURRENT_TIMESTAMP," + "1); \n\n\n\n\n");

			int lastRowNum = sheet.getLastRowNum();
				for (int j = 7; j < lastRowNum; j++) {
					// for (int j = 7; j < 19; j++) {

					Row row = sheet.getRow(j);
					if (row != null) {

						Cell cell0 = row.getCell(0);
						Cell cell1 = row.getCell(1);
						Cell cell2 = row.getCell(2);

						if (cell0 != null && cell1 != null && cell2 != null) {
								writer.write("INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) \n"
									+ " VALUES ("
									+ "nextval('SQ_AC_FEE_SCDL_ITEM'),"
									+ "'"
									+ toString(row.getCell(0))
									+ "',"
									+ toString(row.getCell(4), true)
									+ ","
									// + "8,"
									+ "currval('SQ_AC_FEE_SCDL'),"
									+ "(SELECT ID FROM AC_CHRG_CODE WHERE CODE = '"
									+ row.getCell(3)
									+ "'),"
									+ "'"
									+ toString(row.getCell(2))
									+ "',"
									+ "CURRENT_TIMESTAMP,"
									+ "1,"
									+ "1);"
									+ "\n\n");

						}
						LOG.debug("CELL 0 *********"+toString(row.getCell(0))+"");
						LOG.debug("CELL 1 *********"+toString(row.getCell(2))+"\n\n");

					}

				}
			}
			writer.flush();
			writer.close();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}
	
	private String toString(Cell cell) {
		if (cell.getCellType() == 1)
			return cell.getStringCellValue();
		if (cell.getCellType() == 0)
			return Double.toString(cell.getNumericCellValue());
		if (cell.getCellType() == 2)
			return "";
		return "";
	}

	private String toString(Cell cell, boolean removeDecimal) {
		if (cell.getCellType() == 1)
			return cell.getStringCellValue();
		if (cell.getCellType() == 0)
			return Integer.toString((int) cell.getNumericCellValue());
		if (cell.getCellType() == 2)
			return "";
		return "";
	}

	private String getCell(Sheet sheet, int rowIndex, int colIndex) {
		Row row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(colIndex);
		// LOG.debug("cell: " + cell.getCellType());
		// LOG.debug("row :"+row.getRowNum());

		return toString(cell);
	}

}
