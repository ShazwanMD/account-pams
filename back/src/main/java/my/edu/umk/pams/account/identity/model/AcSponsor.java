package my.edu.umk.pams.account.identity.model;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcSponsor extends AcActor {

    String getSponsorNo();

    void setSponsorNo(String sponsorNo);

    AcSponsorType getSponsorType();

    void setSponsorType(AcSponsorType sponsorType);

    List<AcCoverage> getCoverages();

    void setCoverages(List<AcCoverage> coverages);
}
