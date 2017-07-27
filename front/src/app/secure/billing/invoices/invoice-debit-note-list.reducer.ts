import {Action} from '@ngrx/store';
import {InvoiceActions} from './invoice.action';
import {DebitNote} from '../../../shared/model/billing/debit-note.interface';

export type InvoiceDebitNoteListState = DebitNote[];

const initialState: InvoiceDebitNoteListState = <DebitNote[]>[];

export function invoiceDebitNoteListReducer(state = initialState, action: Action): InvoiceDebitNoteListState {
  switch (action.type) {
    case InvoiceActions.FIND_DEBIT_NOTES_BY_INVOICE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
