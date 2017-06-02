import {Action} from '@ngrx/store';
import {DebitNote} from "./debit-note.interface";
import {DebitNoteActions} from "./debit-note.action";

export type DebitNoteState = DebitNote;

const initialState: DebitNoteState = <DebitNoteState>{};

export function debitNoteReducer(state = initialState, action: Action): DebitNoteState {
  switch (action.type) {
    case DebitNoteActions.FIND_DEBIT_NOTE_BY_ID_SUCCESS: {
      return action.payload;
    }
    case DebitNoteActions.FIND_DEBIT_NOTE_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }

    default: {
      return state;
    }
  }
}
