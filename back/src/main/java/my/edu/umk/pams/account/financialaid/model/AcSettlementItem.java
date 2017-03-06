package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAccount;

/**
 * @author PAMS
 */
public interface AcSettlementItem extends AcSettlement {


    Long getId();

    void setId(Long id);

    AcSettlement getSettlement();

    void setSettlement(AcSettlement settlement);

    AcAccount getAccount();

    void setAccount(AcAccount account);
}
