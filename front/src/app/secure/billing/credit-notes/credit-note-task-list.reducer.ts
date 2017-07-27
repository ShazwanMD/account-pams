import {Action} from '@ngrx/store';
import {CreditNoteActions} from './credit-note.action';
import {CreditNoteTask} from '../../../shared/model/billing/credit-note-task.interface';

export type CreditNoteTaskListState = CreditNoteTask[];

const initialState: CreditNoteTaskListState = <CreditNoteTask[]>[];

export function assignedCreditNoteTaskListReducer(state = initialState, action: Action): CreditNoteTaskListState {
  switch (action.type) {
    case CreditNoteActions.FIND_ASSIGNED_CREDIT_NOTE_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledCreditNoteTaskListReducer(state = initialState, action: Action): CreditNoteTaskListState {
  switch (action.type) {
    case CreditNoteActions.FIND_POOLED_CREDIT_NOTE_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
