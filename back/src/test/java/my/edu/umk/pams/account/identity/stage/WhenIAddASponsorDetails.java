package my.edu.umk.pams.account.identity.stage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcCoverageImpl;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIAddASponsorDetails extends Stage<WhenIAddASponsorDetails> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIAddASponsorDetails.class);

	@Autowired
	private IdentityService identityService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private SessionFactory sessionFactory;

	@ProvidedScenarioState
	AcSponsor sponsor;

	@ProvidedScenarioState
	AcCoverage coverage;

	@ProvidedScenarioState
	Long coverageId;

	public WhenIAddASponsorDetails() {
		coverage = new AcCoverageImpl();
	}

	@As("I add sponsor coverage")
	public WhenIAddASponsorDetails I_add_sponsor_coverage$(Long id) {

		sponsor = identityService.findSponsorById(id);

		coverage.setSponsor(sponsor);
		coverage.setChargeCode(accountService.findChargeCodeById((long) 7));

		identityService.addCoverage(sponsor, coverage);

		Session session = sessionFactory.getCurrentSession();
		
		session.flush();

		coverageId = (Long) session.save(coverage);
		// final String entityName = coverage.getClass().getSimpleName();
		// Assert.notNull(coverageId, entityName + " must have Id");
		//
		return self();
	}
}
