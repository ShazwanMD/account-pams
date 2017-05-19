package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcActor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcAccount extends AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    AcActor getActor();

    void setActor(AcActor actor);

    List<AcAccountCharge> getCharges();

    void setCharges(List<AcAccountCharge> charges);

    List<AcAccountWaiver> getWaivers();

    void setWaivers(List<AcAccountWaiver> waiver);

    List<AcAccountTransaction> getTransactions();

    void setTransactions(List<AcAccountTransaction> transactions);


    // transient
    // todo: debit, credit, balance

    BigDecimal getBalance();

    void setBalance(BigDecimal balance);

    BigDecimal getEffectiveBalance();

    void setEffectiveBalance(BigDecimal effectiveBalance);
}
