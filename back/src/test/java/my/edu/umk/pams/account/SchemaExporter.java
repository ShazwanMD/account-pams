package my.edu.umk.pams.account;

import my.edu.umk.pams.account.util.HibernateExporterUtil;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author PAMS
 */
public class SchemaExporter {
    public static void main(String[] args) throws FileNotFoundException {
        HibernateExporterUtil exporter = new HibernateExporterUtil(
                "org.hibernate.dialect.PostgreSQL82Dialect",
                "my.edu.umk.pams"
        );
        exporter.setGenerateDropQueries(false);
        exporter.export(new File("create.sql"));
    }
}
