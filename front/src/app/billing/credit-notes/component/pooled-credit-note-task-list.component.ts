import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {CreditNote} from "../credit-note.interface";
import {MdSnackBar} from "@angular/material";

@Component({
  selector: 'pams-pooled-credit-note-task-list',
  templateUrl: './pooled-credit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledCreditNoteTaskListComponent {

  @Input() creditNoteTasks: CreditNote[];
  @Output() view = new EventEmitter<CreditNote>();

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

  viewTask(task: CreditNote): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing credit note", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}