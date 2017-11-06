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
import { InvoiceReceiptDialog } from "../dialog/invoice-receipt.dialog";
import { Router, ActivatedRoute } from "@angular/router";
import { ReceiptActions } from "../receipt.action";
import { TdDialogService } from "@covalent/core";

@Component({
  selector: 'pams-invoice-receipt-list',
  templateUrl: './invoice-receipt-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceReceiptListComponent {

    @Input() receiptInvoice: ReceiptInvoice[];
    @Input() receipt: Receipt;
    @Output() view = new EventEmitter<ReceiptInvoice>();
    
  private columns: any[] = [
    {name: 'invoice.referenceNo', label: 'Reference No'},
    {name: 'invoice.description', label: 'Description'},
    {name: 'invoice.totalAmount', label: 'Total Amount'},
    {name: 'invoice.balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];
    
    private selectedRows: ReceiptInvoice[];

  constructor(private snackBar: MdSnackBar,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private router: Router,
              private route: ActivatedRoute,
              private action: ReceiptActions,
              private _dialogService: TdDialogService,
              private dialog: MdDialog) {
  }
  
  viewTask(receiptInvoice: ReceiptInvoice) {

      console.log('ref no for receipt: ' + receiptInvoice.receipt.referenceNo);
      this.showDialog(receiptInvoice);
    }
    
    showDialog(receiptInvoice: ReceiptInvoice) {
        console.log("Receipt for create item dialog "+ receiptInvoice.receipt.referenceNo);
        console.log("Invoice for create item dialog "+ receiptInvoice.invoice.referenceNo);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.viewContainerRef;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '60%';
        config.position = {top: '0px'};
        let editorDialogRef = this.dialog.open(InvoiceApplicatorDialog, config);
        editorDialogRef.componentInstance.receipt = receiptInvoice.receipt;
        editorDialogRef.componentInstance.invoice = receiptInvoice.invoice;
        editorDialogRef.afterClosed().subscribe((res) => {
          this.route.params.subscribe((params: { taskId: string }) => {
            let taskId: string = params.taskId;
            this.store.dispatch(this.action.findReceiptTaskByTaskId(taskId));
         });
         });
      }
  
    create() {
        console.log("ReceiptDialog "+ this.receipt.referenceNo);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.viewContainerRef;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '60%';
        config.position = {top: '0px'};
        let editorDialogRef = this.dialog.open(InvoiceReceiptDialog, config);
        editorDialogRef.componentInstance.receipt = this.receipt;
      }
    
    ngOnInit(): void {
        this.selectedRows = this.receiptInvoice.filter((value) => value.selected);
      }

    delete(): void {
        console.log('length: ' + this.selectedRows.length);
        for (let i: number = 0; i < this.selectedRows.length; i++) {
          this.store.dispatch(this.action.deleteReceiptInvoices(this.selectedRows[i]));
          console.log("selected rows receipt invoice" + this.selectedRows[i].invoice.referenceNo)
        }
        this.selectedRows = [];
      }

      selectRow(receiptInvoice: ReceiptInvoice): void {
      }

      selectAllRows(receiptInvoice: ReceiptInvoice[]): void {
      }

}
