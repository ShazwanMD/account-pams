package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcSponsor;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcSettlement extends AcMetaObject{

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    // todo(uda): sourceNo

    String getDescription();

    void setDescription(String description);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    @Deprecated
    AcSponsor getSponsor();

    @Deprecated
    void setSponsor(AcSponsor sponsor);

    List<AcSettlementItem> getItems();

    void setItems(List<AcSettlementItem> items);
}
