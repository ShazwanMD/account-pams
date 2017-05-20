import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceTask} from "../invoice-task.interface";
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";

@Component({
  selector: 'pams-pooled-invoice-task-list',
  templateUrl: './pooled-invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledInvoiceTaskListComponent {

  @Input() invoiceTasks: InvoiceTask[];
  @Output() claim = new EventEmitter<InvoiceTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  claimTask(task: InvoiceTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Claiming invoice", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }
}
