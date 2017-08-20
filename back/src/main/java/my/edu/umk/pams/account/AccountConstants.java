package my.edu.umk.pams.account;

/**
 * @author PAMS
 */
public interface AccountConstants {

    // id
    public static final String INVOICE_ID = "invoiceId";
    public static final String RECEIPT_ID = "receiptId";
    public static final String VOUCHER_ID = "voucherId";
    public static final String DEBIT_NOTE_ID = "debitNoteId";
    public static final String CREDIT_NOTE_ID = "creditNoteId";
    public static final String WAIVER_APPLICATION_ID = "waiverApplicationId";
    public static final String REFUND_APPLICATION_ID = "refundApplicationId";
    public static final String SHORT_TERM_LOAN_ID = "shortTermLoanId"; 
    public static final String ADVANCE_PAYMENT_ID = "advancePaymentId";
    public static final String KNOCKOFF_ID ="knockoffId";

    public static final String PROMO_CODE_REFERENCE_NO = "AcPromoCode.referenceNo";
    public static final String SETTLEMENT_REFERENCE_NO = "AcSettlement.referenceNo";
    public static final String INVOICE_REFERENCE_NO = "AcInvoice.referenceNo";
    public static final String INVOICE_INVOICE_NO = "AcInvoice.invoiceNo";
    public static final String RECEIPT_REFERENCE_NO = "AcReceipt.referenceNo";
    public static final String RECEIPT_RECEIPT_NO = "AcReceipt.receiptNo";
    public static final String DEBIT_NOTE_REFERENCE_NO = "AcDebitNote.referenceNo";
    public static final String CREDIT_NOTE_REFERENCE_NO = "AcCreditNote.referenceNo";
    public static final String WAIVER_APPLICATION_REFERENCE_NO = "AcWaiverApplication.referenceNo";
    public static final String SHORT_TERM_LOAN_REFERENCE_NO = "AcShortTermLoan.referenceNo";
    public static final String ACCOUNT_CHARGE_REFRENCE_NO = "AcAccountCharge.referenceNo";
    public static final String ADVANCE_PAYMENT_REFRENCE_NO = "AcAdvancePayment.referenceNo";
    public static final String KNOCKOFF_REFRENCE_NO = "AcKnockoff.referenceNo";

    public static final String INVOICE_PROCESS_KEY = "invoice_workflow";
    public static final String INVOICE_RESOURCE_PATH = "invoice.bpmn20.xml";
    public static final String INVOICE_PROCESS_NAME = "invoice";

    public static final String RECEIPT_PROCESS_KEY = "receipt_workflow";
    public static final String RECEIPT_RESOURCE_PATH = "receipt.bpmn20.xml";
    public static final String RECEIPT_PROCESS_NAME = "receipt";

    public static final String DEBIT_NOTE_PROCESS_KEY = "debit_note_workflow";
    public static final String DEBIT_NOTE_RESOURCE_PATH = "debit_note.bpmn20.xml";
    public static final String DEBIT_NOTE_PROCESS_NAME = "debit_note";

    public static final String CREDIT_NOTE_PROCESS_KEY = "credit_note_workflow";
    public static final String CREDIT_NOTE_RESOURCE_PATH = "credit_note.bpmn20.xml";
    public static final String CREDIT_NOTE_PROCESS_NAME = "credit_note";

    public static final String WAIVER_APPLICATION_PROCESS_KEY = "waiver_application_workflow";
    public static final String WAIVER_APPLICATION_RESOURCE_PATH = "waiver_application.bpmn20.xml";
    public static final String WAIVER_APPLICATION_PROCESS_NAME = "waiver_application";

    public static final String SHORT_TERM_LOAN_PROCESS_KEY = "short_term_loan_workflow";
    public static final String SHORT_TERM_LOAN_RESOURCE_PATH = "short_term_loan.bpmn20.xml";
    public static final String SHORT_TERM_LOAN_PROCESS_NAME = "short_term_loan";
    
    public static final String LAST_ENROLLMENT_DATE = "account.last.enrollmentDate";
    
    public static final String KNOCKOFF_PROCESS_NAME = "knockoff";

}
