import {ChangeDetectionStrategy, Component, Input, EventEmitter, Output, ViewContainerRef} from '@angular/core';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';
import { AccountTransaction } from "../../../../shared/model/account/account-transaction.interface";
import { Router, ActivatedRoute } from "@angular/router";
import { AccountActions } from "../account.action";
import { Store } from "@ngrx/store";
import { MdDialog, MdDialogConfig, MdDialogRef } from "@angular/material";
import { AccountModuleState } from "../../index";
import { InvoiceItemDialog } from "../dialog/invoice-item.dialog";
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-account-activity-list',
  templateUrl: './account-activity-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActivityListComponent {

  @Input() activity: AccountActivity[];

  private columns: any[] = [
    {name: 'sourceNo', label: 'Source'},
    {name: 'chargeCode.description', label: 'ChargeCode'},
    {name: 'activity.postedDate', label: 'Session'},
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
  
  showDialog(activity): void {
    console.log('showDialog');
    console.log('Activity' + activity);
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '70%';
    config.position = {top: '0px'};
    
    //if(activity.transactionCode.INVOICE)
    this.editorDialogRef = this.dialog.open(InvoiceItemDialog, config);
    this.editorDialogRef.componentInstance.activity = activity;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
    
//        if(.RECINVOICE)
//    this.editorDialogRef = this.dialog.open(ReciptViewDialog, config);
//    this.editorDialogRef.componentInstance.referenceNO = activity.sourceNo;
//    this.e... componentInstance.`invoice = this.invoice;
    //if (activity) this.editorDialogRef.componentInstance.activity = activity;

  }
}
