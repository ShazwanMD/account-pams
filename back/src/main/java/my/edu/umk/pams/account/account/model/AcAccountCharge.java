package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcAccountCharge extends AcMetaObject{

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    AcAccountChargeType getChargeType();
    
    AcAccountChargeType getChargeType(String i);

    void setChargeType(AcAccountChargeType chargeType);

    AcAccount getAccount();

    void setAccount(AcAccount account);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
