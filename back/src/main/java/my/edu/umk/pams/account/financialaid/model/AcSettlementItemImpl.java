package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAccount;

public class AcSettlementItemImpl extends AcSettlementImpl implements AcSettlementItem {

    public AcSettlement settlement;
    public AcAccount account;

    public AcSettlement getSettlement() {
        return settlement;
    }

    public void setSettlement(AcSettlement settlement) {
        this.settlement = settlement;
    }

    public AcAccount getAccount() {
        return account;
    }

    public void setAccount(AcAccount account) {
        this.account = account;
    }
}
