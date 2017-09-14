import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { KnockoffInvoice } from "../../../../shared/model/billing/knockoff-invoice.interface";
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";

@Component({
  selector: 'pams-knockoff-invoice-list',
  templateUrl: './knockoff-invoice-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class KnockoffInvoiceListComponent {

    @Input() knockoffInvoice: KnockoffInvoice[];
    @Input() knockoff: Knockoff;
    @Output() view = new EventEmitter<KnockoffInvoice>();
    
  private columns: any[] = [
    {name: 'invoice.referenceNo', label: 'Reference No'},
    {name: 'invoice.description', label: 'Description'},
    {name: 'invoice.totalAmount', label: 'Total Amount'},
    {name: 'invoice.balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  constructor(private snackBar: MdSnackBar,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private action: InvoiceActions,
              private dialog: MdDialog) {
  }
  
//  viewTask(receiptInvoice: ReceiptInvoice) {
//
//      console.log('ref no for receipt: ' + receiptInvoice.receipt.referenceNo);
//      this.showDialog(receiptInvoice);
//      
//    }
//    
//    showDialog(receiptInvoice: ReceiptInvoice) {
//        console.log("Receipt for create item dialog "+ receiptInvoice.receipt.referenceNo);
//        console.log("Invoice for create item dialog "+ receiptInvoice.invoice.referenceNo);
//        let config = new MdDialogConfig();
//        config.viewContainerRef = this.viewContainerRef;
//        config.role = 'dialog';
//        config.width = '70%';
//        config.height = '60%';
//        config.position = {top: '0px'};
//        let editorDialogRef = this.dialog.open(InvoiceApplicatorDialog, config);
//        editorDialogRef.componentInstance.receipt = receiptInvoice.receipt;
//        editorDialogRef.componentInstance.invoice = receiptInvoice.invoice;
//      }
  

}
