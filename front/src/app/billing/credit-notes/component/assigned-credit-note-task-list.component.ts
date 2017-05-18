import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {CreditNote} from "../credit-note.interface";
import {MdSnackBar} from "@angular/material";

@Component({
  selector: 'pams-assigned-credit-note-task-list',
  templateUrl: './assigned-credit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedCreditNoteTaskListComponent {

  @Input() creditNoteTasks: CreditNote[];
  @Output() view = new EventEmitter<CreditNote>();

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

  viewTask(task: CreditNote): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing invoice", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
