import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../shared/model/billing/invoice.interface';
import {Account} from '../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';

@Component({
  selector: 'pams-invoice-paid-list',
  templateUrl: './invoice-paid-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoicePaidListComponent {

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];
  private creatorDialogRef: MdDialogRef<InvoiceApplicatorDialog>;

  @Input() invoices: Invoice[];
  @Output() apply: EventEmitter<Invoice> = new EventEmitter<Invoice>();
  @Input() account: Account;

  constructor(private snackBar: MdSnackBar,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    // no op
  }

  showApplyInvoiceDialog(invoice: Invoice): void {
    console.log('showDialog');
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(InvoiceApplicatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}
