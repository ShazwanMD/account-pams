import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceTask} from "../invoice-task.interface";
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";
import {Invoice} from "../invoice.interface";

@Component({
  selector: 'pams-archived-invoice-list',
  templateUrl: './archived-invoice-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedInvoiceListComponent {

  @Input() invoices: Invoice[];
  @Output() view = new EventEmitter<Invoice>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'flowState', label: 'Status'},
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
