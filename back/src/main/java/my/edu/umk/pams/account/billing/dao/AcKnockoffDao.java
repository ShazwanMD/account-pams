package my.edu.umk.pams.account.billing.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountCharge;
import my.edu.umk.pams.account.billing.model.AcKnockoffAccountChargeImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffDebitNote;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoice;
import my.edu.umk.pams.account.billing.model.AcKnockoffInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcKnockoffItem;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptAccountCharge;
import my.edu.umk.pams.account.billing.model.AcReceiptDebitNote;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoice;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcKnockoffDao extends GenericDao<Long, AcKnockoff> {

	AcKnockoffItem findItemById(Long id);
	
    AcKnockoffInvoice findItemInvoiceById(Long id);
    
    AcKnockoffAccountCharge findItemAccChargeById(Long id);
    
    AcKnockoffDebitNote findItemDebitNoteById(Long id);
	
	AcKnockoff findByReferenceNo(String referenceNo);
	
	AcKnockoffItem findKnockoffItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcKnockoff knockoff);
	
	AcKnockoffItem findKnockoffItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcDebitNote debitNote, AcKnockoff knockoff);
	
	AcKnockoffItem findKnockoffItemByChare(AcAccountCharge charge, AcKnockoff knockoff);
	
	AcKnockoffItem findKnockoffItemByDebitNote(AcDebitNote debitNote, AcKnockoff knockoff);
	
	List<AcKnockoffItem> findInvoiceKnockoffItem(AcInvoice invoice, AcKnockoff knockoff);
	
	List<AcKnockoffItem> findDebitKnockoffItem(AcDebitNote debitNote, AcKnockoff knockoff);
	
	List<AcKnockoff> find(String filter, Integer offset, Integer limit);
	
    List<AcKnockoff> findByFlowState(AcFlowState flowState);

    List<AcKnockoff> findByFlowStates(AcFlowState... flowStates);
    
    List<AcKnockoffItem> findItems(AcKnockoff knockoff);
    
    List<AcKnockoffItem> findItems(AcKnockoff knockoff, AcInvoice invoice);
    
    List<AcKnockoffItem> findItems(AcKnockoff knockoff, AcDebitNote debitNote);
    
    List<AcKnockoffInvoice> find(AcKnockoff knockoff);
    
    List<AcKnockoffAccountCharge> findAccountCharge(AcKnockoff knockoff);
    
    List<AcKnockoffDebitNote> findAccountDebitNote(AcKnockoff knockoff);
	
	boolean hasKnockoff(AcKnockoff knockoff);
	
	boolean hasKnockoff(AcKnockoff knockoff, AcInvoice invoice);
	
    void addItem(AcKnockoff knockoff, AcKnockoffItem item, AcUser user);

    void updateKnockoff(AcKnockoff knockoff, AcUser user);

    void removeKnockoff(AcKnockoff knockoff, AcUser user);
    
    void addKnockoffInvoice(AcKnockoff knockoff, AcInvoice invoice, AcUser user);
    
    void addKnockoffAccountCharge(AcKnockoff knockoff, AcAccountCharge accountCharge, AcUser user);
    
    void addKnockoffDebitNote(AcKnockoff knockoff, AcDebitNote debitNote, AcUser user);
    
    void updateItem(AcKnockoff knockoff, AcKnockoffItem item, AcUser user);
    
    void deleteKnockoffInvoice(AcKnockoffInvoice knockoffInvoice, AcUser user);
    
    void deleteKnockoffAccCharge(AcKnockoffAccountCharge knockoffAccCharge, AcUser user);
    
    void deleteKnockoffDebitNote(AcKnockoffDebitNote knockoffDebitNote, AcUser user);
    
    void deleteItem(AcKnockoff knockoff, AcKnockoffItem item, AcUser user);
    
    BigDecimal sumAppliedAmount(AcKnockoff knockoff, AcUser user);
    
    BigDecimal sumAmount(AcInvoice invoice, AcKnockoff knockoff, AcUser user);
    
    BigDecimal sumAmount(AcDebitNote debitNote, AcKnockoff knockoff, AcUser user);
    
    BigDecimal sumTotalAmount(AcKnockoff knockoff, AcAccountCharge accountCharge, AcUser user);
    
    BigDecimal sumTotalAmount(AcDebitNote debitNote, AcKnockoff knockoff, AcUser user);

	boolean hasChargeKnockoffItem(AcAccountCharge accountCharge, AcKnockoff knockoff);

	boolean hasDebitKnockoffItem(AcDebitNote debitNote, AcKnockoff knockoff);
	
	Integer countItem(AcKnockoff knockoff);
}
