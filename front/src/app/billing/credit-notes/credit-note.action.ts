import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class CreditNoteActions {

  static FIND_CREDIT_NOTES = '[CreditNote] Find CreditNotes';

  findCreditNotes(): Action {
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES
    };
  }

  static FIND_CREDIT_NOTES_SUCCESS = '[CreditNote] Find CreditNotes Success';

  findCreditNotesSuccess(creditNotes): Action {
    console.log("findCreditNotesSuccess");
    console.log("CreditNote: " + creditNotes.length);
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES_SUCCESS,
      payload: creditNotes
    };
  }

  static FIND_CREDIT_NOTE_BY_ID = '[CreditNote] Find CreditNote By Id';

  findCreditNoteById(id): Action {
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_BY_ID,
      payload: id
    };
  }

  static FIND_CREDIT_NOTE_BY_ID_SUCCESS = '[CreditNote] Find CreditNote By Id Success';

  findCreditNoteByIdSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_BY_ID_SUCCESS,
      payload: creditNote
    };
  }

  static FIND_CREDIT_NOTES_BY_REFERENCE_NO = '[CreditNote] Find CreditNote By reference No';

  findCreditNoteByReferenceNo(referenceNo): Action {
    console.log("findCreditNoteByReferenceNo");
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_CREDIT_NOTES_BY_REFERENCE_NO_SUCCESS = '[CreditNote] Find CreditNote By reference No Success';

  findCreditNoteByReferenceNoSuccess(creditNote): Action {
    console.log("findCreditNoteByReferenceNoSuccess");
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES_BY_REFERENCE_NO_SUCCESS,
      payload: creditNote
    };
  }

  static FIND_CREDIT_NOTE_TASK_BY_TASK_ID = '[CreditNote] Find CreditNote Task By Task Id';

  findCreditNoteTaskByTaskId(taskId): Action {
    console.log("findCreditNoteTaskByTaskId");
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_TASK_BY_TASK_ID,
      payload: taskId
    };
  }

  static FIND_CREDIT_NOTE_TASK_BY_TASK_ID_SUCCESS = '[CreditNote] Find CreditNote Task By Task Id Success';

  findCreditNoteTaskByTaskIdSuccess(task): Action {
    console.log("findCreditNoteTaskByTaskIdSuccess");
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_TASK_BY_TASK_ID_SUCCESS,
      payload: task
    };
  }

  static START_CREDIT_NOTE_TASK = '[CreditNote] Start CreditNote Task';

  startCreditNoteTask(creditNote): Action {
    return {
      type: CreditNoteActions.START_CREDIT_NOTE_TASK,
      payload: creditNote
    };
  }

  static START_CREDIT_NOTE_TASK_SUCCESS = '[CreditNote] Start CreditNote Task Success';

  startCreditNoteTaskSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.START_CREDIT_NOTE_TASK_SUCCESS,
      payload: creditNote
    };
  }


  static SAVE_CREDIT_NOTE = '[CreditNote] Save CreditNote';

  saveCreditNote(creditNote): Action {
    return {
      type: CreditNoteActions.SAVE_CREDIT_NOTE,
      payload: creditNote
    };
  }

  static SAVE_CREDIT_NOTE_SUCCESS = '[CreditNote] Save CreditNote Success';

  saveCreditNoteSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.SAVE_CREDIT_NOTE_SUCCESS,
      payload: creditNote
    };
  }

  static UPDATE_CREDIT_NOTE = '[CreditNote] Update CreditNote';

  updateCreditNote(creditNote): Action {
    return {
      type: CreditNoteActions.UPDATE_CREDIT_NOTE,
      payload: creditNote
    };
  }

  static UPDATE_CREDIT_NOTE_SUCCESS = '[CreditNote] Update CreditNote Success';

  updateCreditNoteSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.UPDATE_CREDIT_NOTE_SUCCESS,
      payload: creditNote
    };
  }

  static REMOVE_CREDIT_NOTE = '[CreditNote] Remove CreditNote';

  removeCreditNote(creditNote): Action {
    return {
      type: CreditNoteActions.REMOVE_CREDIT_NOTE,
      payload: creditNote
    };
  }

  static REMOVE_CREDIT_NOTE_SUCCESS = '[CreditNote] Remove CreditNote Success';

  removeCreditNoteSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.REMOVE_CREDIT_NOTE_SUCCESS,
      payload: creditNote
    };
  }
}
