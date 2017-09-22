import {Action} from '@ngrx/store';
import {DebitNoteActions} from './debit-note.action';
import {DebitNote} from '../../../shared/model/billing/debit-note.interface';

export type DebitNoteListState = DebitNote[];

const initialState: DebitNoteListState = <DebitNote[]>[];

export function archivedDebitNoteListReducer(state = initialState, action: Action): DebitNoteListState {
  switch (action.type) {
    case DebitNoteActions.FIND_ARCHIVED_DEBIT_NOTES_SUCCESS: {
      return action.payload;
    }
    case DebitNoteActions.FIND_DEBIT_NOTES_BY_INVOICE_SUCCESS: {
      return action.payload;
    }

    default: {
      return state;
    }
  }
}

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

export function dbtListReducer(state = initialState, action: Action): DebitNoteListState {
    switch (action.type) {
      case DebitNoteActions.FIND_UNPAID_DEBIT_NOTES_SUCCESS: {
          console.log("try jupo dh")
          return action.payload;
        }
      default: {
        return state;
      }
    }
  }
