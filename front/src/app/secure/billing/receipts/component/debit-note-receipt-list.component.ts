import { DebitNoteReceiptCreatorDialog } from './../dialog/debit-note-receipt-creator.dialog';
import { DebitNoteReceiptDialog } from './../dialog/debit-note-receipt.dialog';
import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import { ActivatedRoute, Router } from '@angular/router';
import { ReceiptActions } from './../receipt.action';
import { ReceiptDebitNote } from './../../../../shared/model/billing/receipt-debit_note.interface';
import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { Receipt } from "../../../../shared/model/billing/receipt.interface";
import { InvoiceReceiptCreatorDialog } from "../dialog/invoice-receipt-creator.dialog";
import { AccountActions } from '../../../account/accounts/account.action';
import { AccountModuleState } from '../../../account/index';

@Component({
  selector: 'pams-debit-note-receipt-list',
  templateUrl: './debit-note-receipt-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteReceiptListComponent {

    @Input() receiptDebitNote: ReceiptDebitNote[];
    @Input() receipt: Receipt;
    @Output() view = new EventEmitter<ReceiptDebitNote>();
    
    //private selectedRows: ReceiptInvoice[];

     private editorDialogRef: MdDialogRef<DebitNoteReceiptDialog>;
    
  private columns: any[] = [
    {name: 'debitNote.referenceNo', label: 'Reference No'},
    {name: 'debitNote.description', label: 'Description'},
    {name: 'debitNote.totalAmount', label: 'Total Amount'},
    {name: 'debitNote.balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  constructor(private snackBar: MdSnackBar,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private router: Router,
              private route: ActivatedRoute,
              private action: ReceiptActions,
              private dialog: MdDialog) {
  }
  
  viewTask(receiptDebitNote: ReceiptDebitNote) {

      console.log('ref no for receipt: ' + receiptDebitNote.receipt.referenceNo);
      this.showDialog(receiptDebitNote);
      
    }

    ngOnInit(): void {
        //this.selectedRows = this.receiptInvoice.filter((value) => value.selected);
      }
    
//    delete(): void {
//        console.log('length: ' + this.selectedRows.length);
//        for (let i: number = 0; i < this.selectedRows.length; i++) {
//          this.store.dispatch(this.action.dele);
//        }
//        this.selectedRows = [];
//      }

   showDialog(receiptDebitNote: ReceiptDebitNote) {
        console.log("Receipt for create item dialog "+ receiptDebitNote.receipt.referenceNo);
        console.log("DebitNote for create item dialog "+ receiptDebitNote.debitNote.referenceNo);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.viewContainerRef;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '60%';
        config.position = {top: '0px'};
        let editorDialogRef = this.dialog.open(DebitNoteReceiptCreatorDialog, config);
        editorDialogRef.componentInstance.receipt = receiptDebitNote.receipt;
        editorDialogRef.componentInstance.debitNote = receiptDebitNote.debitNote;
      }

      create(): void {
      let config: MdDialogConfig = new MdDialogConfig();
      config.viewContainerRef = this.viewContainerRef;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '60%';
      config.position = {top: '65px'};
      this.editorDialogRef = this.dialog.open(DebitNoteReceiptDialog, config);
      this.editorDialogRef.componentInstance.receipt = this.receipt;
      this.editorDialogRef.afterClosed().subscribe((res) => {
        // no op
      });
    }
}

