import {Action} from '@ngrx/store';
import {DebitNoteTask} from "./debit-note-task.interface";
import {DebitNoteActions} from "./debit-note.action";

export type DebitNoteTaskState = DebitNoteTask;

const initialState: DebitNoteTaskState = <DebitNoteTaskState>{};

export function debitNoteTaskReducer(state = initialState, action: Action): DebitNoteTaskState {
  switch (action.type) {
    case DebitNoteActions.FIND_DEBIT_NOTE_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}