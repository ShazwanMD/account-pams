import {Action} from '@ngrx/store';
import {CreditNote} from "../credit-notes/credit-note.interface";
import {CreditNoteActions} from "../credit-notes/credit-note.action";

export type CreditNoteListState = CreditNote[];

const initialState: CreditNoteListState = <CreditNote[]>[];

export function archivedCreditNoteListReducer(state = initialState, action: Action): CreditNoteListState {
  switch (action.type) {
    case CreditNoteActions.FIND_ARCHIVED_CREDIT_NOTES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
