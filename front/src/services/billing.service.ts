import {Injectable} from '@angular/core';
import {Response, Http, RequestOptions, Headers} from '@angular/http';
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

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INVOICE
  // ====================================================================================================

  findCompletedInvoices(): Observable<Invoice[]> {
    console.log('findCompletedInvoices');
    return this.http.get(environment.endpoint + '/api/billing/invoices/state/COMPLETED')
      .map((res: Response) => <Invoice[]>res.json());
  }

  findUnpaidInvoices(account: Account): Observable<Invoice[]> {
    console.log('findUnpaidInvoices');
    return this.http.get(environment.endpoint + '/api/billing/invoices/unpaidInvoices/' + account.code)
      .map((res: Response) => <Invoice[]>res.json());
  }

  // todo: this goes thru ACL
  findArchivedInvoices(): Observable<Invoice[]> {
    console.log('findArchivedInvoices');
    return this.http.get(environment.endpoint + '/api/billing/invoices/state/COMPLETED')
      .map((res: Response) => <Invoice[]>res.json());
  }

  findAssignedInvoiceTasks(): Observable<InvoiceTask[]> {
    console.log('findAssignedInvoiceTasks');
    return this.http.get(environment.endpoint + '/api/billing/invoices/assignedTasks')
      .map((res: Response) => <InvoiceTask[]>res.json());
  }

  findPooledInvoiceTasks(): Observable<InvoiceTask[]> {
    console.log('findPooledInvoiceTasks');
    return this.http.get(environment.endpoint + '/api/billing/invoices/pooledTasks')
      .map((res: Response) => <InvoiceTask[]>res.json());
  }

  findInvoiceTaskByTaskId(taskId: string): Observable<InvoiceTask> {
    console.log('findInvoiceTaskByTaskId');
    return this.http.get(environment.endpoint + '/api/billing/invoices/viewTask/' + taskId)
      .map((res: Response) => <InvoiceTask>res.json());
  }

  findInvoiceByReferenceNo(referenceNo: string): Observable<Invoice> {
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + referenceNo)
      .map((res: Response) => <Invoice>res.json());
  }

  findInvoiceByTaskId(taskId: string): Observable<Invoice> {
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + taskId)
      .map((res: Response) => <Invoice>res.json());
  }

  findInvoiceItems(invoice: Invoice): Observable<InvoiceItem[]> {
    console.log('findInvoiceItems');
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems')
      .map((res: Response) => <InvoiceItem[]>res.json());
  }

  startInvoiceTask(invoice: Invoice): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/startTask', JSON.stringify(invoice), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    console.log('TaskId: ' + invoiceTask.taskId);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/completeTask', JSON.stringify(invoiceTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/claimTask', JSON.stringify(invoiceTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/releaseTask', JSON.stringify(invoiceTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateInvoice(invoice: Invoice): Observable<String> {
    return this.http.put(environment.endpoint + '/api/billing/invoices', JSON.stringify(invoice))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addInvoiceItem(invoice: Invoice, item: InvoiceItem): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems', JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateInvoiceItem(invoice: Invoice, item: InvoiceItem): Observable<String> {
    console.log('saving invoice item' + item.id);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id, JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteInvoiceItem(invoice: Invoice, item: InvoiceItem): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  findDebitNotesByInvoice(invoice: Invoice): Observable<DebitNote[]> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/debitNotes')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  findCreditNotesByInvoice(invoice: Invoice): Observable<CreditNote[]> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/creditNotes')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  // ====================================================================================================
  // RECEIPT
  // ====================================================================================================

  findAssignedReceiptTasks(): Observable<ReceiptTask[]> {
    console.log('findAssignedReceiptTasks');
    return this.http.get(environment.endpoint + '/api/billing/receipts/assignedTasks')
      .map((res: Response) => <ReceiptTask[]>res.json());
  }

  findPooledReceiptTasks(): Observable<ReceiptTask[]> {
    console.log('findPooledReceiptTasks');
    return this.http.get(environment.endpoint + '/api/billing/receipts/pooledTasks')
      .map((res: Response) => <ReceiptTask[]>res.json());
  }

  findReceiptTaskByTaskId(taskId: string): Observable<ReceiptTask> {
    console.log('findReceiptTaskByTaskId');
    return this.http.get(environment.endpoint + '/api/billing/receipts/viewTask/' + taskId)
      .map((res: Response) => <ReceiptTask>res.json());
  }

  findReceiptByReferenceNo(referenceNo: string): Observable<Receipt> {
    console.log('encoded uri: ' + encodeURI(referenceNo));
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + encodeURI(referenceNo))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptByTaskId(taskId: string): Observable<Receipt> {
    console.log('encoded uri: ' + encodeURI(taskId));
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + encodeURI(taskId))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptItems(receipt: Receipt): Observable<ReceiptItem[]> {
    console.log('findReceiptItems');
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + '/receiptItems')
      .map((res: Response) => <ReceiptItem[]>res.json());
  }

  startReceiptTask(receipt: Receipt): Observable<String> {
    console.log('receipt: ' + receipt);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/startTask', JSON.stringify(receipt), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    console.log('TaskId: ' + receiptTask.taskId);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/completeTask', JSON.stringify(receiptTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/claimTask', JSON.stringify(receiptTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/releaseTask', JSON.stringify(receiptTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateReceipt(receipt: Receipt): Observable<String> {
    return this.http.put(environment.endpoint + '/api/billing/receipts', JSON.stringify(receipt))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addReceiptItem(receipt: Receipt, item: ReceiptItem): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + '/receiptItems', JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateReceiptItem(receipt: Receipt, item: ReceiptItem) {
    console.log('saving receipt item' + item.id);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id, JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteReceiptItem(receipt: Receipt, item: ReceiptItem) {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // CREDIT NOTE
  // ====================================================================================================

  findCreditNotesbyInvoice(invoice: Invoice): Observable<CreditNote[]> {
    console.log('findDebitNotes');
    return this.http.get(environment.endpoint + '/api/billing/invoice/' + invoice.referenceNo + '/creditNotes/')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  findCompletedCreditNotes(): Observable<CreditNote[]> {
    console.log('findCompletedCreditNotes');
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/state/COMPLETED')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  // todo: this goes thru ACL
  findArchivedCreditNotes(): Observable<CreditNote[]> {
    console.log('findArchivedCreditNotes');
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/state/COMPLETED')
      .map((res: Response) => <CreditNote[]>res.json());
  }

  findAssignedCreditNoteTasks(): Observable<CreditNoteTask[]> {
    console.log('findAssignedCreditNoteTasks');
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/assignedTasks')
      .map((res: Response) => <CreditNoteTask[]>res.json());
  }

  findPooledCreditNoteTasks(): Observable<CreditNoteTask[]> {
    console.log('findPooledCreditNoteTasks');
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/pooledTasks')
      .map((res: Response) => <CreditNoteTask[]>res.json());
  }

  findCreditNoteTaskByTaskId(taskId: string): Observable<CreditNoteTask> {
    console.log('findCreditNoteTaskByTaskId');
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/viewTask/' + taskId)
      .map((res: Response) => <CreditNoteTask>res.json());
  }

  findCreditNoteByReferenceNo(referenceNo: string): Observable<CreditNote> {
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/' + referenceNo)
      .map((res: Response) => <CreditNote>res.json());
  }

  findCreditNoteByTaskId(taskId: string): Observable<CreditNote> {
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/' + taskId)
      .map((res: Response) => <CreditNote>res.json());
  }

  findCreditNoteItems(creditNote: CreditNote): Observable<CreditNoteItem[]> {
    console.log('findCreditNoteItems');
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/' + creditNote.referenceNo + '/creditNoteItems')
      .map((res: Response) => <CreditNoteItem[]>res.json());
  }

  startCreditNoteTask(creditNote: CreditNote): Observable<String> {
    console.log('creditNote: ' + creditNote);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/creditNotes/startTask', JSON.stringify(creditNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeCreditNoteTask(creditNoteTask: CreditNoteTask): Observable<String> {
    console.log('TaskId: ' + creditNoteTask.taskId);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/creditNotes/completeTask', JSON.stringify(creditNoteTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimCreditNoteTask(creditNoteTask: CreditNoteTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/creditNotes/claimTask', JSON.stringify(creditNoteTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseCreditNoteTask(creditNoteTask: CreditNoteTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/creditNotes/releaseTask', JSON.stringify(creditNoteTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateCreditNote(creditNote: CreditNote) {
    console.log('saving creditNote' + creditNote.id);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/creditNotes/' + creditNote.id, JSON.stringify(creditNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addCreditNoteItem(creditNote: CreditNote, item: CreditNoteItem): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/creditNotes/' + creditNote.referenceNo + '/creditNoteItems', JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateCreditNoteItem(creditNote: CreditNote, item: CreditNoteItem) {
    console.log('saving creditNote item' + item.id);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/creditNotes/' + creditNote.referenceNo + '/creditNoteItems/' + item.id, JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteCreditNoteItem(creditNote: CreditNote, item: CreditNoteItem) {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/billing/creditNotes/' + creditNote.referenceNo + '/creditNoteItems/' + item.id, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // DEBIT NOTE
  // ====================================================================================================
  findDebitNotesbyInvoice(invoice: Invoice): Observable<DebitNote[]> {
    console.log('findDebitNotes');
    return this.http.get(environment.endpoint + '/api/billing/invoice/' + invoice.referenceNo + '/debitNotes/')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  findCompletedDebitNotes(): Observable<DebitNote[]> {
    console.log('findCompletedDebitNotes');
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/state/COMPLETED')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  // todo: this goes thru ACL
  findArchivedDebitNotes(): Observable<DebitNote[]> {
    console.log('findArchivedDebitNotes');
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/state/COMPLETED')
      .map((res: Response) => <DebitNote[]>res.json());
  }

  findAssignedDebitNoteTasks(): Observable<DebitNoteTask[]> {
    console.log('findAssignedDebitNoteTasks');
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/assignedTasks')
      .map((res: Response) => <DebitNoteTask[]>res.json());
  }

  findPooledDebitNoteTasks(): Observable<DebitNoteTask[]> {
    console.log('findPooledDebitNoteTasks');
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/pooledTasks')
      .map((res: Response) => <DebitNoteTask[]>res.json());
  }

  findDebitNoteTaskByTaskId(taskId: string): Observable<DebitNoteTask> {
    console.log('findDebitNoteTaskByTaskId');
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/viewTask/' + taskId)
      .map((res: Response) => <DebitNoteTask>res.json());
  }

  findDebitNoteByReferenceNo(referenceNo: string): Observable<DebitNote> {
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/' + referenceNo)
      .map((res: Response) => <DebitNote>res.json());
  }

  findDebitNoteByTaskId(taskId: string): Observable<DebitNote> {
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/' + taskId)
      .map((res: Response) => <DebitNote>res.json());
  }

  // findReceiptItems(receipt: Receipt): Observable<ReceiptItem[]> {
  //   console.log("findReceiptItems");
  //   return this.http.get(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + "/receiptItems")
  //     .map((res: Response) => <ReceiptItem[]>res.json());
  // }

  findDebitNoteItems(debitNote: DebitNote): Observable<DebitNoteItem[]> {
    console.log('findDebitNoteItems');
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/' + debitNote.referenceNo + '/debitNoteItems')
      .map((res: Response) => <DebitNoteItem[]>res.json());
  }

  startDebitNoteTask(debitNote: DebitNote): Observable<String> {
    console.log('debitNote: ' + debitNote);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/debitNotes/startTask', JSON.stringify(debitNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeDebitNoteTask(debitNoteTask: DebitNoteTask): Observable<String> {
    console.log('TaskId: ' + debitNoteTask.taskId);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/debitNotes/completeTask', JSON.stringify(debitNoteTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimDebitNoteTask(debitNoteTask: DebitNoteTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/debitNotes/claimTask', JSON.stringify(debitNoteTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseDebitNoteTask(debitNoteTask: DebitNoteTask): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/debitNotes/releaseTask', JSON.stringify(debitNoteTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDebitNote(debitNote: DebitNote) {
    console.log('saving creditNote' + debitNote.id);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/debitNotes/' + debitNote.id, JSON.stringify(debitNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addDebitNoteItem(debitNote: DebitNote, item: DebitNoteItem): Observable<String> {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/debitNotes/' + debitNote.referenceNo + '/debitNoteItems', JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDebitNoteItem(debitNote: DebitNote, item: DebitNoteItem) {
    console.log('saving debitNote item' + item.id);
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/debitNotes/' + debitNote.referenceNo + '/debitNoteItems/' + item.id, JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteDebitNoteItem(debitNote: DebitNote, item: DebitNoteItem) {
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/billing/debitNotes/' + debitNote.referenceNo + '/debitNoteItems/' + item.id, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
}
