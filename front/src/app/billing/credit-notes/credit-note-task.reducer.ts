import {Action} from '@ngrx/store';
import {CreditNoteTask} from "./credit-note-task.interface";
import {CreditNoteActions} from "./credit-note.action";

export type CreditNoteTaskState = CreditNoteTask;

const initialState: CreditNoteTaskState = <CreditNoteTaskState>{};

export function creditNoteTaskReducer(state = initialState, action: Action): CreditNoteTaskState {
  switch (action.type) {
    case CreditNoteActions.FIND_CREDIT_NOTE_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}