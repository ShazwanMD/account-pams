import { AccountTransaction } from './../../../../shared/model/account/account-transaction.interface';
import {ChangeDetectionStrategy, Component, Input, EventEmitter, Output, ViewContainerRef} from '@angular/core';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';
import { Router, ActivatedRoute } from "@angular/router";
import { AccountActions } from "../account.action";
import { Store } from "@ngrx/store";
import { MdDialog, MdDialogConfig, MdDialogRef } from "@angular/material";
import { AccountModuleState } from "../../index";
import { InvoiceItemDialog } from "../dialog/invoice-item.dialog";
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { AccountTransactionCode } from "../../../../shared/model/account/account-transaction-code.enum";
import { DebitNoteItemDialog } from "../dialog/debit-note-item.dialog";
import { CreditNoteItemDialog } from "../dialog/credit-note-item.dialog";

@Component({
  selector: 'pams-account-activity-list',
  templateUrl: './account-activity-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActivityListComponent {

  @Input() activity: AccountActivity[];

  transaction:any= AccountTransactionCode;

  private columns: any[] = [
    {name: 'postedDate', label: 'Date'},                        
    {name: 'sourceNo', label: 'Document No'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Amount'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
          private route: ActivatedRoute,
          private actions: AccountActions,
          private store: Store<AccountModuleState>,
          private vcf: ViewContainerRef,
          private dialog: MdDialog) {
  }

  viewInvoice(activity: AccountActivity): void {
      this.showDialog(activity);     
    }

   private editorDialogRef: MdDialogRef<InvoiceItemDialog>
   private editorDialogRef1: MdDialogRef<DebitNoteItemDialog>
   private editorDialogRef2: MdDialogRef<CreditNoteItemDialog>
  
  showDialog(activity): void {
    console.log('showDialog');
    console.log('Activity',activity);
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '70%';
    config.position = {top: '0px'}; 

    if(activity.transactionCode === this.transaction[0]){    
    this.editorDialogRef = this.dialog.open(InvoiceItemDialog, config);
    this.editorDialogRef.componentInstance.activity = activity;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
    else if(activity.transactionCode === this.transaction[4]){    
    this.editorDialogRef1 = this.dialog.open(DebitNoteItemDialog, config);
    this.editorDialogRef1.componentInstance.activity = activity;
    this.editorDialogRef1.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
    else if(activity.transactionCode === this.transaction[5]){    
    this.editorDialogRef2 = this.dialog.open(CreditNoteItemDialog, config);
    this.editorDialogRef2.componentInstance.activity = activity;
    this.editorDialogRef2.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }

  }
}
