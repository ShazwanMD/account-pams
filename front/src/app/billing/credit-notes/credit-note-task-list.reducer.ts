import { Action } from '@ngrx/store';
import { CreditNoteTask } from "./credit-note-task.interface";
import { CreditNoteActions } from "./credit-note.action";


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
