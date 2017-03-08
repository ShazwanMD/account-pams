package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcActor;

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

    void setCharges(List<AcAccountCharge> studentCharges);

    List<AcAccountTransaction> getTransactions();

    void setTransactions(List<AcAccountTransaction> transactions);


    // transient
    // todo: debit, credit, balance
}
