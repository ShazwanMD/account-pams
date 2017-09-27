//import { DebitNoteReceiptCreatorDialog } from './../dialog/debit-note-receipt-creator.dialog';
//import { DebitNoteReceiptDialog } from './../dialog/debit-note-receipt.dialog';
import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import { ActivatedRoute, Router } from '@angular/router';
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { WaiverDebitNote } from './../../../../shared/model/billing/waiver-debit-note.interface';
import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { AccountActions } from '../../../account/accounts/account.action';
import { AccountModuleState } from '../../../account/index';
import { DebitNoteWaiverDialog } from "../dialog/debit-note-waiver.dialog";
import { DebitNoteWaiverCreatorDialog } from "../dialog/debit-note-waiver-creator.dialog";

@Component({
  selector: 'pams-debit-note-waiver-list',
  templateUrl: './debit-note-waiver-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteWaiverListComponent {

    @Input() waiverDebitNote: WaiverDebitNote[];
    @Input() waiverFinanceApplication: WaiverFinanceApplication;
    @Output() view = new EventEmitter<WaiverDebitNote>();
    
    //private selectedRows: ReceiptInvoice[];

     private editorDialogRef: MdDialogRef<DebitNoteWaiverDialog>;
    
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
              private action: WaiverFinanceApplicationActions,
              private dialog: MdDialog) {
  }
  
  viewTask(waiverDebitNote: WaiverDebitNote) {

      console.log('ref no for receipt: ' + waiverDebitNote.debitNote.referenceNo);
      this.showDialog(waiverDebitNote);
      
    }

    ngOnInit(): void {
        //this.selectedRows = this.receiptInvoice.filter((value) => value.selected);
      }

   showDialog(waiverDebitNote: WaiverDebitNote) {
        console.log("DebitNote for create item dialog "+ waiverDebitNote.debitNote.referenceNo);
        console.log("WF for create item dialog "+ waiverDebitNote.waiverApplication);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.viewContainerRef;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '60%';
        config.position = {top: '0px'};
        let editorDialogRef = this.dialog.open(DebitNoteWaiverCreatorDialog, config);
        editorDialogRef.componentInstance.waiverFinanceApplication = this.waiverFinanceApplication;
        editorDialogRef.componentInstance.debitNote = waiverDebitNote.debitNote;
      }

      create(): void {
      let config: MdDialogConfig = new MdDialogConfig();
      config.viewContainerRef = this.viewContainerRef;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '60%';
      config.position = {top: '65px'};
      this.editorDialogRef = this.dialog.open(DebitNoteWaiverDialog, config);
      this.editorDialogRef.componentInstance.waiverFinanceApplication = this.waiverFinanceApplication;
      this.editorDialogRef.afterClosed().subscribe((res) => {
        // no op
      });
    }
}

