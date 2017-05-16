import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceTask} from "../invoice-task.interface";
import { Observable } from "rxjs/Observable";
import { MdDialogRef, MdDialog, MdDialogConfig } from "@angular/material";
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../invoice.action";

@Component({
  selector: 'pams-pooled-invoice-task-list',
  templateUrl: './pooled-invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledInvoiceTaskListComponent {

  @Input() invoiceTasks: InvoiceTask[];
  @Output() claim = new EventEmitter<InvoiceTask>();

  constructor() {
  }

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];
}
