import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {DebitNote} from '../../../../shared/model/billing/debit-note.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-invoice-debit-note-list',
  templateUrl: './invoice-debit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceDebitNoteListComponent {
  private columns: any[] = [
    {name: 'debitNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'chargeCode.description', label: 'Charge Code'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'createdDate', label: 'Created Date'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() invoice: Invoice;
  @Input() debitNotes: DebitNote[];

}
