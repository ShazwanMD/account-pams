import { KnockoffDebitNote } from './../../../../shared/model/billing/knockoff-debit-note.interface';
import { DebitNoteKnockoffDialog } from './../dialog/knockoff-debit-note-creator.dialog';
import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { KnockoffAccountCharge } from "../../../../shared/model/billing/knockoff-account-charge.interface";
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { AccountChargeKnockoffDialog } from "../dialog/knockoff-account-charge-creator.dialog";
import { KnockoffActions } from "../knockoff.action";
import { KnockoffItemDialog } from "../dialog/knockoff-item.dialog";

@Component({
  selector: 'pams-knockoff-debit-note-list',
  templateUrl: './knockoff-debit-note-list.component.html',
})
export class KnockoffDebitNoteListComponent implements OnInit {

    @Input() knockoffDebitNote: KnockoffDebitNote[];
    @Input() knockoff: Knockoff;
    
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
              private actions: KnockoffActions,
              private dialog: MdDialog) {
  }
  
  ngOnInit(): void {

    }
  
  create(): void {
    console.log("knockoff create"  + this.knockoff.referenceNo);
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(DebitNoteKnockoffDialog, config);
    editorDialogRef.componentInstance.knockoff = this.knockoff;
  
  }
  
  // viewTask(item: KnockoffDebitNote) {

  //     this.showDialog(item);
      
  //   }
  
  // showDialog(item: KnockoffAccountCharge): void {
  //     console.log("knockoff create"  + item.knockoff.referenceNo);
  //     console.log("invoice create"  + item.accountCharge.referenceNo);
  //     let config = new MdDialogConfig();
  //     config.viewContainerRef = this.viewContainerRef;
  //     config.role = 'dialog';
  //     config.width = '70%';
  //     config.height = '60%';
  //     config.position = {top: '0px'};
  //     let editorDialogRef = this.dialog.open(KnockoffItemDialog, config);
  //     editorDialogRef.componentInstance.knockoff = item.knockoff;
  //     editorDialogRef.componentInstance.accountCharge = item.accountCharge;
  //   }

  

}
