import {Injectable} from '@angular/core';
import {Response, Http, RequestOptions, Headers} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {InvoiceTask} from "../app/billing/invoices/invoice-task.interface";
import {Invoice} from "../app/billing/invoices/invoice.interface";
import {InvoiceItem} from "../app/billing/invoices/invoice-item.interface";
import {ReceiptTask} from "../app/billing/receipts/receipt-task.interface";
import {Receipt} from "../app/billing/receipts/receipt.interface";
import {ReceiptItem} from "../app/billing/receipts/receipt-item.interface";
import {CreditNote} from "../app/billing/credit-notes/credit-note.interface";
import {DebitNote} from "../app/billing/debit-notes/debit-note.interface";
import {CreditNoteTask} from "../app/billing/credit-notes/credit-note-task.interface";
import {DebitNoteTask} from "../app/billing/debit-notes/debit-note-task.interface";

@Injectable()
export class BillingService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INVOICE
  // ====================================================================================================


  findCompletedInvoices(): Observable<Invoice[]> {
    console.log("findCompletedInvoices");
    return this.http.get(environment.endpoint + '/api/billing/invoices/state/COMPLETED')
      .map((res: Response) => <Invoice[]>res.json());
  }

  // todo: this goes thru ACL
  findArchivedInvoices(): Observable<Invoice[]> {
    console.log("findArchivedInvoices");
    return this.http.get(environment.endpoint + '/api/billing/invoices/state/COMPLETED')
      .map((res: Response) => <Invoice[]>res.json());
  }

  findAssignedInvoiceTasks(): Observable<InvoiceTask[]> {
    console.log("findAssignedInvoiceTasks");
    return this.http.get(environment.endpoint + '/api/billing/invoices/assignedTasks')
      .map((res: Response) => <InvoiceTask[]>res.json());
  }

  findPooledInvoiceTasks(): Observable<InvoiceTask[]> {
    console.log("findPooledInvoiceTasks");
    return this.http.get(environment.endpoint + '/api/billing/invoices/pooledTasks')
      .map((res: Response) => <InvoiceTask[]>res.json());
  }

  findInvoiceTaskByTaskId(taskId: string): Observable<InvoiceTask> {
    console.log("findInvoiceTaskByTaskId");
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
    console.log("findInvoiceItems");
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + "/invoiceItems")
      .map((res: Response) => <InvoiceItem[]>res.json());
  }

  startInvoiceTask(invoice: Invoice): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/startTask', JSON.stringify(invoice), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    console.log("TaskId: " + invoiceTask.taskId);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/completeTask', JSON.stringify(invoiceTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/claimTask', JSON.stringify(invoiceTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseInvoiceTask(invoiceTask: InvoiceTask): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
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
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems', JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateInvoiceItem(invoice: Invoice, item: InvoiceItem) {
    console.log("saving invoice item" + item.id);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id, JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteInvoiceItem(invoice: Invoice, item: InvoiceItem) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems/' + item.id, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }


  // ====================================================================================================
  // RECEIPT
  // ====================================================================================================

  findAssignedReceiptTasks(): Observable<ReceiptTask[]> {
    console.log("findAssignedReceiptTasks");
    return this.http.get(environment.endpoint + '/api/billing/receipts/assignedTasks')
      .map((res: Response) => <ReceiptTask[]>res.json());
  }

  findPooledReceiptTasks(): Observable<ReceiptTask[]> {
    console.log("findPooledReceiptTasks");
    return this.http.get(environment.endpoint + '/api/billing/receipts/pooledTasks')
      .map((res: Response) => <ReceiptTask[]>res.json());
  }

  findReceiptTaskByTaskId(taskId: string): Observable<ReceiptTask> {
    console.log("findReceiptTaskByTaskId");
    return this.http.get(environment.endpoint + '/api/billing/receipts/viewTask/' + taskId)
      .map((res: Response) => <ReceiptTask>res.json());
  }

  findReceiptByReferenceNo(referenceNo: string): Observable<Receipt> {
    console.log("encoded uri: " + encodeURI(referenceNo))
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + encodeURI(referenceNo))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptByTaskId(taskId: string): Observable<Receipt> {
    console.log("encoded uri: " + encodeURI(taskId))
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + encodeURI(taskId))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptItems(receipt: Receipt): Observable<ReceiptItem[]> {
    console.log("findReceiptItems");
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + "/receiptItems")
      .map((res: Response) => <ReceiptItem[]>res.json());
  }

  startReceiptTask(receipt: Receipt): Observable<String> {
    console.log("receipt: " + receipt);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/startTask', JSON.stringify(receipt), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    console.log("TaskId: " + receiptTask.taskId);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/completeTask', JSON.stringify(receiptTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/claimTask', JSON.stringify(receiptTask), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseReceiptTask(receiptTask: ReceiptTask): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
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
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + '/receiptItems', JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateReceiptItem(receipt: Receipt, item: ReceiptItem) {
    console.log("saving receipt item" + item.id);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id, JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteReceiptItem(receipt: Receipt, item: ReceiptItem) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + '/receiptItems/' + item.id, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // CREDIT NOTE
  // ====================================================================================================

  findCreditNotes(invoice: Invoice): Observable<CreditNote> {
    console.log("findCreditNotes");
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/' + invoice)
      .map((res: Response) => <CreditNote>res.json());
  }

  findCreditNoteByReferenceNo(referenceNo: string): Observable<CreditNote> {
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/' + referenceNo)
      .map((res: Response) => <CreditNote>res.json());
  }

  findCreditNoteTaskByTaskId(taskId: string): Observable<CreditNoteTask> {
    console.log("findCreditNoteTaskByTaskId");
    return this.http.get(environment.endpoint + '/api/billing/creditNotes/viewTask/' + taskId)
      .map((res: Response) => <CreditNoteTask>res.json());
  }

  startCreditNoteTask(creditNote: CreditNote): Observable<String> {
    console.log("creditNote: " + creditNote);
     let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/creditNotes/startTask', JSON.stringify(creditNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateCreditNote(creditNote: CreditNote) {
    console.log("saving creditNote" + creditNote.id);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/creditNotes/' + creditNote.id, JSON.stringify(creditNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // DEBIT NOTE
  // ====================================================================================================

  // findDebitNotes(invoice: Invoice): Observable<DebitNote[]> {
  //   console.log("findDebitNotes");
  //   return this.http.get(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + "/debitNotes")
  //     .map((res: Response) => <DebitNote[]>res.json());
  // }

  findDebitNotes(invoice: Invoice): Observable<DebitNote[]> {
    console.log("findDebitNotes");
    return this.http.get(environment.endpoint + '/api/billing/invoice/' + invoice.referenceNo + "/debitNotes/")
      .map((res: Response) => <DebitNote[]>res.json());
  }

  findDebitNoteByReferenceNo(referenceNo: string): Observable<DebitNote> {
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/' + referenceNo)
      .map((res: Response) => <CreditNote>res.json());
  }

  findDebitNoteTaskByTaskId(taskId: string): Observable<DebitNoteTask> {
    console.log("findDebitNoteTaskByTaskId");
    return this.http.get(environment.endpoint + '/api/billing/debitNotes/viewTask/' + taskId)
      .map((res: Response) => <DebitNoteTask>res.json());
  }

  startDebitNoteTask(debitNote: DebitNote): Observable<String> {
    console.log("debitNote: " + debitNote);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/billing/debitNotes/startTask', JSON.stringify(debitNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDebitNote(debitNote: DebitNote) {
    console.log("saving creditNote" + debitNote.id);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/billing/debitNotes/' + debitNote.id, JSON.stringify(debitNote), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

}
