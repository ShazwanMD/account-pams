package my.edu.umk.pams.account.system.service;

import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.dao.*;
import my.edu.umk.pams.account.system.model.*;
import my.edu.umk.pams.account.util.Util;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author canang technologies
 * @since 1/30/14
 */
@Transactional
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    public static final DateFormat LONG_YEAR_FORMAT = new SimpleDateFormat("yyyy");
    public static final DateFormat SHORT_YEAR_FORMAT = new SimpleDateFormat("yy");
    public static final DateFormat LONG_MONTH_FORMAT = new SimpleDateFormat("MMM");
    public static final DateFormat SHORT_MONTH_FORMAT = new SimpleDateFormat("MM");
    public static final DateFormat LONG_DAY_FORMAT = new SimpleDateFormat("dd");
    public static final DateFormat SHORT_DAY_FORMAT = new SimpleDateFormat("dd");
    public static final DateFormat LONG_HOUR_FORMAT = new SimpleDateFormat("hh");
    public static final DateFormat SHORT_HOUR_FORMAT = new SimpleDateFormat("hh");
    private static final Logger LOG = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Autowired
    private IdentityService identityService;

    @Autowired
    private AcModuleDao moduleDao;

    @Autowired
    private AcSubModuleDao subModuleDao;

    @Autowired
    private AcAuditDao auditDao;

    @Autowired
    private AcReferenceNoDao referenceNoDao;

    @Autowired
    private AcConfigurationDao configurationDao;

    @Autowired
    private AcEmailTemplateDao emailTemplateDao;

    @Autowired
    private AcEmailQueueDao emailQueueDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;


    //====================================================================================================
    // MODULE SUBMODULE
    //====================================================================================================

    @Override
    public AcModule findModuleById(Long id) {
        return moduleDao.findById(id);
    }

    @Override
    public AcSubModule findSubModuleById(Long id) {
        return subModuleDao.findById(id);
    }

    @Override
    public AcModule findModuleByCode(String code) {
        return moduleDao.findByCode(code);
    }

    @Override
    public AcSubModule findSubModuleByCode(String code) {
        return subModuleDao.findByCode(code);
    }

    @Override
    public List<AcModule> findModules(boolean authorized) {
        return authorized ?
                moduleDao.findAuthorized(identityService.findSids(securityService.getCurrentUser())) :
                moduleDao.find();
    }

    @Override
    public List<AcModule> findModules() {
        return moduleDao.find();
    }

    @Override
    public List<AcModule> findModules(Integer offset, Integer limit) {
        return moduleDao.find(offset, limit);
    }

    @Override
    public List<AcModule> findModules(boolean authorized, Integer offset, Integer limit) {
        return authorized ?
                moduleDao.findAuthorized(identityService.findSids(securityService.getCurrentUser()), offset, limit) :
                moduleDao.find(offset, limit);
    }

    @Override
    public List<AcSubModule> findSubModules() {
        return subModuleDao.find();
    }

    @Override
    public List<AcSubModule> findSubModules(boolean authorized) {
        return authorized ?
                subModuleDao.findAuthorized(identityService.findSids(securityService.getCurrentUser())) :
                subModuleDao.find();
    }

    @Override
    public List<AcSubModule> findSubModules(Integer offset, Integer limit) {
        return subModuleDao.find(offset, limit);
    }

    @Override
    public List<AcSubModule> findSubModules(AcModule module, Integer offset, Integer limit) {
        return subModuleDao.find(module, offset, limit);
    }

    @Override
    public Integer countModule() {
        return moduleDao.count();
    }

    @Override
    public Integer countSubModule() {
        return subModuleDao.count();
    }

    @Override
    public Integer countSubModule(AcModule module) {
        return subModuleDao.count(module);
    }

    @Override
    public void saveModule(AcModule module) {
        moduleDao.save(module, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateModule(AcModule module) {
        moduleDao.update(module, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addSubModule(AcModule module, AcSubModule subModule) {
        moduleDao.addSubModule(module, subModule, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateSubModule(AcModule module, AcSubModule subModule) {
        moduleDao.updateSubModule(module, subModule, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // AUDIT
    //====================================================================================================

    @Override
    public AcAudit findAuditById(Long id) {
        return auditDao.findById(id);
    }

    @Override
    public List<AcAudit> findAudits(Integer offset, Integer limit) {
        return auditDao.find(offset, limit);
    }

    @Override
    public List<AcAudit> findAudits(String filter, Integer offset, Integer limit) {
        return auditDao.find(filter, offset, limit);
    }

    @Override
    public Integer countAudit() {
        return auditDao.count();
    }

    @Override
    public Integer countAudit(String filter) {
        return auditDao.count(filter);
    }

    //====================================================================================================
    // REFERENCE NO
    //====================================================================================================

    @Override
    public AcReferenceNo findReferenceNoById(Long id) {
        return referenceNoDao.findById(id);
    }

    @Override
    public AcReferenceNo findReferenceNoByCode(String code) {
        return referenceNoDao.findByCode(code);
    }

    @Override
    public List<AcReferenceNo> findReferenceNos(Integer offset, Integer limit) {
        return referenceNoDao.find(offset, limit);
    }

    @Override
    public List<AcReferenceNo> findReferenceNos(String filter, Integer offset, Integer limit) {
        return referenceNoDao.find(filter, offset, limit);
    }

    @Override
    public Integer countReferenceNo() {
        return referenceNoDao.count();
    }

    @Override
    public Integer countReferenceNo(String filter) {
        return referenceNoDao.count(filter);
    }

    @Override
    public String generateReferenceNo(String code) {
        String generatedRefNo = null;
        synchronized (this) {
            AcReferenceNo referenceNo = referenceNoDao.findByCode(code);
            Integer oldValue = referenceNo.getCurrentValue();
            Integer newValue = referenceNo.getCurrentValue() + referenceNo.getIncrementValue();

            // update
            referenceNo.setCurrentValue(newValue);
            referenceNoDao.save(referenceNo, securityService.getCurrentUser());
            sessionFactory.getCurrentSession().flush();
            NumberFormat numberFormat = new DecimalFormat(referenceNo.getSequenceFormat());

            // format
            generatedRefNo = referenceNo.getPrefix() + numberFormat.format(oldValue);
        }
        return generatedRefNo;
    }

    public String generateFormattedReferenceNo(String code, Map<String, Object> map) {
        synchronized (this) {
            AcReferenceNo referenceNo = referenceNoDao.findByCode(code);
    	    LOG.debug("test referenceNo generate : {}", referenceNo);
            // get old and new value
            Integer oldValue = referenceNo.getCurrentValue();
            Integer newValue = referenceNo.getCurrentValue() + referenceNo.getIncrementValue();
            LOG.debug("referenceNo, securityService.getCurrentUser() :" + securityService.getCurrentUser());
            // update
            referenceNo.setCurrentValue(newValue);
            referenceNoDao.save(referenceNo, securityService.getCurrentUser());
            sessionFactory.getCurrentSession().flush();

            Date now = new Date();
            NumberFormat numberFormat = new DecimalFormat(referenceNo.getSequenceFormat());
            SpelParserConfiguration configuration = new SpelParserConfiguration(true, true);
            StandardEvaluationContext context = new StandardEvaluationContext(configuration);
            ParserContext templateContext = new TemplateParserContext("{", "}");
            context.setVariable("a", referenceNo.getPrefix());
            context.setVariable("b", LONG_YEAR_FORMAT.format(now));
            context.setVariable("c", SHORT_YEAR_FORMAT.format(now));
            context.setVariable("d", LONG_MONTH_FORMAT.format(now));
            context.setVariable("e", SHORT_MONTH_FORMAT.format(now));
            context.setVariable("f", LONG_DAY_FORMAT.format(now));
            context.setVariable("g", SHORT_DAY_FORMAT.format(now));
            context.setVariable("h", LONG_HOUR_FORMAT.format(now));
            context.setVariable("i", SHORT_HOUR_FORMAT.format(now));
            context.setVariable("j", numberFormat.format(oldValue));

            for (String key : map.keySet()) {
                context.setVariable(key, map.get(key));
            }

            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(referenceNo.getReferenceFormat(), templateContext);
            return expression.getValue(context, String.class);
        }
    }


    //====================================================================================================
    // CONFIGURATION
    //====================================================================================================

    @Override
    public AcConfiguration findConfigurationById(Long id) {
        return configurationDao.findById(id);
    }

    @Override
    public AcConfiguration findConfigurationByKey(String key) {
        return configurationDao.findByKey(key);
    }

    @Override
    public List<AcConfiguration> findConfigurationsByPrefix(String prefix) {
        return configurationDao.findByPrefix(prefix);
    }

    @Override
    public List<AcConfiguration> findConfigurations() {
        return configurationDao.find();
    }

    @Override
    public List<AcConfiguration> findConfigurations(Integer offset, Integer limit) {
        return configurationDao.find(offset, limit);
    }

    @Override
    public List<AcConfiguration> findConfigurations(String filter) {
        return configurationDao.find(filter);
    }

    @Override
    public List<AcConfiguration> findConfigurations(String filter, Integer offset, Integer limit) {
        return configurationDao.find(filter, offset, limit);
    }


    @Override
    public Integer countConfiguration() {
        return configurationDao.count();
    }

    @Override
    public Integer countConfiguration(String filter) {
        return configurationDao.count(filter);
    }

    @Override
    public void saveConfiguration(AcConfiguration config) {
        configurationDao.save(config, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateConfiguration(AcConfiguration config) {
        configurationDao.update(config, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeConfiguration(AcConfiguration config) {
        configurationDao.remove(config, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    //====================================================================================================
    // EMAIL TEMPLATE
    //====================================================================================================

    @Override
    public AcEmailTemplate findEmailTemplateById(Long id) {
        return emailTemplateDao.findById(id);
    }

    @Override
    public AcEmailTemplate findEmailTemplateByCode(String code) {
        return emailTemplateDao.findByCode(code);
    }

    @Override
    public List<AcEmailTemplate> findEmailTemplates() {
        return emailTemplateDao.find();
    }

    @Override
    public List<AcEmailTemplate> findEmailTemplates(Integer offset, Integer limit) {
        return emailTemplateDao.find(offset, limit);
    }

    @Override
    public List<AcEmailTemplate> findEmailTemplates(String filter) {
        return emailTemplateDao.find(filter);
    }

    @Override
    public List<AcEmailTemplate> findEmailTemplates(String filter, Integer offset, Integer limit) {
        return emailTemplateDao.find(filter, offset, limit);
    }

    @Override
    public Integer countEmailTemplate() {
        return emailTemplateDao.count();
    }

    @Override
    public Integer countEmailTemplate(String filter) {
        return emailTemplateDao.count(filter);
    }

    @Override
    public void saveEmailTemplate(AcEmailTemplate template) {
        emailTemplateDao.save(template, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateEmailTemplate(AcEmailTemplate template) {
        emailTemplateDao.update(template, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeEmailTemplate(AcEmailTemplate template) {
        emailTemplateDao.remove(template, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void sendWithAttachment(String email, String s, String s1, String s2, String s3, HashMap<String, Object> vars) {

    }

    //====================================================================================================
    // EMAIL QUEUE
    //====================================================================================================

    @Override
    public List<AcEmailQueue> findEmailQueues() {
        return emailQueueDao.find();
    }

    @Override
    public List<AcEmailQueue> findEmailQueues(AcEmailQueueStatus status) {
        return emailQueueDao.find(status);
    }

    @Override
    public List<AcEmailQueue> findEmailQueues(AcEmailQueueStatus status, Integer offset, Integer limit) {
        return emailQueueDao.find(status); // todo(uda): limit offset
    }

    @Override
    public Integer countEmailQueue() {
        return emailQueueDao.count();
    }

    @Override
    public void saveEmailQueue(AcEmailQueue emailQueue) {
        emailQueueDao.save(emailQueue, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateEmailQueue(AcEmailQueue emailQueue) {
        emailQueueDao.update(emailQueue, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
}
