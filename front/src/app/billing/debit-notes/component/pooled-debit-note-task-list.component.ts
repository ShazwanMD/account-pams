import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import { MdSnackBar } from "@angular/material";
import { DebitNote } from "../debit-note.interface";
import { DebitNoteTask } from "../debit-note-task.interface";

@Component({
  selector: 'pams-pooled-debit-note-task-list',
  templateUrl: './pooled-debit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledDebitNoteTaskListComponent {

  @Input() debitNoteTasks: DebitNoteTask[];
  @Output() view = new EventEmitter<DebitNoteTask>();

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

  viewTask(task: DebitNoteTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing debit note", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
