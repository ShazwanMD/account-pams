import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceTask} from "../invoice-task.interface";
import { Observable } from "rxjs/Observable";
import { MdDialogRef, MdDialog, MdDialogConfig } from "@angular/material";
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../invoice.action";
import { InvoiceTaskEditorDialog } from "../dialog/invoice-task-editor.dialog";

@Component({
  selector: 'pams-invoice-task-list',
  templateUrl: './invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceTaskListComponent {

  @Input() invoiceTasks: InvoiceTask[];
  @Output() view = new EventEmitter<InvoiceTask>();

  private INVOICES = "billingModuleState.invoiceTask".split(".");
  invoices$: Observable<InvoiceTask[]>;
  private editorDialogRef: MdDialogRef<InvoiceTaskEditorDialog>;
  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];
  
  constructor(private store: Store<BillingModuleState>,
          private actions: InvoiceActions,
          private vcf: ViewContainerRef,
          private dialog: MdDialog) {
      this.invoices$ = this.store.select(...this.INVOICES);
    }
    
    editDialog(invoice: InvoiceTask): void {
    this.showDialog(invoice);
    }
    
    delete(invoice: InvoiceTask): void {
    // this.store.dispatch(this.actions.de)
    }
    
    filter(): void {
    }
    
    private showDialog(invoice: InvoiceTask): void {
        console.log("create");
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '70%';
        config.height = '65%';
        config.position = {top: '0px'};
        this.editorDialogRef = this.dialog.open(InvoiceTaskEditorDialog, config);
        if (invoice) this.editorDialogRef.componentInstance.invoice = invoice; // set
        this.editorDialogRef.afterClosed().subscribe(res => {
          console.log("close dialog");
        });
    }
}
