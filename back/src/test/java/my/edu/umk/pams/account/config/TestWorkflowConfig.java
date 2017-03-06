package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.workflow.integration.identity.ActivitiGroupManagerFactory;
import my.edu.umk.pams.account.workflow.integration.identity.ActivitiUserManagerFactory;
import my.edu.umk.pams.account.workflow.integration.listener.*;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author canang technologies
 * @since 2/1/14
 */
@Configuration
public class TestWorkflowConfig {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ActivitiUserManagerFactory userManagerFactory;

    @Autowired
    private ActivitiGroupManagerFactory groupManagerFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public ProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setProcessEngineName("Workflow Engine");
        configuration.setDataSource(dataSource);
        configuration.setDatabaseType(env.getProperty("db"));
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setHistory("audit");

        // custom session factory
        List<SessionFactory> sf = new ArrayList<SessionFactory>();
        sf.add(userManagerFactory);
        sf.add(groupManagerFactory);
        configuration.setCustomSessionFactories(sf);
        return configuration;
    }

    @Bean
    public ProcessEngine processEngine() throws Exception {
        ProcessEngineFactoryBean engineFactoryBean = new ProcessEngineFactoryBean();
        engineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration());
        engineFactoryBean.setApplicationContext(applicationContext);   // set application context !!!!
        return engineFactoryBean.getObject();
    }

    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngine().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngine().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngine().getTaskService();
    }

    @Bean
    public IdentityService identityService() throws Exception {
        return processEngine().getIdentityService();
    }

    @Bean
    public HistoryService historyService() throws Exception {
        return processEngine().getHistoryService();
    }

    @Bean
    public TaskCreatedListener taskCreatedListener() {
        return new TaskCreatedListener();
    }

    @Bean
    public TaskAssignedListener taskAssignedListener() {
        return new TaskAssignedListener();
    }

    @Bean
    public TakeProcessListener takeProcessListener() {
        return new TakeProcessListener();
    }

    @Bean
    public StartProcessListener startProcessListener() {
        return new StartProcessListener();
    }

    @Bean
    public EndProcessListener endProcessListener() {
        return new EndProcessListener();
    }
}
