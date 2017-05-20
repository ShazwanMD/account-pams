import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {ReceiptTask} from "../receipt-task.interface";
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";

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
    {name: 'description', label: 'Description'},
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
