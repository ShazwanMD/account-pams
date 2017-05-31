package my.edu.umk.pams.account.data;

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
public class FacultyGenerator extends GeneratorSupport {

    private static final Logger LOG = LoggerFactory.getLogger(FacultyGenerator.class);

    private final String SOURCE_FILE = "data/csv/AC_FCTY.csv";

    private final String TEMPLATE = "INSERT INTO AC_FCTY_CODE (ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST) " +
            "   VALUES (nextval('SQ_AC_FCTY_CODE'), " +
            "   '%1$s',\n" +
            "   '%2$s',\n" +
            "   CURRENT_TIMESTAMP, 0, 1);\n";

    @Test
    @Rollback(false)
    public void loadData() throws IOException {
        FileWriter writer = new FileWriter("src/test/resources/data/AC_FCTY_CODE.sql");
        List<String> lines = readSource(SOURCE_FILE);
        int i = 0;
        for (String line : lines) {
            String[] split = line.split(",", -1);
            String formatted = String.format(TEMPLATE, split[0], split[1]);
            LOG.debug("formatted: " + formatted);
            writer.write(formatted);
            i++;
        }
        writer.flush();
        writer.close();
    }
}