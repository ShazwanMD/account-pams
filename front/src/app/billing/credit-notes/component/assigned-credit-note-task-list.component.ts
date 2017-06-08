import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdSnackBar} from "@angular/material";
import {CreditNoteTask} from "../credit-note-task.interface";

@Component({
  selector: 'pams-assigned-credit-note-task-list',
  templateUrl: './assigned-credit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedCreditNoteTaskListComponent {

  @Input() creditNoteTasks: CreditNoteTask[];
  @Output() view = new EventEmitter<CreditNoteTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  viewTask(task: CreditNoteTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing credit note", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
