import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {InvoiceTask} from '../app/billing/invoices/invoice-task.interface';
import {Invoice} from '../app/billing/invoices/invoice.interface';
import {InvoiceItem} from '../app/billing/invoices/invoice-item.interface';
import {ReceiptTask} from '../app/billing/receipts/receipt-task.interface';
import {Receipt} from '../app/billing/receipts/receipt.interface';
import {ReceiptItem} from '../app/billing/receipts/receipt-item.interface';
import {CreditNote} from '../app/billing/credit-notes/credit-note.interface';
import {DebitNote} from '../app/billing/debit-notes/debit-note.interface';
import {CreditNoteTask} from '../app/billing/credit-notes/credit-note-task.interface';
import {DebitNoteTask} from '../app/billing/debit-notes/debit-note-task.interface';
import {DebitNoteItem} from '../app/billing/debit-notes/debit-note-item.interface';
import {CreditNoteItem} from '../app/billing/credit-notes/credit-note-item.interface';
import {Account} from '../app/account/accounts/account.interface';

@Injectable()
export class BillingService {

  private BILLING_API: string = environment.endpoint + '/api/billing';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INVOICE
  // ====================================================================================================

  findCompletedInvoices(): Observable<Invoice[]> {
    console.log('findCompletedInvoices');
    return this._http.get(this.BILLING_API + '/invoices/state/COMPLETED')
      .map((res: Response) => <Invoice[]>res.json());
  }

  findCancelInvoices(): Observable<Invoice[]> {
    console.log('findCancelInvoices');
    return this._http.get(this.BILLING_API + '/invoices/state/CANCELLED')
      .map((res: Response) => <Invoice[]>res.json());
  }

  findUnpaidInvoices(account: Account): Observable<Invoice[]> {
    console.log('findUnpaidInvoices');
    return this._http.get(this.BILLING_API + '/invoices/unpaidInvoices/' + account.code)
      .map((res: Response) => <Invoice[]>res.json());
  }

  // todo: this goes thru ACL
  findArchivedInvoices(): Observable<Invoice[]> {
    console.log('findArchivedInvoices');
    return this._http.get(this.BILLING_API + '/invoices/archived' )
      .map((res: Response) => <Invoice[]>res.json());
  }

  findAssignedInvoiceTasks(): Observable<InvoiceTask[]> {
    console.log('findAssignedInvoiceTasks');
    return this._http.get(this.BILLING_API + '/invoices/assignedTasks')
      .map((res: Response) => <InvoiceTask[]>res.json());
  }

  findPooledInvoiceTasks(): Observable<InvoiceTask[]> {
    console.log('findPooledInvoiceTasks');
    return this._http.get(this.BILLING_API + '/invoices/pooledTasks')
      .map((res: Response) => <InvoiceTask[]>res.json());
  }

  findInvoiceTaskByTaskId(taskId: string): Observable<InvoiceTask> {
    console.log('findInvoiceTaskByTaskId');
    return this._http.get(this.BILLING_API + '/invoices/viewTask/' + taskId)
      .map((res: Response) => <InvoiceTask>res.json());
  }

  findInvoiceByReferenceNo(referenceNo: string): Observable<Invoice> {
    return this._http.get(this.BILLING_API + '/invoices/' + referenceNo)
      .map((res: Response) => <Invoice>res.json());
  }
  
  findInvoicesByAccount(code: string): Observable<Invoice> {
      return this._http.get(this.BILLING_API + '/invoices/account/' + code)
        .map((res: Response) => <Invoice>res.json());
    }

  findInvoiceByTaskId(taskId: string): Observable<Invoice> {
    return this._http.get(this.BILLING_API + '/invoices/' + taskId)
      .map((res: Response) => <Invoice>res.json());
  }

  findInvoiceItems(invoice: Invoice): Observable<InvoiceItem[]> {
    console.log('findInvoiceItems');
    return this._http.get(this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems')
      .map((res: Response) => <InvoiceItem[]>res.json());
  }

  startInvoiceTask(invoice: Invoice): Observable<String> {
    return this._http.post(this.BILLING_API + '/invoices/startTask', JSON.stringify(invoice))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    console.log('TaskId: ' + invoiceTask.taskId);
    return this._http.post(this.BILLING_API + '/invoices/completeTask', JSON.stringify(invoiceTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/invoices/claimTask', JSON.stringify(invoiceTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/invoices/releaseTask', JSON.stringify(invoiceTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  cancelInvoice(invoice: Invoice): Observable<String> {
    return this._http.post(this.BILLING_API + '/invoices/' + invoice.referenceNo + '/cancel', JSON.stringify(invoice))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateInvoice(invoice: Invoice): Observable<String> {
    return this._http.put(this.BILLING_API + '/invoices', JSON.stringify(invoice))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addInvoiceItem(invoice: Invoice, item: InvoiceItem): Observable<String> {
    return this._http.post(this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems', JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateInvoiceItem(invoice: Invoice, item: InvoiceItem): Observable<String> {
    console.log('saving invoice item' + item.id);
    return this._http.put(this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id, JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteInvoiceItem(invoice: Invoice, item: InvoiceItem): Observable<String> {
    return this._http.delete(this.BILLING_API + '/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  findDebitNotesByInvoice(invoice: Invoice): Observable<DebitNote[]> {
    return this._http.get(this.BILLING_API + '/invoices/' + invoice.referenceNo + '/debitNotes')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  findCreditNotesByInvoice(invoice: Invoice): Observable<CreditNote[]> {
    return this._http.get(this.BILLING_API + '/invoices/' + invoice.referenceNo + '/creditNotes')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  // ====================================================================================================
  // RECEIPT
  // ====================================================================================================

  findAssignedReceiptTasks(): Observable<ReceiptTask[]> {
    console.log('findAssignedReceiptTasks');
    return this._http.get(this.BILLING_API + '/receipts/assignedTasks')
      .map((res: Response) => <ReceiptTask[]>res.json());
  }

  findPooledReceiptTasks(): Observable<ReceiptTask[]> {
    console.log('findPooledReceiptTasks');
    return this._http.get(this.BILLING_API + '/receipts/pooledTasks')
      .map((res: Response) => <ReceiptTask[]>res.json());
  }
  
  findArchivedReceipts(): Observable<Receipt[]> {
      console.log('findArchivedReceipts');
      return this._http.get(this.BILLING_API + '/receipts/archived' )
        .map((res: Response) => <Receipt[]>res.json());
    }

  findReceiptTaskByTaskId(taskId: string): Observable<ReceiptTask> {
    console.log('findReceiptTaskByTaskId');
    return this._http.get(this.BILLING_API + '/receipts/viewTask/' + taskId)
      .map((res: Response) => <ReceiptTask>res.json());
  }

  findReceiptByReferenceNo(referenceNo: string): Observable<Receipt> {
    console.log('encoded uri: ' + encodeURI(referenceNo));
    return this._http.get(this.BILLING_API + '/receipts/' + encodeURI(referenceNo))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptByTaskId(taskId: string): Observable<Receipt> {
    console.log('encoded uri: ' + encodeURI(taskId));
    return this._http.get(this.BILLING_API + '/receipts/' + encodeURI(taskId))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptItems(receipt: Receipt): Observable<ReceiptItem[]> {
    console.log('findReceiptItems');
    return this._http.get(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems')
      .map((res: Response) => <ReceiptItem[]>res.json());
  }

  startReceiptTask(receipt: Receipt): Observable<String> {
    console.log('receipt: ' + receipt);
    return this._http.post(this.BILLING_API + '/receipts/startTask', JSON.stringify(receipt))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    console.log('TaskId: ' + receiptTask.taskId);
    return this._http.post(this.BILLING_API + '/receipts/completeTask', JSON.stringify(receiptTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/receipts/claimTask', JSON.stringify(receiptTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/receipts/releaseTask', JSON.stringify(receiptTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateReceipt(receipt: Receipt): Observable<String> {
    return this._http.put(this.BILLING_API + '/receipts', JSON.stringify(receipt))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addReceiptItem(receipt: Receipt, item: ReceiptItem): Observable<String> {
    return this._http.post(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems', JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateReceiptItem(receipt: Receipt, item: ReceiptItem) {
    console.log('saving receipt item' + item.id);
    return this._http.put(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id, JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteReceiptItem(receipt: Receipt, item: ReceiptItem) {
    return this._http.delete(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  calculateChargeInvoice(receipt: Receipt): Observable<String> {
      return this._http.post(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/account', null)
        .flatMap((res: Response) => Observable.of(res.text()));
    }
  
  addReceiptInvoiceItems(receipt: Receipt, invoice: Invoice): Observable<String> {
      return this._http.post(this.BILLING_API + '/receipts/' + receipt.referenceNo + '/invoice/'+ invoice.referenceNo, JSON.stringify(invoice))
        .flatMap((res: Response) => Observable.of(res.text()));
    }

  // ====================================================================================================
  // CREDIT NOTE
  // ====================================================================================================

  findCreditNotesbyInvoice(invoice: Invoice): Observable<CreditNote[]> {
    console.log('findDebitNotes');
    return this._http.get(this.BILLING_API + '/invoice/' + invoice.referenceNo + '/creditNotes/')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  findCompletedCreditNotes(): Observable<CreditNote[]> {
    console.log('findCompletedCreditNotes');
    return this._http.get(this.BILLING_API + '/creditNotes/state/COMPLETED')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  // todo: this goes thru ACL
  findArchivedCreditNotes(): Observable<CreditNote[]> {
    console.log('findArchivedCreditNotes');
    return this._http.get(this.BILLING_API + '/creditNotes/state/COMPLETED')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  findAssignedCreditNoteTasks(): Observable<CreditNoteTask[]> {
    console.log('findAssignedCreditNoteTasks');
    return this._http.get(this.BILLING_API + '/creditNotes/assignedTasks')
      .map((res: Response) => <CreditNoteTask[]>res.json());
  }

  findPooledCreditNoteTasks(): Observable<CreditNoteTask[]> {
    console.log('findPooledCreditNoteTasks');
    return this._http.get(this.BILLING_API + '/creditNotes/pooledTasks')
      .map((res: Response) => <CreditNoteTask[]>res.json());
  }

  findCreditNoteTaskByTaskId(taskId: string): Observable<CreditNoteTask> {
    console.log('findCreditNoteTaskByTaskId');
    return this._http.get(this.BILLING_API + '/creditNotes/viewTask/' + taskId)
      .map((res: Response) => <CreditNoteTask>res.json());
  }

  findCreditNoteByReferenceNo(referenceNo: string): Observable<CreditNote> {
    return this._http.get(this.BILLING_API + '/creditNotes/' + referenceNo)
      .map((res: Response) => <CreditNote>res.json());
  }

  findCreditNoteByTaskId(taskId: string): Observable<CreditNote> {
    return this._http.get(this.BILLING_API + '/creditNotes/' + taskId)
      .map((res: Response) => <CreditNote>res.json());
  }

  findCreditNoteItems(creditNote: CreditNote): Observable<CreditNoteItem[]> {
    console.log('findCreditNoteItems');
    return this._http.get(this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems')
      .map((res: Response) => <CreditNoteItem[]>res.json());
  }

  startCreditNoteTask(creditNote: CreditNote): Observable<String> {
    console.log('creditNote: ' + creditNote);
    return this._http.post(this.BILLING_API + '/creditNotes/startTask', JSON.stringify(creditNote))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeCreditNoteTask(creditNoteTask: CreditNoteTask): Observable<String> {
    console.log('TaskId: ' + creditNoteTask.taskId);
    return this._http.post(this.BILLING_API + '/creditNotes/completeTask', JSON.stringify(creditNoteTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimCreditNoteTask(creditNoteTask: CreditNoteTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/creditNotes/claimTask', JSON.stringify(creditNoteTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseCreditNoteTask(creditNoteTask: CreditNoteTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/creditNotes/releaseTask', JSON.stringify(creditNoteTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateCreditNote(creditNote: CreditNote) {
    console.log('saving creditNote' + creditNote.id);
    return this._http.put(this.BILLING_API + '/creditNotes/' + creditNote.id, JSON.stringify(creditNote))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addCreditNoteItem(creditNote: CreditNote, item: CreditNoteItem): Observable<String> {
    return this._http.post(this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems', JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateCreditNoteItem(creditNote: CreditNote, item: CreditNoteItem) {
    console.log('saving creditNote item' + item.id);
    return this._http.put(this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems/' + item.id, JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteCreditNoteItem(creditNote: CreditNote, item: CreditNoteItem) {
    return this._http.delete(this.BILLING_API + '/creditNotes/' + creditNote.referenceNo + '/creditNoteItems/' + item.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // DEBIT NOTE
  // ====================================================================================================
  findDebitNotesbyInvoice(invoice: Invoice): Observable<DebitNote[]> {
    console.log('findDebitNotes');
    return this._http.get(this.BILLING_API + '/invoice/' + invoice.referenceNo + '/debitNotes/')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  findCompletedDebitNotes(): Observable<DebitNote[]> {
    console.log('findCompletedDebitNotes');
    return this._http.get(this.BILLING_API + '/debitNotes/state/COMPLETED')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  // todo: this goes thru ACL
  findArchivedDebitNotes(): Observable<DebitNote[]> {
    console.log('findArchivedDebitNotes');
    return this._http.get(this.BILLING_API + '/debitNotes/state/COMPLETED')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  findAssignedDebitNoteTasks(): Observable<DebitNoteTask[]> {
    console.log('findAssignedDebitNoteTasks');
    return this._http.get(this.BILLING_API + '/debitNotes/assignedTasks')
      .map((res: Response) => <DebitNoteTask[]>res.json());
  }

  findPooledDebitNoteTasks(): Observable<DebitNoteTask[]> {
    console.log('findPooledDebitNoteTasks');
    return this._http.get(this.BILLING_API + '/debitNotes/pooledTasks')
      .map((res: Response) => <DebitNoteTask[]>res.json());
  }

  findDebitNoteTaskByTaskId(taskId: string): Observable<DebitNoteTask> {
    console.log('findDebitNoteTaskByTaskId');
    return this._http.get(this.BILLING_API + '/debitNotes/viewTask/' + taskId)
      .map((res: Response) => <DebitNoteTask>res.json());
  }

  findDebitNoteByReferenceNo(referenceNo: string): Observable<DebitNote> {
    return this._http.get(this.BILLING_API + '/debitNotes/' + referenceNo)
      .map((res: Response) => <DebitNote>res.json());
  }

  findDebitNoteByTaskId(taskId: string): Observable<DebitNote> {
    return this._http.get(this.BILLING_API + '/debitNotes/' + taskId)
      .map((res: Response) => <DebitNote>res.json());
  }

  // findReceiptItems(receipt: Receipt): Observable<ReceiptItem[]> {
  //   console.log("findReceiptItems");
  //   return this._http.get(this.BILLING_API + '/receipts/' + receipt.referenceNo + "/receiptItems")
  //     .map((res: Response) => <ReceiptItem[]>res.json());
  // }

  findDebitNoteItems(debitNote: DebitNote): Observable<DebitNoteItem[]> {
    console.log('findDebitNoteItems');
    return this._http.get(this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems')
      .map((res: Response) => <DebitNoteItem[]>res.json());
  }

  startDebitNoteTask(debitNote: DebitNote): Observable<String> {
    console.log('debitNote: ' + debitNote);
    return this._http.post(this.BILLING_API + '/debitNotes/startTask', JSON.stringify(debitNote))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeDebitNoteTask(debitNoteTask: DebitNoteTask): Observable<String> {
    console.log('TaskId: ' + debitNoteTask.taskId);
    return this._http.post(this.BILLING_API + '/debitNotes/completeTask', JSON.stringify(debitNoteTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimDebitNoteTask(debitNoteTask: DebitNoteTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/debitNotes/claimTask', JSON.stringify(debitNoteTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseDebitNoteTask(debitNoteTask: DebitNoteTask): Observable<String> {
    return this._http.post(this.BILLING_API + '/debitNotes/releaseTask', JSON.stringify(debitNoteTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDebitNote(debitNote: DebitNote) {
    console.log('saving creditNote' + debitNote.id);
    return this._http.put(this.BILLING_API + '/debitNotes/' + debitNote.id, JSON.stringify(debitNote))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addDebitNoteItem(debitNote: DebitNote, item: DebitNoteItem): Observable<String> {
    return this._http.post(this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems', JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDebitNoteItem(debitNote: DebitNote, item: DebitNoteItem) {
    console.log('saving debitNote item' + item.id);
    return this._http.put(this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems/' + item.id, JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteDebitNoteItem(debitNote: DebitNote, item: DebitNoteItem) {
    return this._http.delete(this.BILLING_API + '/debitNotes/' + debitNote.referenceNo + '/debitNoteItems/' + item.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
}
