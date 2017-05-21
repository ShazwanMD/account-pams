import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class DebitNoteActions {

  static FIND_DEBIT_NOTES = '[DebitNote] Find DebitNotes';
  findDebitNotes(): Action {
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES
    };
  }

  static FIND_DEBIT_NOTES_SUCCESS = '[DebitNote] Find DebitNotes Success';
  findDebitNotesSuccess(debitNotes): Action {
    console.log("findChargeCodesSuccess");
    console.log("DebitNotes: " + debitNotes.length);
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES_SUCCESS,
      payload: debitNotes
    };
  }

  static FIND_DEBIT_NOTE = '[DebitNote] Find DebitNote';
  findDebitNote(code): Action {
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE,
      payload: code
    };
  }

  static FIND_DEBIT_NOTE_SUCCESS = '[DebitNote] Find DebitNote Success';
  findChargeCodeSuccess(debitNote): Action {
    console.log("findDebitNoteSuccess");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_SUCCESS,
      payload: debitNote
    };
  }

  static START_DEBIT_NOTE_TASK = '[DebitNote] Start DebitNote Task';
  startDebitNoteTask(debitNote): Action {
    return {
      type: DebitNoteActions.START_DEBIT_NOTE_TASK,
      payload: debitNote
    };
  }

  static START_DEBIT_NOTE_TASK_SUCCESS = '[DebitNote] Start DebitNote Task Success';
  startDebitNoteTaskSuccess(debitNote): Action {
    return {
      type: DebitNoteActions.START_DEBIT_NOTE_TASK_SUCCESS,
      payload: debitNote
    };
  }

  static UPDATE_DEBIT_NOTE = '[DebitNote] Update DebitNote';
  updateDebitNote(debitNote): Action {
    return {
      type: DebitNoteActions.UPDATE_DEBIT_NOTE,
      payload: debitNote
    };
  }

  static UPDATE_DEBIT_NOTE_SUCCESS = '[DebitNote] Update DebitNote Success';
  updateDebitNoteSuccess(debitNote): Action {
    return {
      type: DebitNoteActions.UPDATE_DEBIT_NOTE_SUCCESS,
      payload: debitNote
    };
  }

  static REMOVE_DEBIT_NOTE = '[DebitNote] Remove DebitNote';
  removeDebitNote(debitNote): Action {
    return {
      type: DebitNoteActions.REMOVE_DEBIT_NOTE,
      payload: debitNote
    };
  }

  static REMOVE_DEBIT_NOTE_SUCCESS = '[DebitNote] Remove DebitNote Success';
  removeDebitNoteSuccess(debitNote): Action {
    return {
      type: DebitNoteActions.REMOVE_DEBIT_NOTE_SUCCESS,
      payload: debitNote
    };
  }
}
