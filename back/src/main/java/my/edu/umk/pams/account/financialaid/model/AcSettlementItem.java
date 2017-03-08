package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcStudent;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcSettlementItem extends AcMetaObject {

    void setStatus(AcSettlementStatus status);

    AcSettlementStatus getStatus();

    AcAccount getAccount();

    void setAccount(AcAccount account);

    BigDecimal getBalanceAmount();

    void setBalanceAmount(BigDecimal balanceAmount);

    AcSettlement getSettlement();

    void setSettlement(AcSettlement settlement);

    AcStudent getStudent();

    void setStudent(AcStudent student);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
