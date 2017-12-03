import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {CreditNote} from '../../../../shared/model/billing/credit-note.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-invoice-credit-note-list',
  templateUrl: './invoice-credit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceCreditNoteListComponent {
  private columns: any[] = [
    {name: 'creditNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'createdDate', label: 'Created Date'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() invoice: Invoice;
  @Input() creditNotes: CreditNote[];

}
