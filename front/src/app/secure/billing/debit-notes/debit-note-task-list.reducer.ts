import {Action} from '@ngrx/store';
import {DebitNoteActions} from './debit-note.action';
import {DebitNoteTask} from '../../../shared/model/billing/debit-note-task.interface';

export type DebitNoteTaskListState = DebitNoteTask[];

const initialState: DebitNoteTaskListState = <DebitNoteTask[]>[];

export function assignedDebitNoteTaskListReducer(state = initialState, action: Action): DebitNoteTaskListState {
  switch (action.type) {
    case DebitNoteActions.FIND_ASSIGNED_DEBIT_NOTE_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledDebitNoteTaskListReducer(state = initialState, action: Action): DebitNoteTaskListState {
  switch (action.type) {
    case DebitNoteActions.FIND_POOLED_DEBIT_NOTE_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
