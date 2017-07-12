import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {CreditNote} from '../../credit-notes/credit-note.interface';
import {Invoice} from '../invoice.interface';

@Component({
  selector: 'pams-invoice-credit-note-list',
  templateUrl: './invoice-credit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceCreditNoteListComponent {
  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'creditNoteDate', label: 'Date'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() invoice: Invoice;
  @Input() creditNotes: CreditNote[];

}
