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
public class AccountGenerator extends GeneratorSupport {

    private static final Logger LOG = LoggerFactory.getLogger(AccountGenerator.class);

    private final String SOURCE_FILE = "data/csv/AC_SPSR.csv";

    private final String TEMPLATE = "INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)\n" +
            "    values (nextval('SQ_AC_ACCT'),\n" +
            "            (select ID from AC_ACTR where identity_no = '%1$s'),\n" +
            "            (select IDENTITY_NO from AC_ACTR where identity_no = '%1$s'),\n" +
            "            '%2$s',\n" +
            "            1, CURRENT_TIMESTAMP, 1);";

    @Test
    @Rollback(false)
    public void loadData() throws IOException {
        FileWriter writer = new FileWriter("src/test/resources/data/AC_ACCT-SPSR.sql");
        List<String> lines = readSource(SOURCE_FILE);
        for (String line : lines) {
            String[] split = line.split(",", -1);
            // skipping this for now, because we have
            // created JPA/PTPTN seed
            if (!"JPA".equals(split[0]) && !"PTPTN".equals(split[0])) {
                String formatted = String.format(TEMPLATE, split[0], split[1]);
                LOG.debug("formatted: " + formatted);
                writer.write(formatted + "\n");
            }
        }
        writer.flush();
        writer.close();
    }
}