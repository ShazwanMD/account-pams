import {Action} from '@ngrx/store';
import { DebitNote } from "../debit-notes/debit-note.interface";
import { DebitNoteActions } from "../debit-notes/debit-note.action";

export type DebitNoteListState = DebitNote[];

const initialState: DebitNoteListState = <DebitNote[]>[];

export function debitNoteListReducer(state = initialState, action: Action): DebitNoteListState {
  switch (action.type) {
    case DebitNoteActions.FIND_DEBIT_NOTES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
