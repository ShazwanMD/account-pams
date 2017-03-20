package my.edu.umk.pams.academic.data;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author PAMS
 */
public class SponsorGenerator extends GeneratorSupport {

    private static final Logger LOG = LoggerFactory.getLogger(SponsorGenerator.class);

    private final String SOURCE_FILE = "data/csv/AC_SPSR.csv";

    private final String TEMPLATE = "INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)\n" +
            "VALUES\n" +
            "  (nextval('SQ_AC_ACTR'), '%1$s', '%2$s', 'todo@todo.my', NULL, NULL, NULL, 2,\n" +
            "                          CURRENT_TIMESTAMP, 1, 1);\n" +
            "\n" +
            "INSERT INTO AC_SPSR (ID, SPONSOR_TYPE) VALUES (currval('SQ_AC_ACTR'), 1);\n";

    @Test
    @Rollback(false)
    public void loadData() throws IOException {
        FileWriter writer = new FileWriter("src/test/resources/data/AC_SPSR.sql");
        List<String> lines = readSource(SOURCE_FILE);
        for (String line : lines) {
            String[] split = line.split(",", -1);
            // skipping this for now, because we have
            // created JPA/PTPTN seed
            if (!"JPA".equals(split[0]) && !"PTPTN".equals(split[0])) {
                String formatted = String.format(TEMPLATE, split[0], split[1]);
                LOG.debug("formatted: " + formatted);
                writer.write(formatted);
            }
        }
        writer.flush();
        writer.close();
    }
}