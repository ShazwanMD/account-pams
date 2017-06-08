import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdSnackBar} from "@angular/material";
import { DebitNote } from "../debit-note.interface";
import { DebitNoteTask } from "../debit-note-task.interface";

@Component({
  selector: 'pams-archived-debit-note-list',
  templateUrl: './archived-debit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedDebitNoteListComponent {

  @Input() debitNotes: DebitNoteTask[];
  @Output() view = new EventEmitter<DebitNote>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  viewDebitNote(debit: DebitNote): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing debit note", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(debit);
    });
  }
}
