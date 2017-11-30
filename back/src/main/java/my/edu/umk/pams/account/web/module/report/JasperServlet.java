package my.edu.umk.pams.account.web.module.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class JasperServlet extends HttpServlet {

    public static final String PARAM_REPORT = "report";
    private final Logger LOG = LoggerFactory.getLogger(JasperServlet.class);

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletContext = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            // transform parameters from URL
            Map<String, Object> map = new HashMap<String, Object>();
            Enumeration parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = (String) parameterNames.nextElement();
                map.put(paramName, req.getParameter(paramName));
            }

            // report and image path
            String reportName = req.getParameter(PARAM_REPORT);
            String path = "/reports/" + reportName;
            ClassPathResource resource = new ClassPathResource(path);

            // get connection
            connection = dataSource.getConnection();

            // load report
            JasperDesign jasDesign = JRXmlLoader.load(resource.getInputStream());
            JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasReport, map, connection);

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename="+ reportName + ".pdf" );

            // export
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}