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
import {WaiverAccountCharge} from "../../../../shared/model/billing/waiver-account-charge.interface";
import {WaiverFinanceApplication} from "../../../../shared/model/billing/waiver-finance-application.interface";
import { AccountActions } from '../../../account/accounts/account.action';
import { AccountModuleState } from '../../../account/index';
import { AccountChargeWaiverDialog } from "../dialog/account-charge-waiver-creator.dialog";
import { WaiverChargeCreatorDialog } from "../dialog/waiver-charge-creator.dialog";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";

@Component({
  selector: 'pams-account-charge-waiver-list',
  templateUrl: './account-charge-waiver-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeWaiverListComponent {
 
    @Input() waiverAccountCharge: WaiverAccountCharge[];
    @Input() waiverFinanceApplication: WaiverFinanceApplication;
    @Output() view = new EventEmitter<WaiverAccountCharge>();
    
    private selectedRows: WaiverAccountCharge[];
    
    private editorDialogRef: MdDialogRef<AccountChargeWaiverDialog>;
    
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
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialog) {
  }
  
  viewTask(waiverAccountCharge: WaiverAccountCharge) {

      this.showDialog(waiverAccountCharge);
      
    }
    
     showDialog(waiverAccountCharge: WaiverAccountCharge) {
         let config = new MdDialogConfig();
         config.viewContainerRef = this.viewContainerRef;
         config.role = 'dialog';
         config.width = '70%';
         config.height = '60%';
         config.position = {top: '0px'};
         let editorDialogRef = this.dialog.open(WaiverChargeCreatorDialog, config);
         editorDialogRef.componentInstance.waiverFinanceApplication = this.waiverFinanceApplication;
        editorDialogRef.componentInstance.accountCharge = waiverAccountCharge.accountCharge;
       }
     
  create(): void {
      let config: MdDialogConfig = new MdDialogConfig();
      config.viewContainerRef = this.viewContainerRef;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '60%';
      config.position = {top: '65px'};
      this.editorDialogRef = this.dialog.open(AccountChargeWaiverDialog, config);
      this.editorDialogRef.componentInstance.waiverFinanceApplication = this.waiverFinanceApplication;
      this.editorDialogRef.afterClosed().subscribe((res) => {
        // no op
      });
    }
  
  ngOnInit(): void {
      this.selectedRows = this.waiverAccountCharge.filter((value) => value.selected);
    }

  delete(): void {
      console.log('length: ' + this.selectedRows.length);
      for (let i: number = 0; i < this.selectedRows.length; i++) {
        this.store.dispatch(this.actions.deleteWaiverAccCharges(this.selectedRows[i]));
      }
      this.selectedRows = [];
    }

    selectRow(waiverAccountCharge: WaiverAccountCharge): void {
    }

    selectAllRows(waiverAccountCharge: WaiverAccountCharge[]): void {
    }

}
