import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {ReceiptTask} from "../receipt-task.interface";
import {Observable} from "rxjs/Observable";
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";
import {Store} from "@ngrx/store";
import {BillingModuleState} from "../../index";
import {ReceiptActions} from "../receipt.action";

@Component({
  selector: 'pams-pooled-receipt-task-list',
  templateUrl: './pooled-receipt-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledReceiptTaskListComponent {

  @Input() receiptTasks: ReceiptTask[];
  @Output() claim = new EventEmitter<ReceiptTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  claimTask(task: ReceiptTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Claiming receipt", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }
}
