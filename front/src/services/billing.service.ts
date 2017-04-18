import { Injectable } from '@angular/core';
import {Response, Http} from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
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
    console.log("encoded uri: " + encodeURI (referenceNo))
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + encodeURI (referenceNo))
      .map((res: Response) => <Invoice>res.json());
  }

  findInvoiceByTaskId(taskId: string): Observable<Invoice> {
    console.log("encoded uri: " + encodeURI (taskId))
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + encodeURI (taskId))
      .map((res: Response) => <Invoice>res.json());
  }

  findInvoiceItems(invoice:Invoice): Observable<InvoiceItem[]> {
    console.log("findInvoiceItems");
    return this.http.get(environment.endpoint + '/api/billing/invoices/' + invoice.referenceNo + "/invoiceItems")
      .map((res: Response) => <InvoiceItem[]>res.json());
  }

  startInvoiceTask(invoice: Invoice): Observable<Boolean> {
    return this.http.post(environment.endpoint + '/api/billing/invoices/startTask', JSON.stringify(invoice))
      .flatMap(data => Observable.of(true));
  }

  updateInvoice(invoice: Invoice): Observable<Boolean> {
    return this.http.put(environment.endpoint + '/api/billing/invoices', JSON.stringify(invoice))
      .flatMap(data => Observable.of(true));
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
    console.log("encoded uri: " + encodeURI (referenceNo))
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + encodeURI (referenceNo))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptByTaskId(taskId: string): Observable<Receipt> {
    console.log("encoded uri: " + encodeURI (taskId))
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + encodeURI (taskId))
      .map((res: Response) => <Receipt>res.json());
  }

  findReceiptItems(receipt:Receipt): Observable<ReceiptItem[]> {
    console.log("findReceiptItems");
    return this.http.get(environment.endpoint + '/api/billing/receipts/' + receipt.referenceNo + "/receiptItems")
      .map((res: Response) => <ReceiptItem[]>res.json());
  }

  startReceiptTask(receipt: Receipt): Observable<Boolean> {
    return this.http.post(environment.endpoint + '/api/billing/receipts/startTask', JSON.stringify(receipt))
      .flatMap(data => Observable.of(true));
  }

  updateReceipt(receipt: Receipt): Observable<Boolean> {
    return this.http.put(environment.endpoint + '/api/billing/receipts', JSON.stringify(receipt))
      .flatMap(data => Observable.of(true));
  }
}
