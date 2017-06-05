import {Action} from '@ngrx/store';
import {DebitNoteActions} from "./debit-note.action";
import {DebitNoteItem} from "./debit-note-item.interface";

export type DebitNoteItemListState = DebitNoteItem[];

const initialState: DebitNoteItemListState = <DebitNoteItem[]>[];

export function debitNoteItemListReducer(state = initialState, action: Action): DebitNoteItemListState {
  switch (action.type) {
    //todo(ashraf: bukak balik bila dah siap)
    //console.log("debit note");
    case DebitNoteActions.FIND_DEBIT_NOTE_ITEMS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
