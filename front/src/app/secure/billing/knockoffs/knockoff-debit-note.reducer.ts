import {Action} from '@ngrx/store';
import {KnockoffActions} from './knockoff.action';
import {KnockoffDebitNote} from '../../../shared/model/billing/knockoff-debit-note.interface';

export type KnockoffDebitNoteListState = KnockoffDebitNote[];

const initialState: KnockoffDebitNoteListState = <KnockoffDebitNote[]>[];

export function knockoffDebitNoteListReducer(state = initialState, action: Action): KnockoffDebitNoteListState {
  switch (action.type) {
    case KnockoffActions.FIND_DEBIT_NOTE_BY_KNOCKOFF_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
