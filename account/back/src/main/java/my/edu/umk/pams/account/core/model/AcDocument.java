package my.edu.umk.pams.account.core.model;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcDocument extends my.edu.umk.pams.account.core.AcFlowObject {

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getAuditNo();

    void setAuditNo(String auditNo);

    String getDescription();

    void setDescription(String description);

    String getRemoveComment();

    void setRemoveComment(String removeComment);

    String getCancelComment();

    void setCancelComment(String cancelComment);
}
