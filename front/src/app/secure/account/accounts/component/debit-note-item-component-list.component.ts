import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';

@Component({
  selector: 'pams-debit-note-item-component-list',
  templateUrl: './debit-note-item-component-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteItemListAccountComponent implements OnInit {

  @Input() debitNotes: DebitNote[];

private columns: any[] = [
    {name: 'debitNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'chargeCode.description', label: 'Charge Code'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  ngOnInit(): void {
      
  }

}
