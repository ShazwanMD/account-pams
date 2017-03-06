package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.identity.model.AcActor;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcAccount {


    Long getId();

    void setId(Long id);

    AcActor getActor();

    void setActor(AcActor actor);

    List<AcAccountTransaction> getTransactions();

    void setTransactions(List<AcAccountTransaction> transactions);

    List<AcAccountCharge> getCharges();

    void setCharges(List<AcAccountCharge> studentCharges);
}
