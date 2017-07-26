import {Action} from '@ngrx/store';
import {DebitNoteActions} from './debit-note.action';
import {DebitNoteItem} from '../../shared/model/billing/debit-note-item.interface';

export type DebitNoteItemListState = DebitNoteItem[];

const initialState: DebitNoteItemListState = <DebitNoteItem[]>[];

export function debitNoteItemListReducer(state = initialState, action: Action): DebitNoteItemListState {
  switch (action.type) {
    case DebitNoteActions.FIND_DEBIT_NOTE_ITEMS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
