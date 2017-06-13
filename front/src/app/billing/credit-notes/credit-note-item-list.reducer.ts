import {Action} from '@ngrx/store';
import {CreditNoteActions} from "./credit-note.action";
import {CreditNoteItem} from "./credit-note-item.interface";

export type CreditNoteItemListState = CreditNoteItem[];

const initialState: CreditNoteItemListState = <CreditNoteItem[]>[];

export function creditNoteItemListReducer(state = initialState, action: Action): CreditNoteItemListState {
  switch (action.type) {
    // todo(ashraf: bukak balik bila dah siap)
    case CreditNoteActions.FIND_CREDIT_NOTE_ITEMS_SUCCESS: {
    return action.payload;
     }
    default: {
      return state;
    }
  }
}
