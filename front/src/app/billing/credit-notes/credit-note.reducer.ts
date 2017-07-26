import {Action} from '@ngrx/store';
import {CreditNoteActions} from './credit-note.action';
import {CreditNote} from '../../shared/model/billing/credit-note.interface';

export type CreditNoteState = CreditNote;

const initialState: CreditNoteState = <CreditNoteState>{};

export function creditNoteReducer(state = initialState, action: Action): CreditNoteState {
  switch (action.type) {
    case CreditNoteActions.FIND_CREDIT_NOTE_BY_ID_SUCCESS: {
      return action.payload;
    }
    case CreditNoteActions.FIND_CREDIT_NOTE_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
