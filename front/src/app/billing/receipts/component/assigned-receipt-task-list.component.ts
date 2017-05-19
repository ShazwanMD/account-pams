import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {ReceiptTask} from "../receipt-task.interface";
import {MdSnackBar} from "@angular/material";

@Component({
  selector: 'pams-assigned-receipt-task-list',
  templateUrl: './assigned-receipt-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedReceiptTaskListComponent {

  @Input() receiptTasks: ReceiptTask[];
  @Output() view = new EventEmitter<ReceiptTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  viewTask(task: ReceiptTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing receipt", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
