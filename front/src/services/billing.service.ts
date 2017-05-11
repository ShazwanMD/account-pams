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

@Injectable()
export class BillingService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INVOICE
  // ====================================================================================================

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
    return this.http.post(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + '/invoiceItems' , JSON.stringify(item), options)
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

  updateReceipt(receipt: Receipt): Observable<Boolean> {
    return this.http.put(environment.endpoint + '/api/billing/receipts', JSON.stringify(receipt))
      .flatMap(data => Observable.of(true));
  }
}
