import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class CreditNoteActions {

  static FIND_CREDIT_NOTES = '[CreditNote] Find CreditNotes';

  findCreditNotes(creditNote): Action {
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES,
      payload: creditNote,
    };
  }

  static FIND_CREDIT_NOTES_SUCCESS = '[CreditNote] Find CreditNotes Success';

  findCreditNotesSuccess(creditNotes): Action {
    console.log('findCreditNotesSuccess');
    console.log('CreditNotes: ' + creditNotes.length);
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES_SUCCESS,
      payload: creditNotes,
    };
  }

  static FIND_CREDIT_NOTES_BY_INVOICE = '[Invoice] Find Invoice';

  findCreditNotesByInvoice(invoice): Action {
    console.log('findCreditNotesByInvoice');
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES_BY_INVOICE,
      payload: invoice,
    };
  }

  static FIND_CREDIT_NOTES_BY_INVOICE_SUCCESS = '[Intake Application] Find Invoice Success';

  findCreditNotesByInvoiceSuccess(creditNotes): Action {
    console.log('findCreditNotesByInvoiceSuccess');
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTES_BY_INVOICE_SUCCESS,
      payload: creditNotes,
    };
  }

  static FIND_COMPLETED_CREDIT_NOTES = '[CreditNote] Find Completed CreditNotes';

  findCompletedCreditNotes(): Action {
    return {
      type: CreditNoteActions.FIND_COMPLETED_CREDIT_NOTES,
    };
  }

  static FIND_COMPLETED_CREDIT_NOTES_SUCCESS = '[CreditNote] Find Completed CreditNotes Success';

  findCompletedCreditNotesSuccess(creditNotes): Action {
    console.log('findCompletedCreditNotesSuccess');
    return {
      type: CreditNoteActions.FIND_COMPLETED_CREDIT_NOTES_SUCCESS,
      payload: creditNotes,
    };
  }

  static FIND_ARCHIVED_CREDIT_NOTES = '[CreditNote] Find Archived Credit_notes';

  findArchivedCreditNotes(): Action {
    return {
      type: CreditNoteActions.FIND_ARCHIVED_CREDIT_NOTES,
    };
  }

  static FIND_ARCHIVED_CREDIT_NOTES_SUCCESS = '[CreditNote] Find Archived Credit_notes Success';

  findArchivedCreditNotesSuccess(credit_notes): Action {
    console.log('findArchivedCredit_notesSuccess');
    return {
      type: CreditNoteActions.FIND_ARCHIVED_CREDIT_NOTES_SUCCESS,
      payload: credit_notes,
    };
  }

  static FIND_ASSIGNED_CREDIT_NOTE_TASKS = '[CreditNote] Find Assigned CreditNotes Tasks';

  findAssignedCreditNoteTasks(): Action {
    return {
      type: CreditNoteActions.FIND_ASSIGNED_CREDIT_NOTE_TASKS,
    };
  }

  static FIND_ASSIGNED_CREDIT_NOTE_TASKS_SUCCESS = '[CreditNote] Find Assigned CreditNotes Tasks Success';

  findAssignedCreditNoteTasksSuccess(tasks): Action {
    console.log('findAssignedCreditNoteTasksSuccess');
    return {
      type: CreditNoteActions.FIND_ASSIGNED_CREDIT_NOTE_TASKS_SUCCESS,
      payload: tasks,
    };
  }

  static FIND_POOLED_CREDIT_NOTE_TASKS = '[CreditNote] Find Pooled CreditNotes Tasks';

  findPooledCreditNoteTasks(): Action {
    return {
      type: CreditNoteActions.FIND_POOLED_CREDIT_NOTE_TASKS,
    };
  }

  static FIND_POOLED_CREDIT_NOTE_TASKS_SUCCESS = '[CreditNote] Find Pooled CreditNotes Tasks Success';

  findPooledCreditNoteTasksSuccess(tasks): Action {
    console.log('findAssignedCreditNoteTasksSuccess');
    return {
      type: CreditNoteActions.FIND_POOLED_CREDIT_NOTE_TASKS_SUCCESS,
      payload: tasks,
    };
  }

  static FIND_CREDIT_NOTE_BY_ID = '[CreditNote] Find CreditNote By Id';

  findCreditNoteById(id): Action {
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_BY_ID,
      payload: id,
    };
  }

  static FIND_CREDIT_NOTE_BY_ID_SUCCESS = '[CreditNote] Find CreditNote By Id Success';

  findCreditNoteByIdSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_BY_ID_SUCCESS,
      payload: creditNote,
    };
  }

  static FIND_CREDIT_NOTE_BY_REFERENCE_NO = '[CreditNote] Find CreditNotes By reference No';

  findCreditNoteByReferenceNo(referenceNo): Action {
    console.log('findCreditNoteByReferenceNo');
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_BY_REFERENCE_NO,
      payload: referenceNo,
    };
  }

  static FIND_CREDIT_NOTE_BY_REFERENCE_NO_SUCCESS = '[CreditNote] Find CreditNotes By reference No Success';

  findCreditNoteByReferenceNoSuccess(creditNote): Action {
    console.log('findCreditNoteByReferenceNoSuccess');
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_BY_REFERENCE_NO_SUCCESS,
      payload: creditNote,
    };
  }

  static FIND_CREDIT_NOTE_ITEMS = '[CreditNote] Find CreditNote Items';

  findCreditNoteItems(creditNote): Action {
    console.log('findCreditNoteItems for creditNote: ' + creditNote.referenceNo);
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_ITEMS,
      payload: creditNote,
    };
  }

  static FIND_CREDIT_NOTE_ITEMS_SUCCESS = '[CreditNote] Find CreditNote Items Success';

  findCreditNoteItemsSuccess(items): Action {
    console.log('findCreditNoteItemsSuccess');
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_ITEMS_SUCCESS,
      payload: items,
    };
  }

  static FIND_CREDIT_NOTE_TASK_BY_TASK_ID = '[CreditNote] Find CreditNote Task By Task Id';

  findCreditNoteTaskByTaskId(taskId): Action {
    console.log('findCreditNoteTaskByTaskId');
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_TASK_BY_TASK_ID,
      payload: taskId,
    };
  }

  static FIND_CREDIT_NOTE_TASK_BY_TASK_ID_SUCCESS = '[CreditNote] Find CreditNote Task By Task Id Success';

  findCreditNoteTaskByTaskIdSuccess(task): Action {
    console.log('findCreditNoteTaskByTaskIdSuccess');
    return {
      type: CreditNoteActions.FIND_CREDIT_NOTE_TASK_BY_TASK_ID_SUCCESS,
      payload: task,
    };
  }

  static START_CREDIT_NOTE_TASK = '[CreditNote] Start CreditNote Task';

  startCreditNoteTask(creditNote): Action {
    return {
      type: CreditNoteActions.START_CREDIT_NOTE_TASK,
      payload: creditNote,
    };
  }

  static START_CREDIT_NOTE_TASK_SUCCESS = '[CreditNote] Start CreditNote Task Success';

  startCreditNoteTaskSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.START_CREDIT_NOTE_TASK_SUCCESS,
      payload: creditNote,
    };
  }

  static COMPLETE_CREDIT_NOTE_TASK = '[CreditNote] Complete CreditNote Task';

  completeCreditNoteTask(creditNote): Action {
    return {
      type: CreditNoteActions.COMPLETE_CREDIT_NOTE_TASK,
      payload: creditNote,
    };
  }

  static COMPLETE_CREDIT_NOTE_TASK_SUCCESS = '[CreditNote] Complete CreditNote Task Success';

  completeCreditNoteTaskSuccess(message): Action {
    return {
      type: CreditNoteActions.COMPLETE_CREDIT_NOTE_TASK_SUCCESS,
      payload: message,
    };
  }

  static CLAIM_CREDIT_NOTE_TASK = '[CreditNote] Assign CreditNote Task';

  claimCreditNoteTask(creditNote): Action {
    return {
      type: CreditNoteActions.CLAIM_CREDIT_NOTE_TASK,
      payload: creditNote,
    };
  }

  static CLAIM_CREDIT_NOTE_TASK_SUCCESS = '[CreditNote] Assign CreditNote Task Success';

  claimCreditNoteTaskSuccess(task): Action {
    return {
      type: CreditNoteActions.CLAIM_CREDIT_NOTE_TASK_SUCCESS,
      payload: task,
    };
  }

  static RELEASE_CREDIT_NOTE_TASK = '[CreditNote] Release CreditNote Task';

  releaseCreditNoteTask(creditNote): Action {
    return {
      type: CreditNoteActions.RELEASE_CREDIT_NOTE_TASK,
      payload: creditNote,
    };
  }

  static RELEASE_CREDIT_NOTE_TASK_SUCCESS = '[CreditNote] Release CreditNote Task Success';

  releaseCreditNoteTaskSuccess(task): Action {
    return {
      type: CreditNoteActions.RELEASE_CREDIT_NOTE_TASK_SUCCESS,
      payload: task,
    };
  }

  static ADD_CREDIT_NOTE_ITEM = '[CreditNote] Add CreditNote Item';

  addCreditNoteItem(creditNote, item): Action {
    return {
      type: CreditNoteActions.ADD_CREDIT_NOTE_ITEM,
      payload: {creditNote: creditNote, item: item},
    };
  }

  static ADD_CREDIT_NOTE_ITEM_SUCCESS = '[CreditNote] Add CreditNote Item Success';

  addCreditNoteItemSuccess(message): Action {
    return {
      type: CreditNoteActions.ADD_CREDIT_NOTE_ITEM_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_CREDIT_NOTE = '[CreditNote] Update CreditNote';

  updateCreditNote(creditNote): Action {
    return {
      type: CreditNoteActions.UPDATE_CREDIT_NOTE,
      payload: creditNote,
    };
  }

  static UPDATE_CREDIT_NOTE_SUCCESS = '[CreditNote] Update CreditNote Success';

  updateCreditNoteSuccess(creditNote): Action {
    return {
      type: CreditNoteActions.UPDATE_CREDIT_NOTE_SUCCESS,
      payload: creditNote,
    };
  }

  static UPDATE_CREDIT_NOTE_ITEM = '[CreditNote] Update CreditNote Item';

  updateCreditNoteItem(creditNote, item): Action {
    return {
      type: CreditNoteActions.UPDATE_CREDIT_NOTE_ITEM,
      payload: {creditNote: creditNote, item: item},
    };
  }

  static UPDATE_CREDIT_NOTE_ITEM_SUCCESS = '[Invoice] Update CreditNote Item Success';

  updateCreditNoteItemSuccess(message): Action {
    return {
      type: CreditNoteActions.UPDATE_CREDIT_NOTE_ITEM_SUCCESS,
      payload: message,
    };
  }

  static DELETE_CREDIT_NOTE_ITEM = '[CreditNote] Delete CreditNote Item';

  deleteCreditNoteItem(creditNote, item): Action {
    return {
      type: CreditNoteActions.DELETE_CREDIT_NOTE_ITEM,
      payload: {creditNote: creditNote, item: item},
    };
  }

  static DELETE_CREDIT_NOTE_ITEM_SUCCESS = '[Invoice] Delete CreditNote Item Success';

  deleteCreditNoteItemSuccess(message): Action {
    return {
      type: CreditNoteActions.DELETE_CREDIT_NOTE_ITEM_SUCCESS,
      payload: message,
    };
  }
  
  static REMOVE_CREDIT_NOTE_TASK = '[CreditNote] Remove CreditNote Task';
  removeCreditNoteTask(creditNoteTask): Action {
      return {
          type: CreditNoteActions.REMOVE_CREDIT_NOTE_TASK,
          payload: creditNoteTask,
    };
  }

  static REMOVE_CREDIT_NOTE_TASK_SUCCESS = '[CreditNote] Remove CreditNote Task Success';
  removeCreditNoteTaskSuccess(task): Action {
      return {
          type: CreditNoteActions.REMOVE_CREDIT_NOTE_TASK_SUCCESS,
          payload: task,
    };
  }
}
