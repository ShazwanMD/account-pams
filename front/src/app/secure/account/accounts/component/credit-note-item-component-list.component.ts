import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';
import { CreditNote } from "../../../../shared/model/billing/credit-note.interface";

@Component({
  selector: 'pams-credit-note-item-component-list',
  templateUrl: './credit-note-item-component-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreditNoteItemListAccountComponent implements OnInit {

  @Input() creditNotes: CreditNote[];

private columns: any[] = [
    {name: 'creditNoteDate', label: 'Date'},
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
