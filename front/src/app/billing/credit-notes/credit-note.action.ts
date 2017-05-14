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

  static FIND_CREDIT_NOTES_SUCCESS = '[DebitNote] Find DebitNotes Success';
  findCreditNotesSuccess(creditNote): Action {
    console.log("findCreditNotesSuccess");
    console.log("CreditNote: " + creditNote.length);
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES_SUCCESS,
      payload: creditNote
    };
  }

  static FIND_CREDIT_NOTE = '[CreditNote] Find CreditNote';
  findCreditNote(code): Action {
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE,
      payload: code
    };
  }

  static FIND_CREDIT_NOTE_SUCCESS = '[DebitNote] Find DebitNote Success';
  findCreditNoteSuccess(creditNote): Action {
    console.log("findCreditNoteSuccess");
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_SUCCESS,
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
