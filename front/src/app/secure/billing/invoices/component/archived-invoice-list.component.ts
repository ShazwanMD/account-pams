import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-archived-invoice-list',
  templateUrl: './archived-invoice-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedInvoiceListComponent {

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() invoices: Invoice[];
  @Output() view = new EventEmitter<Invoice>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewInvoice(invoice: Invoice): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing invoice', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(invoice);
    });
  }
}
