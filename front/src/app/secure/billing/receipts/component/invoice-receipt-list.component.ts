import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { ReceiptInvoice } from "../../../../shared/model/billing/receipt-invoice.interface";
import { Receipt } from "../../../../shared/model/billing/receipt.interface";

@Component({
  selector: 'pams-invoice-receipt-list',
  templateUrl: './invoice-receipt-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceReceiptListComponent implements OnInit {

  private columns: any[] = [
    {name: 'invoice.referenceNo', label: 'Reference No'},
    {name: 'invoice.description', label: 'Description'},
    {name: 'invoice.totalAmount', label: 'Total Amount'},
    {name: 'invoice.balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  @Input() receiptInvoice: ReceiptInvoice[];
  @Input() receipt: Receipt;

  constructor(private snackBar: MdSnackBar,
              private vcf: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private action: InvoiceActions,
              private dialog: MdDialog) {
  }
  
  ngOnInit(): void {
      
    }

}
