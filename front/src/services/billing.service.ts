import { DebitNote } from './../app/shared/model/billing/debit-note.interface';
import { ReceiptAccountCharge } from './../app/shared/model/billing/receipt-account-charge.interface';
import { ReceiptDebitNote } from './../app/shared/model/billing/receipt-debit_note.interface';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { InvoiceTask } from '../app/shared/model/billing/invoice-task.interface';
import { Invoice } from '../app/shared/model/billing/invoice.interface';
import { InvoiceItem } from '../app/shared/model/billing/invoice-item.interface';
import { ReceiptTask } from '../app/shared/model/billing/receipt-task.interface';
import { Receipt } from '../app/shared/model/billing/receipt.interface';
import { ReceiptItem } from '../app/shared/model/billing/receipt-item.interface';
import { CreditNote } from '../app/shared/model/billing/credit-note.interface';
import { CreditNoteTask } from '../app/shared/model/billing/credit-note-task.interface';
import { DebitNoteTask } from '../app/shared/model/billing/debit-note-task.interface';
import { DebitNoteItem } from '../app/shared/model/billing/debit-note-item.interface';
import { CreditNoteItem } from '../app/shared/model/billing/credit-note-item.interface';
import { Account } from '../app/shared/model/account/account.interface';
import { ReceiptInvoice } from '../app/shared/model/billing/receipt-invoice.interface';
import { AdvancePayment } from '../app/shared/model/billing/advance-payment.interface';
import { Knockoff } from '../app/shared/model/billing/knockoff.interface';
import { KnockoffTask } from '../app/shared/model/billing/knockoff-task.interface';
import { WaiverFinanceApplication } from "../app/shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationTask } from "../app/shared/model/billing/waiver-finance-application-task.interface";
import { RefundPayment } from '../app/shared/model/billing/refund-payment.interface';
import { RefundPaymentTask } from '../app/shared/model/billing/refund-payment-task.interface';
import { KnockoffInvoice } from "../app/shared/model/billing/knockoff-invoice.interface";
import { KnockoffAccountCharge } from "../app/shared/model/billing/knockoff-account-charge.interface";
import { KnockoffItem } from "../app/shared/model/billing/knockoff-item.interface";
import { WaiverInvoice } from "../app/shared/model/billing/waiver-invoice.interface";
import { WaiverDebitNote } from "../app/shared/model/billing/waiver-debit-note.interface";
import { WaiverAccountCharge } from "../app/shared/model/billing/waiver-account-charge.interface";
import { WaiverItem } from "../app/shared/model/billing/waiver-item.interface";
import { AccountCharge } from "../app/shared/model/account/account-charge.interface";
import { KnockoffDebitNote } from "../app/shared/model/billing/knockoff-debit-note.interface";

@Injectable()
export class BillingService {

    private BILLING_API: string = environment.endpoint + '/api/billing';

    constructor( private _http: HttpInterceptorService ) {
    }

    // ====================================================================================================
    // INVOICE
    // ====================================================================================================

    findCompletedInvoices(): Observable<Invoice[]> {
        console.log( 'findCompletedInvoices' );
        return this._http.get( this.BILLING_API + '/invoices/state/COMPLETED' )
            .map(( res: Response ) => <Invoice[]>res.json() );
    }

    findCancelInvoices(): Observable<Invoice[]> {
        console.log( 'findCancelInvoices' );
        return this._http.get( this.BILLING_API + '/invoices/state/CANCELLED' )
            .map(( res: Response ) => <Invoice[]>res.json() );
    }

    findUnpaidInvoices( account: Account ): Observable<Invoice[]> {
        console.log( 'findUnpaidInvoices' );
        return this._http.get( this.BILLING_API + '/invoices/unpaidInvoices/' + account.code )
            .map(( res: Response ) => <Invoice[]>res.json() );
    }

    // todo: this goes thru ACL
    findArchivedInvoices(): Observable<Invoice[]> {
        console.log( 'findArchivedInvoices' );
        return this._http.get( this.BILLING_API + '/invoices/archived' )
            .map(( res: Response ) => <Invoice[]>res.json() );
    }

    findAssignedInvoiceTasks(): Observable<InvoiceTask[]> {
        console.log( 'findAssignedInvoiceTasks' );
        return this._http.get( this.BILLING_API + '/invoices/assignedTasks' )
            .map(( res: Response ) => <InvoiceTask[]>res.json() );
    }

    findPooledInvoiceTasks(): Observable<InvoiceTask[]> {
        console.log( 'findPooledInvoiceTasks' );
        return this._http.get( this.BILLING_API + '/invoices/pooledTasks' )
            .map(( res: Response ) => <InvoiceTask[]>res.json() );
    }

    findInvoiceTaskByTaskId( taskId: string ): Observable<InvoiceTask> {
        console.log( 'findInvoiceTaskByTaskId' );
        return this._http.get( this.BILLING_API + '/invoices/viewTask/' + taskId )
            .map(( res: Response ) => <InvoiceTask>res.json() );
    }

    findInvoiceByReferenceNo( referenceNo: string ): Observable<Invoice> {
        return this._http.get( this.BILLING_API + '/invoices/' + referenceNo )
            .map(( res: Response ) => <Invoice>res.json() );
    }

    findInvoicesByAccount( code: string ): Observable<Invoice> {
        return this._http.get( this.BILLING_API + '/invoices/account/' + code )
            .map(( res: Response ) => <Invoice>res.json() );
    }

    findInvoiceByTaskId( taskId: string ): Observable<Invoice> {
        return this._http.get( this.BILLING_API + '/invoices/' + taskId )
            .map(( res: Response ) => <Invoice>res.json() );
    }

    findInvoiceItems( invoice: Invoice ): Observable<InvoiceItem[]> {
        console.log( 'findInvoiceItems' );
        return this._http.get( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems' )
            .map(( res: Response ) => <InvoiceItem[]>res.json() );
    }

    startInvoiceTask( invoice: Invoice ): Observable<String> {
        return this._http.post( this.BILLING_API + '/invoices/startTask', JSON.stringify( invoice ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    completeInvoiceTask( invoiceTask: InvoiceTask ): Observable<String> {
        console.log( 'TaskId: ' + invoiceTask.taskId );
        return this._http.post( this.BILLING_API + '/invoices/completeTask', JSON.stringify( invoiceTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    claimInvoiceTask( invoiceTask: InvoiceTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/invoices/claimTask', JSON.stringify( invoiceTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    releaseInvoiceTask( invoiceTask: InvoiceTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/invoices/releaseTask', JSON.stringify( invoiceTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    cancelInvoice( invoice: Invoice ): Observable<String> {
        return this._http.post( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/cancel', JSON.stringify( invoice ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    removeInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
        return this._http.post(this.BILLING_API + '/invoices/removeTask', JSON.stringify(invoiceTask))
            .flatMap((res: Response) => Observable.of(res.text()));
    }
    
    updateInvoice( invoice: Invoice ): Observable<String> {
        return this._http.put( this.BILLING_API + '/invoices', JSON.stringify( invoice ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    addInvoiceItem( invoice: Invoice, item: InvoiceItem ): Observable<String> {
        return this._http.post( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems', JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    updateInvoiceItem( invoice: Invoice, item: InvoiceItem ): Observable<String> {
        console.log( 'saving invoice item' + item.id );
        return this._http.put( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id, JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteInvoiceItem( invoice: Invoice, item: InvoiceItem ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    findDebitNotesByInvoice( invoice: Invoice ): Observable<DebitNote[]> {
        return this._http.get( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/debitNotes' )
            .map(( res: Response ) => <DebitNote[]>res.json() );
    }

    findCreditNotesByInvoice( invoice: Invoice ): Observable<CreditNote[]> {
        return this._http.get( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/creditNotes' )
            .map(( res: Response ) => <CreditNote[]>res.json() );
    }

    // ====================================================================================================
    // RECEIPT
    // ====================================================================================================

    findAssignedReceiptTasks(): Observable<ReceiptTask[]> {
        console.log( 'findAssignedReceiptTasks' );
        return this._http.get( this.BILLING_API + '/receipts/assignedTasks' )
            .map(( res: Response ) => <ReceiptTask[]>res.json() );
    }

    findPooledReceiptTasks(): Observable<ReceiptTask[]> {
        console.log( 'findPooledReceiptTasks' );
        return this._http.get( this.BILLING_API + '/receipts/pooledTasks' )
            .map(( res: Response ) => <ReceiptTask[]>res.json() );
    }

    findArchivedReceipts(): Observable<Receipt[]> {
        console.log( 'findArchivedReceipts' );
        return this._http.get( this.BILLING_API + '/receipts/archived' )
            .map(( res: Response ) => <Receipt[]>res.json() );
    }

    findReceiptTaskByTaskId( taskId: string ): Observable<ReceiptTask> {
        console.log( 'findReceiptTaskByTaskId' );
        return this._http.get( this.BILLING_API + '/receipts/viewTask/' + taskId )
            .map(( res: Response ) => <ReceiptTask>res.json() );
    }

    findReceiptByReferenceNo( referenceNo: string ): Observable<Receipt> {
        console.log( 'encoded uri: ' + encodeURI( referenceNo ) );
        return this._http.get( this.BILLING_API + '/receipts/' + encodeURI( referenceNo ) )
            .map(( res: Response ) => <Receipt>res.json() );
    }

    findReceiptByTaskId( taskId: string ): Observable<Receipt> {
        console.log( 'encoded uri: ' + encodeURI( taskId ) );
        return this._http.get( this.BILLING_API + '/receipts/' + encodeURI( taskId ) )
            .map(( res: Response ) => <Receipt>res.json() );
    }

    findReceiptItems( receipt: Receipt ): Observable<ReceiptItem[]> {
        console.log( 'findReceiptItems' );
        return this._http.get( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems' )
            .map(( res: Response ) => <ReceiptItem[]>res.json() );
    }
    
    findInvoiceReceiptItems( receipt: Receipt, invoice:Invoice ): Observable<ReceiptItem[]> {
        console.log( 'findReceiptItems' );
        return this._http.get( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/items/invoices/' + invoice.id )
            .map(( res: Response ) => <ReceiptItem[]>res.json() );
    }
    
    findDebitNoteReceiptItems( receipt: Receipt, debitNote:DebitNote ): Observable<ReceiptItem[]> {
        console.log( 'findReceiptItems' );
        return this._http.get( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/items/debitNotes/' + debitNote.id )
            .map(( res: Response ) => <ReceiptItem[]>res.json() );
    }

    startReceiptTask( receipt: Receipt ): Observable<String> {
        console.log( 'receipt: ' + receipt );
        return this._http.post( this.BILLING_API + '/receipts/startTask', JSON.stringify( receipt ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    completeReceiptTask( receiptTask: ReceiptTask ): Observable<String> {
        console.log( 'TaskId: ' + receiptTask.taskId );
        return this._http.post( this.BILLING_API + '/receipts/completeTask', JSON.stringify( receiptTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    claimReceiptTask( receiptTask: ReceiptTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/receipts/claimTask', JSON.stringify( receiptTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    releaseReceiptTask( receiptTask: ReceiptTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/receipts/releaseTask', JSON.stringify( receiptTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    removeReceiptTask(receiptTask: ReceiptTask): Observable<String> {
        return this._http.post(this.BILLING_API + '/receipts/removeTask', JSON.stringify(receiptTask))
            .flatMap((res: Response) => Observable.of(res.text()));
    }

    updateReceipt( receipt: Receipt ): Observable<String> {
        return this._http.put( this.BILLING_API + '/receipts/'  + receipt.referenceNo , JSON.stringify( receipt ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    addReceiptItem( receipt: Receipt, item: ReceiptItem ): Observable<String> {
        console.log("receipt " + receipt.referenceNo + " item")
        return this._http.post( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems', JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    updateReceiptItem( receipt: Receipt, item: ReceiptItem ) {
        console.log( 'saving receipt item' + item.id );
        return this._http.put( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id, JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteReceiptItem( receipt: Receipt, item: ReceiptItem ) {
        return this._http.delete( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    calculateChargeInvoice( receipt: Receipt ): Observable<String> {
        return this._http.post( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/account', null )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    addReceiptInvoiceItems( receipt: Receipt, invoice: Invoice ): Observable<String> {
        return this._http.post( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/invoice/' + invoice.id, JSON.stringify( invoice ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    findReceiptsByInvoice( receipt: Receipt ): Observable<ReceiptInvoice[]> {
        return this._http.get( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptInvoice' )
            .map(( res: Response ) => <ReceiptInvoice[]>res.json() );
    }

    findReceiptsByDebitNote( receipt: Receipt ): Observable<ReceiptDebitNote[]> {
        return this._http.get( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptDebitNote' )
            .map(( res: Response ) => <ReceiptDebitNote[]>res.json() );
    }

    findReceiptsByAccountCharge( receipt: Receipt ): Observable<ReceiptAccountCharge[]> {
        return this._http.get( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptAccountCharge' )
            .map(( res: Response ) => <ReceiptAccountCharge[]>res.json() );
    }
    
    itemToReceiptItem( invoice: Invoice, receipt: Receipt ): Observable<String> {
        return this._http.post( this.BILLING_API + '/invoices/' + invoice.referenceNo + '/receipts/' + receipt.id, JSON.stringify( receipt ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    debitItemToReceiptItem( debitNote: DebitNote, receipt: Receipt ): Observable<String> {
        return this._http.post( this.BILLING_API + '/receipts/' + debitNote.referenceNo + '/debitNotes/' + receipt.id, JSON.stringify( receipt ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    updateItemToReceipt( receipt: Receipt, item: ReceiptItem ) {
        return this._http.put( this.BILLING_API + '/receipts/updateReceiptItems/' + receipt.referenceNo + '/receiptItems/' + item.id, JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    addReceiptCharge( receipt: Receipt, charge: AccountCharge ): Observable<String> {
        return this._http.post( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/accountCharge/' + charge.id, JSON.stringify( charge ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    addReceiptDebitNote( receipt: Receipt, debitNote: DebitNote ): Observable<String> {
        return this._http.post( this.BILLING_API + '/receipts/' + receipt.referenceNo + '/debitNote/' + debitNote.id, JSON.stringify( debitNote ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    deleteReceiptInvoices( receiptInvoice: ReceiptInvoice ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/receiptInvoices/' + receiptInvoice.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteReceiptAccCharges( receiptAccountCharge: ReceiptAccountCharge ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/receiptAccCharges/' + receiptAccountCharge.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    deleteReceiptDebitNotes( receiptDebitNote: ReceiptDebitNote ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/receiptDebitNotes/' + receiptDebitNote.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    hasReceiptItem( receipt: Receipt, invoice: Invoice ): Observable<Boolean> {
        return this._http.get(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/invoices/' + invoice.id)
          .map((res: Response) => <Boolean>res.json());
      }
    
    checkReceipts( receipt: Receipt ): Observable<Boolean> {
        console.log("receipt " + receipt.referenceNo + " check receipt")
        return this._http.post(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/checkReceipt', JSON.stringify( receipt ))
        .map((res: Response) => <Boolean>res.json() );
      }
    // ====================================================================================================
    // CREDIT NOTE
    // ====================================================================================================

    findCreditNotesbyInvoice( invoice: Invoice ): Observable<CreditNote[]> {
        console.log( 'findDebitNotes' );
        return this._http.get( this.BILLING_API + '/invoice/' + invoice.referenceNo + '/creditNotes/' )
            .map(( res: Response ) => <CreditNote[]>res.json() );
    }

    findCompletedCreditNotes(): Observable<CreditNote[]> {
        console.log( 'findCompletedCreditNotes' );
        return this._http.get( this.BILLING_API + '/creditNotes/state/COMPLETED' )
            .map(( res: Response ) => <CreditNote[]>res.json() );
    }

    // todo: this goes thru ACL
    findArchivedCreditNotes(): Observable<CreditNote[]> {
        console.log( 'findArchivedCreditNotes' );
        return this._http.get( this.BILLING_API + '/creditNotes/archived' )
            .map(( res: Response ) => <CreditNote[]>res.json() );
    }

    findAssignedCreditNoteTasks(): Observable<CreditNoteTask[]> {
        console.log( 'findAssignedCreditNoteTasks' );
        return this._http.get( this.BILLING_API + '/creditNotes/assignedTasks' )
            .map(( res: Response ) => <CreditNoteTask[]>res.json() );
    }

    findPooledCreditNoteTasks(): Observable<CreditNoteTask[]> {
        console.log( 'findPooledCreditNoteTasks' );
        return this._http.get( this.BILLING_API + '/creditNotes/pooledTasks' )
            .map(( res: Response ) => <CreditNoteTask[]>res.json() );
    }

    findCreditNoteTaskByTaskId( taskId: string ): Observable<CreditNoteTask> {
        console.log( 'findCreditNoteTaskByTaskId' );
        return this._http.get( this.BILLING_API + '/creditNotes/viewTask/' + taskId )
            .map(( res: Response ) => <CreditNoteTask>res.json() );
    }

    findCreditNoteByReferenceNo( referenceNo: string ): Observable<CreditNote> {
        return this._http.get( this.BILLING_API + '/creditNotes/' + referenceNo )
            .map(( res: Response ) => <CreditNote>res.json() );
    }

    findCreditNoteByTaskId( taskId: string ): Observable<CreditNote> {
        return this._http.get( this.BILLING_API + '/creditNotes/' + taskId )
            .map(( res: Response ) => <CreditNote>res.json() );
    }

    findCreditNoteItems( creditNote: CreditNote ): Observable<CreditNoteItem[]> {
        console.log( 'findCreditNoteItems' );
        return this._http.get( this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems' )
            .map(( res: Response ) => <CreditNoteItem[]>res.json() );
    }

    startCreditNoteTask( creditNote: CreditNote ): Observable<String> {
        console.log( 'creditNote: ' + creditNote );
        return this._http.post( this.BILLING_API + '/creditNotes/startTask', JSON.stringify( creditNote ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    completeCreditNoteTask( creditNoteTask: CreditNoteTask ): Observable<String> {
        console.log( 'TaskId: ' + creditNoteTask.taskId );
        return this._http.post( this.BILLING_API + '/creditNotes/completeTask', JSON.stringify( creditNoteTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    claimCreditNoteTask( creditNoteTask: CreditNoteTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/creditNotes/claimTask', JSON.stringify( creditNoteTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    releaseCreditNoteTask( creditNoteTask: CreditNoteTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/creditNotes/releaseTask', JSON.stringify( creditNoteTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    removeCreditNoteTask(creditNoteTask: CreditNoteTask): Observable<String> {
        return this._http.post(this.BILLING_API + '/creditNotes/removeTask', JSON.stringify(creditNoteTask))
            .flatMap((res: Response) => Observable.of(res.text()));
    }

    updateCreditNote( creditNote: CreditNote ) {
        console.log( 'saving creditNote' + creditNote.id );
        return this._http.put( this.BILLING_API + '/creditNotes/' + creditNote.id, JSON.stringify( creditNote ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    addCreditNoteItem( creditNote: CreditNote, item: CreditNoteItem ): Observable<String> {
        return this._http.post( this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems', JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    updateCreditNoteItem( creditNote: CreditNote, item: CreditNoteItem ) {
        console.log( 'saving creditNote item' + item.id );
        return this._http.put( this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems/' + item.id, JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteCreditNoteItem( creditNote: CreditNote, item: CreditNoteItem ) {
        return this._http.delete( this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems/' + item.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    // ====================================================================================================
    // DEBIT NOTE
    // ====================================================================================================
    
    findDebitNotes(): Observable<DebitNote[]> {
    return this._http.get(this.BILLING_API + '/debitNotes')
      .map((res: Response) => <DebitNote[]>res.json());
   }
    
    findDebitNotesbyInvoice( invoice: Invoice ): Observable<DebitNote[]> {
        console.log( 'findDebitNotes' );
        return this._http.get( this.BILLING_API + '/invoice/' + invoice.referenceNo + '/debitNotes/' )
            .map(( res: Response ) => <DebitNote[]>res.json() );
    }

    findCompletedDebitNotes(): Observable<DebitNote[]> {
        console.log( 'findCompletedDebitNotes' );
        return this._http.get( this.BILLING_API + '/debitNotes/state/COMPLETED' )
            .map(( res: Response ) => <DebitNote[]>res.json() );
    }

    // todo: this goes thru ACL
    findArchivedDebitNotes(): Observable<DebitNote[]> {
        console.log( 'findArchivedDebitNotes' );
        return this._http.get( this.BILLING_API + '/debitNotes/archived' )
            .map(( res: Response ) => <DebitNote[]>res.json() );
    }

    findAssignedDebitNoteTasks(): Observable<DebitNoteTask[]> {
        console.log( 'findAssignedDebitNoteTasks' );
        return this._http.get( this.BILLING_API + '/debitNotes/assignedTasks' )
            .map(( res: Response ) => <DebitNoteTask[]>res.json() );
    }

    findPooledDebitNoteTasks(): Observable<DebitNoteTask[]> {
        console.log( 'findPooledDebitNoteTasks' );
        return this._http.get( this.BILLING_API + '/debitNotes/pooledTasks' )
            .map(( res: Response ) => <DebitNoteTask[]>res.json() );
    }

    findDebitNoteTaskByTaskId( taskId: string ): Observable<DebitNoteTask> {
        console.log( 'findDebitNoteTaskByTaskId' );
        return this._http.get( this.BILLING_API + '/debitNotes/viewTask/' + taskId )
            .map(( res: Response ) => <DebitNoteTask>res.json() );
    }

    findDebitNoteByReferenceNo( referenceNo: string ): Observable<DebitNote> {
        return this._http.get( this.BILLING_API + '/debitNotes/' + referenceNo )
            .map(( res: Response ) => <DebitNote>res.json() );
    }

    findDebitNoteByTaskId( taskId: string ): Observable<DebitNote> {
        return this._http.get( this.BILLING_API + '/debitNotes/' + taskId )
            .map(( res: Response ) => <DebitNote>res.json() );
    }

    // findReceiptItems(receipt: Receipt): Observable<ReceiptItem[]> {
    //   console.log("findReceiptItems");
    //   return this._http.get(this.BILLING_API + '/receipts/' + receipt.referenceNo + "/receiptItems")
    //     .map((res: Response) => <ReceiptItem[]>res.json());
    // }

    findDebitNoteItems( debitNote: DebitNote ): Observable<DebitNoteItem[]> {
        console.log( 'findDebitNoteItems' );
        return this._http.get( this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems' )
            .map(( res: Response ) => <DebitNoteItem[]>res.json() );
    }

    startDebitNoteTask( debitNote: DebitNote ): Observable<String> {
        console.log( 'debitNote: ' + debitNote );
        return this._http.post( this.BILLING_API + '/debitNotes/startTask', JSON.stringify( debitNote ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    completeDebitNoteTask( debitNoteTask: DebitNoteTask ): Observable<String> {
        console.log( 'TaskId: ' + debitNoteTask.taskId );
        return this._http.post( this.BILLING_API + '/debitNotes/completeTask', JSON.stringify( debitNoteTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    claimDebitNoteTask( debitNoteTask: DebitNoteTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/debitNotes/claimTask', JSON.stringify( debitNoteTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    releaseDebitNoteTask( debitNoteTask: DebitNoteTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/debitNotes/releaseTask', JSON.stringify( debitNoteTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    removeDebitNoteTask(debitNoteTask: DebitNoteTask): Observable<String> {
        return this._http.post(this.BILLING_API + '/debitnotes/removeTask', JSON.stringify(debitNoteTask))
            .flatMap((res: Response) => Observable.of(res.text()));
    }

    updateDebitNote( debitNote: DebitNote ) {
        console.log( 'saving creditNote' + debitNote.id );
        return this._http.put( this.BILLING_API + '/debitNotes/' + debitNote.id, JSON.stringify( debitNote ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    addDebitNoteItem( debitNote: DebitNote, item: DebitNoteItem ): Observable<String> {
        return this._http.post( this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems', JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    updateDebitNoteItem( debitNote: DebitNote, item: DebitNoteItem ) {
        console.log( 'saving debitNote item' + item.id );
        return this._http.put( this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems/' + item.id, JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteDebitNoteItem( debitNote: DebitNote, item: DebitNoteItem ) {
        return this._http.delete( this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems/' + item.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    findUnpaidDebitNotes( account: Account ): Observable<DebitNote[]> {
        console.log( 'findUnpaidDebitNotes service' + account.code);
        return this._http.get( this.BILLING_API + '/debitnotes/unpaidDebitNotes/' + account.code )
            .map(( res: Response ) => <DebitNote[]>res.json() );
    }

    // ====================================================================================================
    // KNOCKOFF & ADVANCE PAYMENT
    // ==================================================================================================== 

    findAdvancePayments(): Observable<AdvancePayment[]> {
        return this._http.get( this.BILLING_API + '/advancePayments' )
            .map(( res: Response ) => <AdvancePayment[]>res.json() );
    }

    findUnpaidAdvancePayments( account: Account ): Observable<AdvancePayment[]> {
        console.log( 'findUnpaidAdvancePayments' );
        return this._http.get( this.BILLING_API + '/advancePayments/unpaidInvoices/' + account.code )
            .map(( res: Response ) => <AdvancePayment[]>res.json() );
    }

    updateAdvancePayment( payment: AdvancePayment ) {
        return this._http.put( this.BILLING_API + '/advancePayments/' + payment.referenceNo, JSON.stringify( payment ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    findKnockoffs(): Observable<Knockoff[]> {
        return this._http.get( this.BILLING_API + '/knockoffs' )
            .map(( res: Response ) => <Knockoff[]>res.json() );
    }

    findKnockoffByReferenceNo( referenceNo: string ): Observable<Knockoff> {
        return this._http.get( this.BILLING_API + '/knockoffs/' + referenceNo )
            .map(( res: Response ) => <Knockoff>res.json() );
    }

    startKnockoffTask( knockoff: Knockoff): Observable<String> {
        console.log("payment dlm service front ends : " +knockoff);
        return this._http.post( this.BILLING_API + '/knockoffs/startTask', JSON.stringify( knockoff ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    completeKnockoffTask( knockoffTask: KnockoffTask ): Observable<String> {
        console.log( 'TaskId: ' + knockoffTask.taskId );
        return this._http.post( this.BILLING_API + '/knockoffs/completeTask', JSON.stringify( knockoffTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    claimKnockoffTask( knockoffTask: KnockoffTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/knockoffs/claimTask', JSON.stringify( knockoffTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    releaseKnockoffTask( knockoffTask: KnockoffTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/knockoffs/releaseTask', JSON.stringify( knockoffTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    removeKnockoffTask(knockoffTask: KnockoffTask): Observable<String> {
        return this._http.post(this.BILLING_API + '/knockoffs/removeTask', JSON.stringify(knockoffTask))
            .flatMap((res: Response) => Observable.of(res.text()));
    }

    findCompletedKnockoffs(): Observable<Knockoff[]> {
        console.log( 'findCompletedKnockoffs' );
        return this._http.get( this.BILLING_API + '/knockoffs/state/COMPLETED' )
            .map(( res: Response ) => <Knockoff[]>res.json() );
    }

    findCancelKnockoffs(): Observable<Knockoff[]> {
        console.log( 'findCancelInvoices' );
        return this._http.get( this.BILLING_API + '/knockoffs/state/CANCELLED' )
            .map(( res: Response ) => <Knockoff[]>res.json() );
    }

    findArchivedknockoffs(): Observable<Knockoff[]> {
        console.log( 'findArchivedknockoffs' );
        return this._http.get( this.BILLING_API + '/knockoffs/archived' )
            .map(( res: Response ) => <Knockoff[]>res.json() );
    }

    findAssignedKnockoffTasks(): Observable<KnockoffTask[]> {
        console.log( 'findAssignedKnockoffTasks front end services');
        return this._http.get( this.BILLING_API + '/knockoffs/assignedTasks' )
            .map((res: Response) => <KnockoffTask[]>res.json());
    }

    findPooledKnockoffTasks(): Observable<KnockoffTask[]> {
        console.log( 'findPooledKnockoffTasks');
        return this._http.get( this.BILLING_API + '/knockoffs/pooledTasks' )
            .map(( res: Response ) => <KnockoffTask[]>res.json() );
    }

    findKnockoffTaskByTaskId( taskId: string ): Observable<KnockoffTask> {
        console.log( 'findKnockoffTaskByTaskId' );
        return this._http.get( this.BILLING_API + '/knockoffs/viewTask/' + taskId )
            .map(( res: Response ) => <KnockoffTask>res.json() );
    }
    
    addKnockoffInvoice( knockoff: Knockoff, invoice: Invoice ): Observable<String> {
        return this._http.post( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/invoice/' + invoice.id, JSON.stringify( invoice ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    findKnockoffsByInvoice( knockoff: Knockoff ): Observable<KnockoffInvoice[]> {
        return this._http.get( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffInvoice' )
            .map(( res: Response ) => <KnockoffInvoice[]>res.json() );
    }
    
    addKnockoffAccountCharge( knockoff: Knockoff, accountCharge: AccountCharge ): Observable<String> {
        return this._http.post( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/accountCharge/' + accountCharge.id, JSON.stringify( accountCharge ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    findKnockoffsByAccountCharge( knockoff: Knockoff ): Observable<KnockoffAccountCharge[]> {
        return this._http.get( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffAccountCharge' )
            .map(( res: Response ) => <KnockoffAccountCharge[]>res.json() );
    }
    
    itemToKnockoffItem( invoice: Invoice, knockoff: Knockoff  ): Observable<String> {
        return this._http.post( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/invoices/' + invoice.id, JSON.stringify( invoice ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    debitToKnockoffItem( debitNote: DebitNote, knockoff: Knockoff  ): Observable<String> {
        return this._http.post( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/debitNotes/' + debitNote.id, JSON.stringify( debitNote ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    findKnockoffItems( knockoff: Knockoff ): Observable<KnockoffItem[]> {
        return this._http.get( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffItems' )
            .map(( res: Response ) => <KnockoffItem[]>res.json() );
    }
    
    findKnockoffItemsByInvoice( knockoff: Knockoff, invoice: Invoice ): Observable<KnockoffItem[]> {
        return this._http.get( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffItems/' +  invoice.id)
            .map(( res: Response ) => <KnockoffItem[]>res.json() );
    }
    
    findInvoiceKnockoffItems( knockoff: Knockoff, invoice:Invoice ): Observable<KnockoffItem[]> {
        console.log( 'findReceiptItems' );
        return this._http.get( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/items/invoices/' + invoice.id )
            .map(( res: Response ) => <KnockoffItem[]>res.json() );
    }
    
    findDebitKnockoffItems( knockoff: Knockoff, debitNote:DebitNote ): Observable<KnockoffItem[]> {
        console.log( 'findReceiptItems' );
        return this._http.get( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/items/debits/' + debitNote.id )
            .map(( res: Response ) => <KnockoffItem[]>res.json() );
    }
    
    addKnockoffDebitNote( knockoff: Knockoff, debitNote: DebitNote ): Observable<String> {
        return this._http.post( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/debitNote/' + debitNote.id, JSON.stringify( debitNote ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }    

    updateKnockoff( knockoff: Knockoff ): Observable<String> {
        return this._http.put( this.BILLING_API + '/knockoffs/'  + knockoff.referenceNo , JSON.stringify( knockoff ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    updateKnockoffItems( knockoff: Knockoff, item: KnockoffItem ) {
        //console.log( 'Knockoff servis ref no ' + knockoff.referenceNo );
        return this._http.put( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffItems/' + item.id, JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    findKnockoffsByDebitNote( knockoff: Knockoff ): Observable<KnockoffDebitNote[]> {
        return this._http.get( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffDebitNote' )
            .map(( res: Response ) => <KnockoffDebitNote[]>res.json() );
    }

    addKnockoffItem( knockoff: Knockoff, item: KnockoffItem ): Observable<String> {
        console.log("knockoff " + knockoff.referenceNo + " item")
        return this._http.post( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffItems', JSON.stringify( item ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    deleteKnockoffDebitNotes( knockoffDebitNote: KnockoffDebitNote ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/knockoffDebitNotes/' + knockoffDebitNote.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteKnockoffInvoices( knockoffInvoice: KnockoffInvoice ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/knockoffInvoices/' + knockoffInvoice.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteKnockoffAccCharges( knockoffAccountCharge: KnockoffAccountCharge ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/knockoffAccCharges/' + knockoffAccountCharge.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    deleteKnockoffItem( knockoff: Knockoff, item: KnockoffItem ): Observable<String> {
        return this._http.delete( this.BILLING_API + '/knockoffs/' + knockoff.referenceNo + '/knockoffItems/' + item.id )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    // ====================================================================================================
  // WAIVER FINANCE APPLICATION
  // ====================================================================================================

  findCompletedWaiverFinanceApplications(): Observable<WaiverFinanceApplication[]> {
    console.log('findCompletedWaiverApplications');
    return this._http.get(this.BILLING_API + '/waiverFinanceApplications/state/COMPLETED')
      .map((res: Response) => <WaiverFinanceApplication[]>res.json());
  }

  findAssignedWaiverFinanceApplicationTasks(): Observable<WaiverFinanceApplicationTask[]> {
    console.log('findAssignedWaiverApplicationTasks');
    return this._http.get(this.BILLING_API + '/waiverFinanceApplications/assignedTasks')
      .map((res: Response) => <WaiverFinanceApplicationTask[]>res.json());
  }

  findPooledWaiverFinanceApplicationTasks(): Observable<WaiverFinanceApplicationTask[]> {
    console.log('findPooledWaiverApplicationTasks');
    return this._http.get(this.BILLING_API + '/waiverFinanceApplications/pooledTasks')
      .map((res: Response) => <WaiverFinanceApplicationTask[]>res.json());
  }

  findWaiverFinanceApplicationTaskByTaskId(taskId: string): Observable<WaiverFinanceApplicationTask> {
    console.log('findWaiverApplicationTaskByTaskId');
    return this._http.get(this.BILLING_API + '/waiverFinanceApplications/viewTask/' + taskId)
      .map((res: Response) => <WaiverFinanceApplicationTask>res.json());
  }

  findWaiverFinanceApplicationByReferenceNo(referenceNo: string): Observable<WaiverFinanceApplicationTask> {
    return this._http.get(this.BILLING_API + '/waiverFinanceApplications/' + referenceNo)
      .map((res: Response) => <WaiverFinanceApplicationTask>res.json());
  }

  findWaiverFinanceApplicationByTaskId(taskId: string): Observable<WaiverFinanceApplication> {
    return this._http.get(this.BILLING_API + '/waiverFinanceApplications/' + taskId)
      .map((res: Response) => <WaiverFinanceApplication>res.json());
  }

  findArchivedWaiverFinanceApplications(): Observable<WaiverFinanceApplication[]> {
    console.log('findArchivedWaiverApplications');
    return this._http.get(this.BILLING_API + '/waiverFinanceApplications/archived')
      .map((res: Response) => <WaiverFinanceApplication[]>res.json());
  }

  startWaiverFinanceApplicationTask(waiverFinanceApplication: WaiverFinanceApplication): Observable<String> {
    return this._http.post(this.BILLING_API + '/waiverFinanceApplications/startTask', JSON.stringify(waiverFinanceApplication))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeWaiverFinanceApplicationTask(waiverFinanceApplicationTask: WaiverFinanceApplicationTask): Observable<String> {
    console.log('TaskId: ' + waiverFinanceApplicationTask.taskId);
    return this._http.post(this.BILLING_API + '/waiverFinanceApplications/completeTask', JSON.stringify(waiverFinanceApplicationTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimWaiverFinanceApplicationTask(waiverFinanceApplicationTask: WaiverFinanceApplicationTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/waiverFinanceApplications/claimTask', JSON.stringify(waiverFinanceApplicationTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseWaiverFinanceApplicationTask(waiverFinanceApplicationTask: WaiverFinanceApplicationTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/waiverFinanceApplications/releaseTask', JSON.stringify(waiverFinanceApplicationTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  removeWaiverFinanceApplicationTask(waiverFinanceApplicationTask: WaiverFinanceApplicationTask): Observable<String> {
      return this._http.post(this.BILLING_API + '/waiverFinanceApplications/removeTask', JSON.stringify(waiverFinanceApplicationTask))
          .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateWaiverFinanceApplication(waiverFinanceApplication: WaiverFinanceApplication): Observable<String> {
    console.log('updateWaiverFinanceApplication' + waiverFinanceApplication.referenceNo);
    return this._http.put(this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/updateWaivers', JSON.stringify(waiverFinanceApplication))
    .flatMap((res: Response) => Observable.of(res.text()));
  }

  addWaiverAccountCharge( waiverFinanceApplication: WaiverFinanceApplication, accountCharge: AccountCharge ): Observable<String> {
      return this._http.post( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/accountCharge/' + accountCharge.id, JSON.stringify( accountCharge ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }
  
  addWaiverInvoice( waiverFinanceApplication: WaiverFinanceApplication, invoice: Invoice ): Observable<String> {
      return this._http.post( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/invoice/' + invoice.id, JSON.stringify( invoice ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }
  
  addWaiverDebitNote( waiverFinanceApplication: WaiverFinanceApplication, debitNote: DebitNote ): Observable<String> {
      return this._http.post( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/debitNote/' + debitNote.id, JSON.stringify( debitNote ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }
  
  updateWaivers( waiverFinanceApplication: WaiverFinanceApplication ): Observable<String> {
      return this._http.put( this.BILLING_API + '/waiverFinanceApplications/'  + waiverFinanceApplication.referenceNo + '/updateWaivers', JSON.stringify( waiverFinanceApplication ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }

  updateWaiverItems( waiverFinanceApplication: WaiverFinanceApplication, item: WaiverItem ): Observable<String> {
      return this._http.put( this.BILLING_API + '/waiverFinanceApplications/'  + waiverFinanceApplication.referenceNo + '/waiverFinanceApplicationItems/' + item.id, JSON.stringify( item ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }
  
  itemToWaiverItem( invoice: Invoice, waiverFinanceApplication: WaiverFinanceApplication  ): Observable<String> {
      return this._http.post( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/invoices/' + invoice.id, JSON.stringify( invoice ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }
  
  debitToWaiverItem( debitNote: DebitNote, waiverFinanceApplication: WaiverFinanceApplication  ): Observable<String> {
      return this._http.post( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/debitNotes/' + debitNote.id, JSON.stringify( debitNote ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }

  findWaiversByInvoice( waiverFinanceApplication: WaiverFinanceApplication ): Observable<WaiverInvoice[]> {
      return this._http.get( this.BILLING_API + '/waiverFinanceApplications/waiverInvoices/' + waiverFinanceApplication.referenceNo )
          .map(( res: Response ) => <WaiverInvoice[]>res.json() );
  }
  
  findWaiverByDebitNote( waiverFinanceApplication: WaiverFinanceApplication ): Observable<WaiverDebitNote[]> {
      return this._http.get( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/debitNotes' )
          .map(( res: Response ) => <WaiverDebitNote[]>res.json() );
  }
  
  findWaiverByAccountCharge( waiverFinanceApplication: WaiverFinanceApplication ): Observable<WaiverAccountCharge[]> {
      return this._http.get( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/accountCharges' )
          .map(( res: Response ) => <WaiverAccountCharge[]>res.json() );
  }

  findWaiverItems( waiverFinanceApplication: WaiverFinanceApplication ): Observable<WaiverItem[]> {
      return this._http.get( this.BILLING_API + '/waiverFinanceApplications/waiverItems/' + waiverFinanceApplication.referenceNo )
          .map(( res: Response ) => <WaiverItem[]>res.json() );
  }
  
  addWaiverItem( waiverFinanceApplication: WaiverFinanceApplication, item: WaiverItem): Observable<String> {
      return this._http.post( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/waiverFinanceApplicationItems', JSON.stringify( item ) )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }
  
  findInvoiceWaiverItems( waiverFinanceApplication: WaiverFinanceApplication, invoice:Invoice ): Observable<WaiverItem[]> {
      return this._http.get( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/items/invoices/' + invoice.id )
          .map(( res: Response ) => <WaiverItem[]>res.json() );
  } 
  
  findDebitWaiverItems( waiverFinanceApplication: WaiverFinanceApplication, debitNote:DebitNote ): Observable<KnockoffItem[]> {
      console.log( 'findReceiptItems' );
      return this._http.get( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo + '/items/debitNotes/' + debitNote.id )
          .map(( res: Response ) => <KnockoffItem[]>res.json() );
  }
  
  deleteWaiverDebitNotes( waiverDebitNote: WaiverDebitNote ): Observable<String> {
      return this._http.delete( this.BILLING_API + '/waiverDebitNotes/' + waiverDebitNote.id )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }

  deleteWaiverInvoices( waiverInvoice: WaiverInvoice ): Observable<String> {
      return this._http.delete( this.BILLING_API + '/waiverInvoices/' + waiverInvoice.id )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  }
  
  deleteWaiverAccCharges( waiverAccountCharge: WaiverAccountCharge ): Observable<String> {
      return this._http.delete( this.BILLING_API + '/waiverAccCharges/' + waiverAccountCharge.id )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  } 
  
  deleteWaiverItem( waiverFinanceApplication: WaiverFinanceApplication, item: WaiverItem ): Observable<String> {
      return this._http.delete( this.BILLING_API + '/waiverFinanceApplications/' + waiverFinanceApplication.referenceNo +  '/waiverItems/' + item.id )
          .flatMap(( res: Response ) => Observable.of( res.text() ) );
  } 
  
    // ====================================================================================================
    // REFUND PAYMENT
    // ==================================================================================================== 

    findRefundPayments(): Observable<RefundPayment[]> {
        return this._http.get( this.BILLING_API + '/refundPayments' )
            .map(( res: Response ) => <RefundPayment[]>res.json() );
    }

    findRefundPaymentByReferenceNo( referenceNo: string ): Observable<RefundPayment> {
        return this._http.get( this.BILLING_API + '/refundPayments/' + referenceNo )
            .map(( res: Response ) => <RefundPayment>res.json() );
    }

    startRefundPaymentTask( refundPayment: RefundPayment, payment: AdvancePayment ): Observable<String> {
        console.log("payment dlm service front ends : " +refundPayment);
        return this._http.post( this.BILLING_API + '/refundPayments/startTask/' + payment.referenceNo, JSON.stringify( refundPayment ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    completeRefundPaymentTask( refundPaymentTask: RefundPaymentTask ): Observable<String> {
        console.log( 'TaskId: ' + refundPaymentTask.taskId );
        return this._http.post( this.BILLING_API + '/refundPayments/completeTask', JSON.stringify( refundPaymentTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    claimRefundPaymentTask( refundPaymentTask: RefundPaymentTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/refundPayments/claimTask', JSON.stringify( refundPaymentTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

    releaseRefundPaymentTask( refundPaymentTask: RefundPaymentTask ): Observable<String> {
        return this._http.post( this.BILLING_API + '/refundPayments/releaseTask', JSON.stringify( refundPaymentTask ) )
            .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }
    
    removeRefundPaymentTask(refundPaymentTask: RefundPaymentTask): Observable<String> {
        return this._http.post(this.BILLING_API + '/refundPayments/removeTask', JSON.stringify(refundPaymentTask))
            .flatMap((res: Response) => Observable.of(res.text()));
    }

    findCompletedRefundPayments(): Observable<RefundPayment[]> {
        console.log( 'findCompletedRefundPayments' );
        return this._http.get( this.BILLING_API + '/refundPayments/state/COMPLETED' )
            .map(( res: Response ) => <RefundPayment[]>res.json() );
    }

    findCancelRefundPayments(): Observable<RefundPayment[]> {
        console.log( 'findCancelInvoices' );
        return this._http.get( this.BILLING_API + '/refundPayments/state/CANCELLED' )
            .map(( res: Response ) => <RefundPayment[]>res.json() );
    }

    findArchivedRefundPayments(): Observable<RefundPayment[]> {
        console.log( 'findArchivedRefundPayments' );
        return this._http.get( this.BILLING_API + '/refundPayments/archived' )
            .map(( res: Response ) => <RefundPayment[]>res.json() );
    }

    findAssignedRefundPaymentTasks(): Observable<RefundPaymentTask[]> {
        console.log( 'findAssignedRefundPaymentTasks front end services');
        return this._http.get( this.BILLING_API + '/refundPayments/assignedTasks' )
            .map((res: Response) => <RefundPaymentTask[]>res.json());
    }

    findPooledRefundPaymentTasks(): Observable<RefundPaymentTask[]> {
        console.log( 'findPooledRefundPaymentTasks');
        return this._http.get( this.BILLING_API + '/refundPayments/pooledTasks' )
            .map(( res: Response ) => <RefundPaymentTask[]>res.json() );
    }

    findRefundPaymentTaskByTaskId( taskId: string ): Observable<RefundPaymentTask> {
        console.log( 'findRefundPaymentTaskByTaskId' );
        return this._http.get( this.BILLING_API + '/refundPayments/viewTask/' + taskId )
            .map(( res: Response ) => <RefundPaymentTask>res.json() );
    }
    
    updateRefundPayments(refundPayment: RefundPayment): Observable<String> {
        console.log( 'update Refund Payment for voucher' );
        return this._http.put( this.BILLING_API + '/refundPayments/vouchers/' + refundPayment.referenceNo, JSON.stringify( refundPayment ))
        .flatMap(( res: Response ) => Observable.of( res.text() ) );
    }

}

