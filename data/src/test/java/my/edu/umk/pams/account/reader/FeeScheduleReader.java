package my.edu.umk.pams.account.reader;

import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.data.ProgramGenerator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
    public void loadData() throws IOException {
        try {
            File out = new File("AC_FEE_SCDL.sql");
            File file = new File("C:/Projects/GitLab/UMK/account/data/src/site/cps-normalizatize table.xlsx");
            FileWriter writer = new FileWriter(out);
            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
            LOG.debug("number of sheets: " + workbook.getNumberOfSheets());
            for (int i = 0; i < COURSES.length; i++) {
                String course = COURSES[i];
                Sheet sheet = workbook.getSheet(course);
                int lastRowNum = sheet.getLastRowNum();
                for (int j = 0; j < 10; j++) {
                    Row row = sheet.getRow(j);
                    String description = row.getCell(0).getStringCellValue();
                    LOG.debug("ro: " + description);
//                    Double amount = row.getCell(1).getNumericCellValue();
                    writer.write("INSERT INTO AC_FEE_SCDL VALUES = " + description + ");");
                }
            }
            writer.flush();
            writer.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
