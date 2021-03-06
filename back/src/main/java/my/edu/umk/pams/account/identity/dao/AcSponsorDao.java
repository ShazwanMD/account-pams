package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcSponsorDao extends GenericDao<Long, AcSponsor> {

    AcSponsor findBySponsorNo(String sponsorNo);

    AcCoverage findCoverageById(Long id);

    AcSponsorship findSponsorshipById(Long id);

    List<AcSponsor> find(String filter, Integer offset, Integer limit);

    List<AcCoverage> findCoverages(AcSponsor sponsor);

    List<AcSponsorship> findSponsorships(AcSponsor sponsor);

    Integer countCoverage(AcSponsor sponsor);

    Integer countSponsorship(AcSponsor sponsor);

    boolean hasSponsorship(AcSponsor sponsor);

    boolean hasCoverage(AcSponsor sponsor);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user);

    void deleteCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user);

    void addSponsorship(AcSponsor sponsor, AcSponsorship sponsorship, AcUser user);

    void removeSponsorship(AcSponsor sponsor, AcSponsorship sponsorship, AcUser user);

}
