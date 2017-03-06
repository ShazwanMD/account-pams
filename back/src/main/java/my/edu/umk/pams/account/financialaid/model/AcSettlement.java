package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.identity.model.AcSponsor;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcSettlement {


    Long getId();

    void setId(Long id);

    AcSponsor getSponsor();

    void setSponsor(AcSponsor sponsor);

    List<AcSettlementItem> getItems();

    void setItems(List<AcSettlementItem> items);
}
