package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcSettlement extends AcMetaObject{

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getDescription();

    void setDescription(String description);

    boolean isExecuted();

    void setExecuted(boolean executed);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    List<AcSettlementItem> getItems();

    void setItems(List<AcSettlementItem> items);
}
