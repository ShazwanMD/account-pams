package my.edu.umk.pams.account.financialaid.model;

import java.util.List;

import my.edu.umk.pams.account.identity.model.AcSponsor;

public class AcSettlementImpl implements AcSettlement {

    private List<AcSettlementItem> items;

    private AcSponsor sponsor;

    public AcSponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(AcSponsor sponsor) {
        this.sponsor = sponsor;
    }

    public List<AcSettlementItem> getItems() {
        return items;
    }

    public void setItems(List<AcSettlementItem> items) {
        this.items = items;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setId(Long id) {
        // TODO Auto-generated method stub

    }
}
