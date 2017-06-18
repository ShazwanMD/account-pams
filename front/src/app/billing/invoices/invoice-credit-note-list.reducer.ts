import {Action} from '@ngrx/store';
import {InvoiceActions} from './invoice.action';
import {CreditNote} from '../credit-notes/credit-note.interface';

export type InvoiceCreditNoteListState = CreditNote[];

const initialState: InvoiceCreditNoteListState = <CreditNote[]>[];

export function invoiceCreditNoteListReducer(state = initialState, action: Action): InvoiceCreditNoteListState {
  switch (action.type) {
    case InvoiceActions.FIND_CREDIT_NOTES_BY_INVOICE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
