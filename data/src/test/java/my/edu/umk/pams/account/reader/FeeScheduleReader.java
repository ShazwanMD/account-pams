package my.edu.umk.pams.account.reader;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.data.ProgramGenerator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author PAMS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class FeeScheduleReader {

    private static final Logger LOG = LoggerFactory.getLogger(ProgramGenerator.class);
    private static final String[] COURSES = {"FKP-FULLTIME"};

    @Test
    @Rollback(false)
    public void loadDataTempatan() throws IOException {
        try {
            File out = new File("AC_FEE_SCDL_DOMESTIC.sql");
            File file = new File("C:/Projects/GitLab/UMK/account/data/src/site/cps-normalizatize table.xlsx");
            FileWriter writer = new FileWriter(out);
            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
            LOG.debug("number of sheets: " + workbook.getNumberOfSheets());
            for (int i = 0; i < COURSES.length; i++) {
                String course = COURSES[i];
                Sheet sheet = workbook.getSheet(course);
                writer.write("INSERT INTO AC_FEE_SCDL (ID, DESCRIPTION ) VALUES ("
                        + "nextval('SQ_AC_FEE_SCDL'),"
                        + "'XXX'); \n");


                int lastRowNum = sheet.getLastRowNum();
                for (int j = 7; j < lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        LOG.debug(toString(row.getCell(0)));
                        LOG.debug(toString(row.getCell(1)));
                        writer.write("INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES ("
                                + "nextval('SQ_AC_FEE_SCDL_ITEM'),"
                                + "'" + toString(row.getCell(0)) + "',"
                                + "'" + toString(row.getCell(1)) + "');");
                    }
                }
            }
            writer.flush();
            writer.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Rollback(false)
    public void loadDataInternational() throws IOException {
        try {
            File out = new File("AC_FEE_SCDL_INTERNATIONAL.sql");
            File file = new File("C:/Projects/GitLab/UMK/account/data/src/site/cps-normalizatize table.xlsx");
            FileWriter writer = new FileWriter(out);
            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
            LOG.debug("number of sheets: " + workbook.getNumberOfSheets());
            for (int i = 0; i < COURSES.length; i++) {
                String course = COURSES[i];
                Sheet sheet = workbook.getSheet(course);
                writer.write("INSERT INTO AC_FEE_SCDL (ID, DESCRIPTION ) VALUES ("
                        + "nextval('SQ_AC_FEE_SCDL'),"
                        + "'XXX'); \n");


                int lastRowNum = sheet.getLastRowNum();
                for (int j = 7; j < lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        LOG.debug(toString(row.getCell(0)));
                        LOG.debug(toString(row.getCell(2)));
                        writer.write("INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES ("
                                + "nextval('SQ_AC_FEE_SCDL_ITEM'),"
                                + "'" + toString(row.getCell(0)) + "',"
                                + "'" + toString(row.getCell(1)) + "'); \n");
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

}
