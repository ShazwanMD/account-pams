import { ReceiptDebitNote } from './../../../shared/model/billing/receipt-debit_note.interface';
import {Action} from '@ngrx/store';
import {ReceiptActions} from './receipt.action';
import {ReceiptInvoice} from '../../../shared/model/billing/receipt-invoice.interface';

export type ReceiptDebitNoteListState = ReceiptDebitNote[];

const initialState: ReceiptDebitNoteListState = <ReceiptDebitNote[]>[];

export function receiptDebitNoteListReducer(state = initialState, action: Action): ReceiptDebitNoteListState {
  switch (action.type) {
    case ReceiptActions.FIND_RECEIPTS_BY_DEBIT_NOTE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
