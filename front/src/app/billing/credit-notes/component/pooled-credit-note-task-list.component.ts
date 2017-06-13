import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {CreditNote} from "../credit-note.interface";
import {MdSnackBar} from "@angular/material";
import { CreditNoteTask } from "../credit-note-task.interface";

@Component({
  selector: 'pams-pooled-credit-note-task-list',
  templateUrl: './pooled-credit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledCreditNoteTaskListComponent {

  @Input() creditNoteTasks: CreditNoteTask[];
  @Output() claim = new EventEmitter<CreditNoteTask>();

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

 claimTask(task: CreditNoteTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Claiming invoice", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }
}
