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
import { InvoiceReceiptCreatorDialog } from "../dialog/invoice-receipt-creator.dialog";
import {ReceiptAccountCharge} from "../../../../shared/model/billing/receipt-account-charge.interface";
import { AccountActions } from '../../../account/accounts/account.action';
import { AccountModuleState } from '../../../account/index';

@Component({
  selector: 'pams-account-charge-receipt-list',
  templateUrl: './account-charge-receipt-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeReceiptListComponent {

    @Input() receiptAccountCharge: ReceiptAccountCharge[];
    @Input() receipt: Receipt;
    @Output() view = new EventEmitter<ReceiptAccountCharge>();
    
  private columns: any[] = [
    {name: 'accountCharge.referenceNo', label: 'Reference No'},
    {name: 'accountCharge.description', label: 'Description'},
    {name: 'accountCharge.totalAmount', label: 'Total Amount'},
    {name: 'accountCharge.balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  constructor(private snackBar: MdSnackBar,
              private viewContainerRef: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private action: AccountActions,
              private dialog: MdDialog) {
  }
  
  viewTask(receiptAccountCharge: ReceiptAccountCharge) {

      console.log('ref no for receipt: ' + receiptAccountCharge.receipt.referenceNo);
    //   this.showDialog(receiptAccountCharge);
      
    }
    
    // showDialog(receiptAccountCharge: ReceiptAccountCharge) {
    //     console.log("Receipt for create item dialog "+ receiptAccountCharge.receipt.referenceNo);
    //     console.log("Invoice for create item dialog "+ receiptAccountCharge.accountCharge.referenceNo);
    //     let config = new MdDialogConfig();
    //     config.viewContainerRef = this.viewContainerRef;
    //     config.role = 'dialog';
    //     config.width = '70%';
    //     config.height = '60%';
    //     config.position = {top: '0px'};
    //     let editorDialogRef = this.dialog.open(InvoiceApplicatorDialog, config);
    //     editorDialogRef.componentInstance.receipt = receiptAccountCharge.receipt;
    //     editorDialogRef.componentInstance.invoice = receiptAccountCharge.invoice;
    //   }
  

}
