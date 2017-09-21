import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class DebitNoteActions {

  static FIND_DEBIT_NOTES = '[DebitNote] Find DebitNotes';

  findDebitNotes(debitNote): Action {
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES,
      payload: debitNote
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


  static FIND_UNPAID_DEBIT_NOTES = '[DebitNote] Find Unpaid Debit Notes';

  findUnpaidDebitNotes(account): Action {
    return {
      type: DebitNoteActions.FIND_UNPAID_DEBIT_NOTES,
      payload: account,
    };
  }

  static FIND_UNPAID_DEBIT_NOTES_SUCCESS = '[DebitNote] Find Unpaid Debit Notes Success';

  findUnpaidDebitNotesSuccess(debitNotes): Action {
    console.log('findUnpaidDebitNotesSuccess');
    return {
      type: DebitNoteActions.FIND_UNPAID_DEBIT_NOTES_SUCCESS,
      payload: debitNotes,
    };
  }


  static FIND_DEBIT_NOTES_BY_INVOICE = '[Invoice] Find Invoice';

  findDebitNotesByInvoice(invoice): Action {
    console.log("findDebitNotesByInvoice");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES_BY_INVOICE,
      payload: invoice
    };
  }

  static FIND_DEBIT_NOTES_BY_INVOICE_SUCCESS = '[Intake Application] Find Invoice Success';

  findDebitNotesByInvoiceSuccess(debitNotes): Action {
    console.log("findDebitNotesByInvoiceSuccess");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTES_BY_INVOICE_SUCCESS,
      payload: debitNotes
    };
  }


  static FIND_COMPLETED_DEBIT_NOTES = '[DebitNote] Find Completed DebitNotes';

  findCompletedDebitNotes(): Action {
    return {
      type: DebitNoteActions.FIND_COMPLETED_DEBIT_NOTES
    };
  }

  static FIND_COMPLETED_DEBIT_NOTES_SUCCESS = '[DebitNote] Find Completed DebitNotes Success';

  findCompletedDebitNotesSuccess(debitNotes): Action {
    console.log("findCompletedDebitNotesSuccess");
    return {
      type: DebitNoteActions.FIND_COMPLETED_DEBIT_NOTES_SUCCESS,
      payload: debitNotes
    };
  }


  static FIND_ARCHIVED_DEBIT_NOTES = '[DebitNote] Find Archived Debit_notes';

  findArchivedDebitNotes(): Action {
    return {
      type: DebitNoteActions.FIND_ARCHIVED_DEBIT_NOTES
    };
  }

  static FIND_ARCHIVED_DEBIT_NOTES_SUCCESS = '[DebitNote] Find Archived Debit_notes Success';

  findArchivedDebitNotesSuccess(debit_notes): Action {
    console.log("findArchivedDebit_notesSuccess");
    return {
      type: DebitNoteActions.FIND_ARCHIVED_DEBIT_NOTES_SUCCESS,
      payload: debit_notes
    };
  }

  static FIND_ASSIGNED_DEBIT_NOTE_TASKS = '[DebitNote] Find Assigned DebitNotes Tasks';

  findAssignedDebitNoteTasks(): Action {
    return {
      type: DebitNoteActions.FIND_ASSIGNED_DEBIT_NOTE_TASKS
    };
  }

  static FIND_ASSIGNED_DEBIT_NOTE_TASKS_SUCCESS = '[DebitNote] Find Assigned DebitNotes Tasks Success';

  findAssignedDebitNoteTasksSuccess(tasks): Action {
    console.log("findAssignedDebitNoteTasksSuccess");
    return {
      type: DebitNoteActions.FIND_ASSIGNED_DEBIT_NOTE_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_POOLED_DEBIT_NOTE_TASKS = '[DebitNote] Find Pooled DebitNotes Tasks';

  findPooledDebitNoteTasks(): Action {
    return {
      type: DebitNoteActions.FIND_POOLED_DEBIT_NOTE_TASKS
    };
  }

  static FIND_POOLED_DEBIT_NOTE_TASKS_SUCCESS = '[DebitNote] Find Pooled DebitNotes Tasks Success';

  findPooledDebitNoteTasksSuccess(tasks): Action {
    console.log("findAssignedDebitNoteTasksSuccess");
    return {
      type: DebitNoteActions.FIND_POOLED_DEBIT_NOTE_TASKS_SUCCESS,
      payload: tasks
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

  static FIND_DEBIT_NOTE_BY_REFERENCE_NO = '[DebitNote] Find DebitNotes By reference No';

  findDebitNoteByReferenceNo(referenceNo): Action {
    console.log("findDebitNoteByReferenceNo");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_DEBIT_NOTE_BY_REFERENCE_NO_SUCCESS = '[DebitNote] Find DebitNotes By reference No Success';

  findDebitNoteByReferenceNoSuccess(debitNote): Action {
    console.log("findDebitNoteByReferenceNoSuccess");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_BY_REFERENCE_NO_SUCCESS,
      payload: debitNote
    };
  }

  static FIND_DEBIT_NOTE_ITEMS = '[DebitNote] Find DebitNote Items';

  findDebitNoteItems(debitNote): Action {
    console.log("findDebitNoteItems for debitNote: " + debitNote);
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_ITEMS,
      payload: debitNote
    };
  }

  static FIND_DEBIT_NOTE_ITEMS_SUCCESS = '[DebitNote] Find DebitNote Items Success';

  findDebitNoteItemsSuccess(items): Action {
    console.log("findDebitNoteItemsSuccess");
    return {
      type: DebitNoteActions.FIND_DEBIT_NOTE_ITEMS_SUCCESS,
      payload: items
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

  static COMPLETE_DEBIT_NOTE_TASK = '[DebitNote] Complete DebitNote Task';

  completeDebitNoteTask(debitNote): Action {
    return {
      type: DebitNoteActions.COMPLETE_DEBIT_NOTE_TASK,
      payload: debitNote
    };
  }

  static COMPLETE_DEBIT_NOTE_TASK_SUCCESS = '[DebitNote] Complete DebitNote Task Success';

  completeDebitNoteTaskSuccess(message): Action {
    return {
      type: DebitNoteActions.COMPLETE_DEBIT_NOTE_TASK_SUCCESS,
      payload: message
    };
  }

  static CLAIM_DEBIT_NOTE_TASK = '[DebitNote] Assign DebitNote Task';

  claimDebitNoteTask(debitNote): Action {
    return {
      type: DebitNoteActions.CLAIM_DEBIT_NOTE_TASK,
      payload: debitNote
    };
  }

  static CLAIM_DEBIT_NOTE_TASK_SUCCESS = '[DebitNote] Assign DebitNote Task Success';

  claimDebitNoteTaskSuccess(task): Action {
    return {
      type: DebitNoteActions.CLAIM_DEBIT_NOTE_TASK_SUCCESS,
      payload: task
    };
  }

  static RELEASE_DEBIT_NOTE_TASK = '[DebitNote] Release DebitNote Task';

  releaseDebitNoteTask(debitNote): Action {
    return {
      type: DebitNoteActions.RELEASE_DEBIT_NOTE_TASK,
      payload: debitNote
    };
  }

  static RELEASE_DEBIT_NOTE_TASK_SUCCESS = '[DebitNote] Release DebitNote Task Success';

  releaseDebitNoteTaskSuccess(task): Action {
    return {
      type: DebitNoteActions.RELEASE_DEBIT_NOTE_TASK_SUCCESS,
      payload: task
    };
  }


  static ADD_DEBIT_NOTE_ITEM = '[DebitNote] Add DebitNote Item';

  addDebitNoteItem(debitNote, item): Action {
    return {
      type: DebitNoteActions.ADD_DEBIT_NOTE_ITEM,
      payload: {debitNote: debitNote, item: item}
    };
  }

  static ADD_DEBIT_NOTE_ITEM_SUCCESS = '[DebitNote] Add DebitNote Item Success';

  addDebitNoteItemSuccess(message): Action {
    return {
      type: DebitNoteActions.ADD_DEBIT_NOTE_ITEM_SUCCESS,
      payload: message
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

  static UPDATE_DEBIT_NOTE_ITEM = '[DebitNote] Update DebitNote Item';

  updateDebitNoteItem(debitNote, item): Action {
    return {
      type: DebitNoteActions.UPDATE_DEBIT_NOTE_ITEM,
      payload: {debitNote: debitNote, item: item}
    };
  }

  static UPDATE_DEBIT_NOTE_ITEM_SUCCESS = '[Invoice] Update DebitNote Item Success';

  updateDebitNoteItemSuccess(message): Action {
    return {
      type: DebitNoteActions.UPDATE_DEBIT_NOTE_ITEM_SUCCESS,
      payload: message
    };
  }

  static DELETE_DEBIT_NOTE_ITEM = '[DebitNote] Delete DebitNote Item';

  deleteDebitNoteItem(debitNote, item): Action {
    return {
      type: DebitNoteActions.DELETE_DEBIT_NOTE_ITEM,
      payload: {debitNote: debitNote, item: item}
    };
  }

  static DELETE_DEBIT_NOTE_ITEM_SUCCESS = '[Invoice] Delete DebitNote Item Success';

  deleteDebitNoteItemSuccess(message): Action {
    return {
      type: DebitNoteActions.DELETE_DEBIT_NOTE_ITEM_SUCCESS,
      payload: message
    };
  }
}
