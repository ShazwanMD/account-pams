package my.edu.umk.pams.account.system.service;

import my.edu.umk.pams.account.system.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface SystemService {

    //====================================================================================================
    // MODULE SUB MODULE
    //====================================================================================================

    AcModule findModuleById(Long id);

    AcSubModule findSubModuleById(Long id);

    AcModule findModuleByCode(String code);

    AcSubModule findSubModuleByCode(String code);

    List<AcModule> findModules();

    List<AcModule> findModules(boolean authorized);

    List<AcModule> findModules(Integer offset, Integer limit);

    List<AcModule> findModules(boolean authorized, Integer offset, Integer limit);

    List<AcSubModule> findSubModules();

    List<AcSubModule> findSubModules(boolean authorized);

    List<AcSubModule> findSubModules(Integer offset, Integer limit);

    List<AcSubModule> findSubModules(AcModule module, Integer offset, Integer limit);

    Integer countModule();

    Integer countSubModule();

    Integer countSubModule(AcModule module);

    void saveModule(AcModule module);

    void updateModule(AcModule module);

    void addSubModule(AcModule module, AcSubModule subModule);

    void updateSubModule(AcModule module, AcSubModule subModule);

    //====================================================================================================
    // AUDIT
    //====================================================================================================

    AcAudit findAuditById(Long id);

    List<AcAudit> findAudits(Integer offset, Integer limit);

    List<AcAudit> findAudits(String filter, Integer offset, Integer limit);

    Integer countAudit();

    Integer countAudit(String filter);

    //====================================================================================================
    // REFERENCE NO
    //====================================================================================================

    AcReferenceNo findReferenceNoById(Long id);

    AcReferenceNo findReferenceNoByCode(String code);

    List<AcReferenceNo> findReferenceNos(Integer offset, Integer limit);

    List<AcReferenceNo> findReferenceNos(String filter, Integer offset, Integer limit);

    Integer countReferenceNo();

    Integer countReferenceNo(String filter);

    String generateReferenceNo(String code);

    String generateFormattedReferenceNo(String code, Map<String, Object> map);

    //====================================================================================================
    // CONFIGURATION
    //====================================================================================================

    AcConfiguration findConfigurationById(Long id);

    AcConfiguration findConfigurationByKey(String key);

    List<AcConfiguration> findConfigurationsByPrefix(String prefix);

    List<AcConfiguration> findConfigurations();

    List<AcConfiguration> findConfigurations(Integer offset, Integer limit);

    List<AcConfiguration> findConfigurations(String filter);

    List<AcConfiguration> findConfigurations(String filter, Integer offset, Integer limit);

    Integer countConfiguration();

    Integer countConfiguration(String filter);

    void saveConfiguration(AcConfiguration config);

    void updateConfiguration(AcConfiguration config);

    void removeConfiguration(AcConfiguration config);

    //====================================================================================================
    // EMAIL
    //====================================================================================================

    AcEmailTemplate findEmailTemplateById(Long id);

    AcEmailTemplate findEmailTemplateByCode(String code);

    List<AcEmailTemplate> findEmailTemplates();

    List<AcEmailTemplate> findEmailTemplates(Integer offset, Integer limit);

    List<AcEmailTemplate> findEmailTemplates(String filter);

    List<AcEmailTemplate> findEmailTemplates(String filter, Integer offset, Integer limit);

    Integer countEmailTemplate();

    Integer countEmailTemplate(String filter);

    void saveEmailTemplate(AcEmailTemplate config);

    void updateEmailTemplate(AcEmailTemplate config);

    void removeEmailTemplate(AcEmailTemplate config);

    // TODO:
    void sendWithAttachment(String email, String s, String s1, String s2, String s3, HashMap<String, Object> vars);


    //====================================================================================================
    // EMAIL QUEUE
    //====================================================================================================

    List<AcEmailQueue> findEmailQueues();

    List<AcEmailQueue> findEmailQueues(AcEmailQueueStatus status);

    List<AcEmailQueue> findEmailQueues(AcEmailQueueStatus status, Integer offset, Integer limit);

    Integer countEmailQueue();

    void saveEmailQueue(AcEmailQueue emailQueue);

    void updateEmailQueue(AcEmailQueue emailQueue);

    void sendEmail();
}
