import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceTask} from "../invoice-task.interface";
import {Store} from "@ngrx/store";
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";
import {Invoice} from "../invoice.interface";
import { InvoiceActions } from "../invoice.action";
import { BillingModuleState } from "../../index";
import { Observable } from "rxjs/Observable";
import {Account} from "../../../account/accounts/account.interface";

@Component({
  selector: 'pams-invoice-unpaid-list',
  templateUrl: './invoice-unpaid-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceUnpaidListComponent {

  @Input() invoices: Invoice[];
  @Output() view = new EventEmitter<Invoice>();
  @Input() account: Account;

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''}
  ];
  
  

  constructor(private snackBar: MdSnackBar) {
      
    }

  viewInvoice(invoice: Invoice): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing invoice", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(invoice);
    });
  }
  
}
