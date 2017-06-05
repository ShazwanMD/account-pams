import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import { DebitNote } from "../../debit-notes/debit-note.interface";
import { Invoice } from "../invoice.interface";

@Component({
  selector: 'pams-invoice-debit-note-list',
  templateUrl: './invoice-debit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceDebitNoteListComponent {
  @Input() invoice: Invoice;
  @Input() debitNotes: DebitNote[];

private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

}