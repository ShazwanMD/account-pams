import {Action} from '@ngrx/store';
import {CreditNote} from "./credit-note.interface";
import {CreditNoteActions} from "./credit-note.action";

export type CreditNoteState = CreditNote;

const initialState: CreditNoteState = <CreditNoteState>{};

export function creditNoteReducer(state = initialState, action: Action): CreditNoteState {
  switch (action.type) {
    case CreditNoteActions.FIND_CREDIT_NOTE_BY_ID_SUCCESS: {
      return action.payload;
    }
    case CreditNoteActions.FIND_CREDIT_NOTES_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
