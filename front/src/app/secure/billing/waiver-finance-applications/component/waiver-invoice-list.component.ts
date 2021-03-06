import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {WaiverReceiptDialog} from '../dialog/invoice-waiver.dialog';
import {ItemApplicatorDialog} from '../dialog/item-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { WaiverInvoice } from "../../../../shared/model/billing/waiver-invoice.interface";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { Receipt } from "../../../../shared/model/billing/receipt.interface";
import { Router, ActivatedRoute } from "@angular/router";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";

@Component({
  selector: 'pams-waiver-invoice-list',
  templateUrl: './waiver-invoice-list.component.html',

})
export class WaiverInvoiceListComponent implements OnInit {

    @Input() waiverInvoice: WaiverInvoice[];
    @Input() waiverFinanceApplication: WaiverFinanceApplication;
    @Output() view = new EventEmitter<WaiverInvoice>();
    
    private selectedRows: WaiverInvoice[];
    
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
              private router: Router,
              private route: ActivatedRoute,
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialog) {
  }
  
  viewTask(waiverInvoice: WaiverInvoice) {

      this.showDialog(waiverInvoice);
      
    }
    
    showDialog(waiverInvoice: WaiverInvoice) {
        console.log("waiver ref no " + this.waiverFinanceApplication.referenceNo);
        console.log("waiver invoice no " + waiverInvoice.invoice.referenceNo);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.viewContainerRef;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '60%';
        config.position = {top: '0px'};
        let editorDialogRef = this.dialog.open(ItemApplicatorDialog, config);
        editorDialogRef.componentInstance.waiverFinanceApplication = this.waiverFinanceApplication;
        editorDialogRef.componentInstance.invoice = waiverInvoice.invoice;
      }
  
    create() {
        console.log("Dialog "+ this.waiverFinanceApplication.referenceNo);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.viewContainerRef;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '60%';
        config.position = {top: '0px'};
        let editorDialogRef = this.dialog.open(WaiverReceiptDialog, config);
        editorDialogRef.componentInstance.waiverFinanceApplication = this.waiverFinanceApplication;
      }

    ngOnInit(): void {
        this.selectedRows = this.waiverInvoice.filter((value) => value.selected);
      }

    delete(): void {
        console.log('length: ' + this.selectedRows.length);
        for (let i: number = 0; i < this.selectedRows.length; i++) {
          this.store.dispatch(this.actions.deleteWaiverInvoices(this.selectedRows[i]));
          console.log("selected rows receipt invoice" + this.selectedRows[i].invoice.referenceNo)
        }
        this.selectedRows = [];
      }

      selectRow(waiverInvoice: WaiverInvoice): void {
      }

      selectAllRows(waiverInvoice: WaiverInvoice[]): void {
      }
}
