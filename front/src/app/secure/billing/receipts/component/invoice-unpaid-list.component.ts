import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';

@Component({
  selector: 'pams-invoice-unpaid-list',
  templateUrl: './invoice-unpaid-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceUnpaidListComponent {

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
  @Input() account: Account;

  constructor(private snackBar: MdSnackBar,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    // no op
  }
  
  showApplyInvoiceDialog(invoice: Invoice): void {
      this.showDialog(invoice);     
    }

  showDialog(invoice): void {
    console.log('showDialog');
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '90%';
    config.height = '90%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(InvoiceApplicatorDialog, config);
    this.creatorDialogRef.componentInstance.invoice = invoice;
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}
