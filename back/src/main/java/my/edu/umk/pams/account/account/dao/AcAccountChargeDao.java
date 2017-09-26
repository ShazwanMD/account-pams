package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcFeeSchedule;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcAccountChargeDao extends GenericDao<Long, AcAccountCharge> {

	// ====================================================================================================
	// FINDER
	// ====================================================================================================

	AcAccountCharge findByReferenceNo(String referenceNo);

	AcAccountCharge findBySourceNo(String sourceNo);

	AcAccountCharge findByActor(AcActor actor);

	List<AcAccountCharge> find(AcAccountChargeType[] chargeType);

	List<AcAccountCharge> find(AcAcademicSession academicSession, AcAccountChargeType[] chargeType);

	List<AcAccountCharge> find(String filter, Integer offset, Integer limit);

	List<AcAccountCharge> find(AcActor actor, Integer offset, Integer limit);

	List<AcAccountCharge> find(AcActor actor, String filter, Integer offset, Integer limit);

	List<AcAccountCharge> find(AcAcademicSession academicSession, AcAccount account);

	List<AcAccountCharge> find(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit);

	List<AcAccountCharge> find(AcAccount account);

	List<AcAccountCharge> find(AcAccount account, AcAccountChargeType... types);

	List<AcAccountCharge> find(AcAccount account, Integer offset, Integer limit);

	List<AcAccountCharge> findAttached(AcAccount account);

	List<AcAccountCharge> findAttached(AcAcademicSession academicSession, AcAccount account);

	List<AcAccountCharge> findDetached(AcAccount account);

	List<AcAccountCharge> findDetached(AcAcademicSession academicSession, AcAccount account);
	
	List<AcAccountCharge> find(boolean paid, AcAccount account, Integer offset, Integer limit);

	// ====================================================================================================
	// HELPER
	// ====================================================================================================

	Integer count(String filter);

	Integer count(AcActor actor);
	
	Integer count(AcAccount account);

	Integer count(AcAcademicSession academicSession, AcAccount account);

	Integer countAttached(AcAccount account);

	Integer countAttached(AcAcademicSession academicSession, AcAccount account);

	Integer countDetached(AcAccount account);

	Integer countDetached(AcAcademicSession academicSession, AcAccount account);

	boolean isChargeExists(AcAccount account, String sourceNo);

	boolean isChargeExists(AcAccount account, AcAcademicSession academicSession, AcAccountChargeType chargeType);
}
