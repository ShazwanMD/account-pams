import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdSnackBar} from "@angular/material";
import {DebitNote} from "../debit-note.interface";

@Component({
  selector: 'pams-archived-debit-list',
  templateUrl: './archived-debit-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedDebitNoteListComponent {

  @Input() debits: DebitNote[];
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
