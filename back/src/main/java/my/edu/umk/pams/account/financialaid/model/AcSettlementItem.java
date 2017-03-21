package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcSettlementItem extends AcMetaObject {

    BigDecimal getBalanceAmount();

    void setBalanceAmount(BigDecimal balanceAmount);

    void setStatus(AcSettlementStatus status);

    AcSettlementStatus getStatus();

    AcAccount getAccount();

    void setAccount(AcAccount account);

    AcSettlement getSettlement();

    void setSettlement(AcSettlement settlement);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
