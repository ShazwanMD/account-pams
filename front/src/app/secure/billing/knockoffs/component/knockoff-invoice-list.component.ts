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
import { InvoiceKnockoffDialog } from "../dialog/knockoff-invoice-creator.dialog";
import { KnockoffActions } from "../knockoff.action";
import { KnockoffItemDialog } from "../dialog/knockoff-item.dialog";

@Component({
  selector: 'pams-knockoff-invoice-list',
  templateUrl: './knockoff-invoice-list.component.html',
})
export class KnockoffInvoiceListComponent implements OnInit {

    @Input() knockoffInvoice: KnockoffInvoice[];
    @Input() knockoff: Knockoff;
    
    private selectedRows: KnockoffInvoice[];
    
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
              private actions: KnockoffActions,
              private dialog: MdDialog) {
  }
  
  ngOnInit(): void {
      this.selectedRows = this.knockoffInvoice.filter((value) => value.selected);
    }
  
  create(): void {
    console.log("knockoff create"  + this.knockoff.referenceNo);
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(InvoiceKnockoffDialog, config);
    editorDialogRef.componentInstance.knockoff = this.knockoff;
  
  }
  
  viewTask(item: KnockoffInvoice) {

      this.showDialog(item);
      
    }
  
  showDialog(item: KnockoffInvoice): void {
      console.log("knockoff create"  + item.knockoff.referenceNo);
      console.log("invoice create"  + item.invoice.referenceNo);
      let config = new MdDialogConfig();
      config.viewContainerRef = this.viewContainerRef;
      config.role = 'dialog';
      config.width = '70%';
      config.height = '60%';
      config.position = {top: '0px'};
      let editorDialogRef = this.dialog.open(KnockoffItemDialog, config);
      editorDialogRef.componentInstance.knockoff = item.knockoff;
      editorDialogRef.componentInstance.invoice = item.invoice;
    }
  
  delete(): void {
      console.log('length: ' + this.selectedRows.length);
      for (let i: number = 0; i < this.selectedRows.length; i++) {
        this.store.dispatch(this.actions.deleteKnockoffInvoices(this.selectedRows[i]));
      }
      this.selectedRows = [];
  }
  
  filter(): void {
  }

  selectRow(knockoffInvoice: KnockoffInvoice): void {
  }

  selectAllRows(knockoffInvoice: KnockoffInvoice[]): void {
  }
  

}
