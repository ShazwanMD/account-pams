import {Action} from '@ngrx/store';
import {CreditNoteActions} from './credit-note.action';
import {CreditNoteItem} from '../../../shared/model/billing/credit-note-item.interface';

export type CreditNoteItemListState = CreditNoteItem[];

const initialState: CreditNoteItemListState = <CreditNoteItem[]>[];

export function creditNoteItemListReducer(state = initialState, action: Action): CreditNoteItemListState {
  switch (action.type) {
    case CreditNoteActions.FIND_CREDIT_NOTE_ITEMS_SUCCESS: {
    return action.payload;
     }
    default: {
      return state;
    }
  }
}
