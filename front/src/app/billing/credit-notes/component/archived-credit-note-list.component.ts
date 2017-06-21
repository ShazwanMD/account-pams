import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdSnackBar} from "@angular/material";
import {CreditNote} from "../credit-note.interface";
import { CreditNoteTask } from "../credit-note-task.interface";

@Component({
  selector: 'pams-archived-credit-note-list',
  templateUrl: './archived-credit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedCreditNoteListComponent {

  @Input() creditNotes: CreditNoteTask[];
  @Output() view = new EventEmitter<CreditNote>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  viewCreditNote(credit: CreditNote): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing credit note", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(credit);
    });
  }
}
