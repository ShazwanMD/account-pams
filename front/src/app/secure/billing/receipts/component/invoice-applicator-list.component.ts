import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { InvoiceActions } from "../../invoices/invoice.action";

@Component({
  selector: 'pams-invoice-applicator-list',
  templateUrl: './invoice-applicator-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceApplicatorListComponent implements OnInit {

    //@Input() invoice: Invoice;
  @Input() invoiceItems: InvoiceItem[];
  private selectedRows: InvoiceItem[];

  private columns: any[] = [
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'chargeCode.description', label: 'Charge Code Description'},
    {name: 'chargeCode.taxCode.code', label: 'Tax Code'},
    {name: 'chargeCode.taxCode.taxRate', label: 'Tax Rate'},
    {name: 'chargeCode.inclusive', label: 'Inclusive'},
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''},
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: InvoiceActions,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.invoiceItems.filter((value) => value.selected);
      //this.store.dispatch(this.actions.findInvoiceItems(this.invoice));
  }

//  delete(): void {
//    console.log('length: ' + this.selectedRows.length);
//    for (let i: number = 0; i < this.selectedRows.length; i++) {
//      //this.store.dispatch(this.actions.deleteInvoiceItem(this.invoice, this.selectedRows[i]));
//    }
//    this.selectedRows = [];
//  }
//
  filter(): void {
  }

  selectRow(invoiceItem: InvoiceItem): void {
  }

  selectAllRows(invoiceItems: InvoiceItem[]): void {
  }

}
