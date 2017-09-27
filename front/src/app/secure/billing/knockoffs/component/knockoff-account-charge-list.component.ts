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
import { KnockoffAccountChargeItemDialog } from '../dialog/knockoff-account-charge-item.dialog';

@Component({
  selector: 'pams-knockoff-account-charge-list',
  templateUrl: './knockoff-account-charge-list.component.html',
})
export class KnockoffAccountChargeListComponent implements OnInit {

    @Input() knockoffAccountCharge: KnockoffAccountCharge[];
    @Input() knockoff: Knockoff;
    
  private columns: any[] = [
    {name: 'accountCharge.referenceNo', label: 'Reference No'},
    {name: 'accountCharge.description', label: 'Description'},
    {name: 'accountCharge.netAmount', label: 'Total Amount'},
    {name: 'accountCharge.balanceAmount', label: 'Balance Amount'},
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
    let editorDialogRef = this.dialog.open(AccountChargeKnockoffDialog, config);
    editorDialogRef.componentInstance.knockoff = this.knockoff;
  
  }
  
  viewTask(item: KnockoffAccountCharge) {

      this.showDialog(item);
      
    }
  
  showDialog(item: KnockoffAccountCharge): void {
      console.log("knockoff create"  + item.knockoff.referenceNo);
      console.log("account charge create"  + item.accountCharge.referenceNo);
      let config = new MdDialogConfig();
      config.viewContainerRef = this.viewContainerRef;
      config.role = 'dialog';
      config.width = '70%';
      config.height = '60%';
      config.position = {top: '0px'};
      let editorDialogRef = this.dialog.open(KnockoffAccountChargeItemDialog, config);
      editorDialogRef.componentInstance.knockoff = item.knockoff;
      editorDialogRef.componentInstance.accountCharge = item.accountCharge;
    }

  

}
