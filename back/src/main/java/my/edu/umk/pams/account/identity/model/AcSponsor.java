package my.edu.umk.pams.account.identity.model;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcSponsor extends AcActor {

    String getCode();

    void setCode(String code);

    List<AcCoverage> getCoverages();

    void setCoverages(List<AcCoverage> coverages);
    
	AcSponsorType getSponsorType();

	void setAcSponsorType(AcSponsorType sponsorType);
}
