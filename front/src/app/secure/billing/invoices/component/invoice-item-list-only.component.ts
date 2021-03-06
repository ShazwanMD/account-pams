import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {InvoiceItemEditorDialog} from '../dialog/invoice-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {InvoiceActions} from '../invoice.action';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-invoice-item-list-only',
  templateUrl: './invoice-item-list-only.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceItemListOnlyComponent implements OnInit {

  private selectedRows: InvoiceItem[];
  private editorDialogRef: MdDialogRef<InvoiceItemEditorDialog>;
  private columns: any[] = [
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'chargeCode.description', label: 'Charge Code Description'},
    {name: 'chargeCode.taxCode.code', label: 'Tax Code'},
    {name: 'chargeCode.taxCode.taxRate', label: 'Tax Rate'},
    {name: 'chargeCode.inclusive', label: 'Inclusive'},
    {name: 'amount', label: 'Amount'},
    {name: 'taxAmount', label: 'Tax Amount'},
    {name: 'netAmount', label: 'Net Amount'},
    {name: 'action', label: ''},
  ];

  @Input() invoice: Invoice;
  @Input() invoiceItems: InvoiceItem[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: InvoiceActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.invoiceItems.filter((value) => value.selected);
  }

  createDialog(): void {
    this.showDialog(null);
  }

  edit(invoiceItem: InvoiceItem): void {
    this.showDialog(invoiceItem);
  }

  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i: number = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.deleteInvoiceItem(this.invoice, this.selectedRows[i]));
    }
    this.selectedRows = [];
  }

  filter(): void {
  }

  selectRow(invoiceItem: InvoiceItem): void {
  }

  selectAllRows(invoiceItems: InvoiceItem[]): void {
  }

  showDialog(invoiceItem: InvoiceItem): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(InvoiceItemEditorDialog, config);
    this.editorDialogRef.componentInstance.invoice = this.invoice;
    if (invoiceItem) this.editorDialogRef.componentInstance.invoiceItem = invoiceItem; // set
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}
