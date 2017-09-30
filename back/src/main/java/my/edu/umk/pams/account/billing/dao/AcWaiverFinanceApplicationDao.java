package my.edu.umk.pams.account.billing.dao;

import java.math.BigDecimal;
import java.util.List;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptAccountCharge;
import my.edu.umk.pams.account.billing.model.AcReceiptDebitNote;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcWaiverAccountCharge;
import my.edu.umk.pams.account.billing.model.AcWaiverDebitNote;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverInvoice;
import my.edu.umk.pams.account.billing.model.AcWaiverItem;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcWaiverFinanceApplicationDao extends GenericDao<Long, AcWaiverFinanceApplication> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
	
	AcWaiverFinanceApplication findByReferenceNo(String referenceNo);
	
	AcWaiverItem findWaiverItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcWaiverFinanceApplication waiver);

	AcWaiverItem findWaiverItemByCharge(AcAccountCharge charge, AcWaiverFinanceApplication waiver);
	
	AcWaiverItem findWaiverItemByDebitNote(AcDebitNote debitNote, AcWaiverFinanceApplication waiver);
	
	List<AcWaiverFinanceApplication> find(AcAcademicSession academicSession, Integer offset, Integer limit);
	
	List<AcWaiverFinanceApplication> findByFlowState(AcFlowState acFlowState);
	
	List<AcWaiverFinanceApplication> findByFlowStates(AcFlowState... acFlowState);
	
    List<AcWaiverItem> findItems(AcWaiverFinanceApplication waiver);
    
    List<AcWaiverItem> findItems(AcWaiverFinanceApplication waiver, AcInvoice invoice);
    
    List<AcWaiverInvoice> findWaivers(AcWaiverFinanceApplication waiver);
    
    List<AcWaiverAccountCharge> findWaiverAccountCharge(AcWaiverFinanceApplication waiver);
    
	List<AcWaiverDebitNote> findWaiverDebitNote(AcWaiverFinanceApplication waiver);
	
	void addWaiverInvoice(AcWaiverFinanceApplication waiver, AcInvoice invoice, AcUser user);
	
	void addWaiverAccountCharge(AcWaiverFinanceApplication waiver, AcAccountCharge accountCharge, AcUser user);
	
	void addWaiverDebitNote(AcWaiverFinanceApplication waiver, AcDebitNote debitNote, AcUser user);
	
	void addWaiverItem(AcWaiverFinanceApplication waiver, AcWaiverItem item, AcUser user);
	
	void updateItem(AcWaiverFinanceApplication waiver, AcWaiverItem waiverItem, AcUser user);
	
	BigDecimal sumAppliedAmount(AcWaiverFinanceApplication waiver, AcUser user);
	
	BigDecimal sumAppliedAmount(AcInvoice invoice, AcWaiverFinanceApplication waiver, AcUser user);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAcademicSession academicSession);
    
    BigDecimal sumAmount(AcInvoice invoice, AcWaiverFinanceApplication waiver, AcUser user);
    
    BigDecimal sumTotalAmount(AcWaiverFinanceApplication waiver, AcAccountCharge accountCharge, AcUser user);
    
    BigDecimal sumTotalAmount(AcWaiverFinanceApplication waiver, AcDebitNote debitNote, AcInvoice invoice, AcUser user);
    
    BigDecimal sumTotalAmount(AcWaiverFinanceApplication waiver, AcDebitNote debitNote, AcUser user);

	

	

}