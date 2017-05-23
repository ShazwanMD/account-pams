import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class DebitNoteActions {

  static FIND_DEBIT_NOTES = '[DebitNote] Find DebitNotes';
  findDebitNotes(invoice): Action {
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES,
      payload: invoice
    };
  }

  static FIND_DEBIT_NOTES_SUCCESS = '[DebitNote] Find DebitNotes Success';
  findDebitNotesSuccess(debitNotes): Action {
    console.log("findDebitNotesSuccess");
    console.log("DebitNotes: " + debitNotes.length);
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES_SUCCESS,
      payload: debitNotes
    };
  }
  
  static FIND_DEBIT_NOTE_BY_ID = '[DebitNote] Find DebitNote By Id';

  findDebitNoteById(id): Action {
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_BY_ID,
      payload: id
    };
  }

  static FIND_DEBIT_NOTE_BY_ID_SUCCESS = '[DebitNote] Find DebitNote By Id Success';

  findDebitNoteByIdSuccess(debitNote): Action {
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_BY_ID_SUCCESS,
      payload: debitNote
    };
  }

  static FIND_DEBIT_NOTES_BY_REFERENCE_NO = '[DebitNote] Find DebitNotes By reference No';

  findDebitNoteByReferenceNo(referenceNo): Action {
    console.log("findDebitNoteByReferenceNo");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_DEBIT_NOTES_BY_REFERENCE_NO_SUCCESS = '[DebitNote] Find DebitNotes By reference No Success';

  findDebitNoteByReferenceNoSuccess(debitNote): Action {
    console.log("findDebitNoteByReferenceNoSuccess");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES_BY_REFERENCE_NO_SUCCESS,
      payload: debitNote
    };
  }
  
  static FIND_DEBIT_NOTE_TASK_BY_TASK_ID = '[DebitNote] Find DebitNote Task By Task Id';

  findDebitNoteTaskByTaskId(taskId): Action {
    console.log("findDebitNoteTaskByTaskId");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_TASK_BY_TASK_ID,
      payload: taskId
    };
  }

  static FIND_DEBIT_NOTE_TASK_BY_TASK_ID_SUCCESS = '[DebitNote] Find DebitNote Task By Task Id Success';

  findDebitNoteTaskByTaskIdSuccess(task): Action {
    console.log("findDebitNoteTaskByTaskIdSuccess");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_TASK_BY_TASK_ID_SUCCESS,
      payload: task
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

//  static REMOVE_DEBIT_NOTE = '[DebitNote] Remove DebitNote';
//  removeDebitNote(debitNote): Action {
//    return {
//      type: DebitNoteActions.REMOVE_DEBIT_NOTE,
//      payload: debitNote
//    };
//  }
//
//  static REMOVE_DEBIT_NOTE_SUCCESS = '[DebitNote] Remove DebitNote Success';
//  removeDebitNoteSuccess(debitNote): Action {
//    return {
//      type: DebitNoteActions.REMOVE_DEBIT_NOTE_SUCCESS,
//      payload: debitNote
//    };
//  }
}
