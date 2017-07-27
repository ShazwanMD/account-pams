import {Action} from '@ngrx/store';
import {CreditNoteActions} from './credit-note.action';
import {CreditNote} from '../../../shared/model/billing/credit-note.interface';

export type CreditNoteListState = CreditNote[];

const initialState: CreditNoteListState = <CreditNote[]>[];

export function archivedCreditNoteListReducer(state = initialState, action: Action): CreditNoteListState {
  switch (action.type) {
    case CreditNoteActions.FIND_ARCHIVED_CREDIT_NOTES_SUCCESS: {
      return action.payload;
    }

    case CreditNoteActions.FIND_CREDIT_NOTES_BY_INVOICE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
