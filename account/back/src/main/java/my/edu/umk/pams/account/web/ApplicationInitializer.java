package my.edu.umk.pams.account.web;

import my.edu.umk.pams.account.config.AppConfiguration;
import my.edu.umk.pams.account.config.WebConfiguration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        FilterRegistration.Dynamic filter = servletContext
                .addFilter("NDCLogServletFilter", new org.springframework.web.filter.Log4jNestedDiagnosticContextFilter());
        super.onStartup(servletContext);
    }

    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[]{AppConfiguration.class, WebConfiguration.class};
    }

    @Override
    protected Class[] getServletConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);
        servletContext.addListener(new HttpSessionEventPublisher());
    }
}