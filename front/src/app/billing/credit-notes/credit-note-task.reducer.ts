import {Action} from '@ngrx/store';
import {CreditNoteActions} from './credit-note.action';
import {CreditNoteTask} from '../../shared/model/billing/credit-note-task.interface';

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
