import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceTask} from "../invoice-task.interface";
import {MdSnackBar} from "@angular/material";

@Component({
  selector: 'pams-assigned-invoice-task-list',
  templateUrl: './assigned-invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedInvoiceTaskListComponent {

  @Input() invoiceTasks: InvoiceTask[];
  @Output() view = new EventEmitter<InvoiceTask>();

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
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

  viewTask(task: InvoiceTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing invoice", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
