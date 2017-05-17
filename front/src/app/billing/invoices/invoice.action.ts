import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class InvoiceActions {

  static FIND_COMPLETED_INVOICES = '[Invoice] Find Completed Invoices';

  findCompletedInvoices(): Action {
    return {
      type: InvoiceActions.FIND_COMPLETED_INVOICES
    };
  }

  static FIND_COMPLETED_INVOICES_SUCCESS = '[Invoice] Find Completed Invoices Success';

  findCompletedInvoicesSuccess(invoices): Action {
    console.log("findCompletedInvoicesSuccess");
    return {
      type: InvoiceActions.FIND_COMPLETED_INVOICES_SUCCESS,
      payload: invoices
    };
  }


  static FIND_ASSIGNED_INVOICE_TASKS = '[Invoice] Find Assigned Invoice Tasks';

  findAssignedInvoiceTasks(): Action {
    return {
      type: InvoiceActions.FIND_ASSIGNED_INVOICE_TASKS
    };
  }

  static FIND_ASSIGNED_INVOICE_TASKS_SUCCESS = '[Invoice] Find Assigned Invoice Tasks Success';

  findAssignedInvoiceTasksSuccess(tasks): Action {
    console.log("findAssignedInvoiceTasksSuccess");
    return {
      type: InvoiceActions.FIND_ASSIGNED_INVOICE_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_POOLED_INVOICE_TASKS = '[Invoice] Find Pooled Invoice Tasks';

  findPooledInvoiceTasks(): Action {
    return {
      type: InvoiceActions.FIND_POOLED_INVOICE_TASKS
    };
  }

  static FIND_POOLED_INVOICE_TASKS_SUCCESS = '[Invoice] Find Pooled Invoice Tasks Success';

  findPooledInvoiceTasksSuccess(tasks): Action {
    console.log("findAssignedInvoiceTasksSuccess");
    return {
      type: InvoiceActions.FIND_POOLED_INVOICE_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_INVOICE_TASK_BY_TASK_ID = '[Invoice] Find Invoice Task By Task Id';

  findInvoiceTaskByTaskId(taskId): Action {
    console.log("findInvoiceTaskByTaskId");
    return {
      type: InvoiceActions.FIND_INVOICE_TASK_BY_TASK_ID,
      payload: taskId
    };
  }

  static FIND_INVOICE_TASK_BY_TASK_ID_SUCCESS = '[Invoice] Find Invoice Task By Task Id Success';

  findInvoiceTaskByTaskIdSuccess(task): Action {
    console.log("findInvoiceTaskByTaskIdSuccess");
    return {
      type: InvoiceActions.FIND_INVOICE_TASK_BY_TASK_ID_SUCCESS,
      payload: task
    };
  }

  static START_INVOICE_TASK = '[Invoice] Start Invoice Task';

  startInvoiceTask(invoice): Action {
    return {
      type: InvoiceActions.START_INVOICE_TASK,
      payload: invoice
    };
  }

  static START_INVOICE_TASK_SUCCESS = '[Invoice] Start Invoice Task Success';

  startInvoiceTaskSuccess(referenceNo): Action {
    return {
      type: InvoiceActions.START_INVOICE_TASK_SUCCESS,
      payload: referenceNo
    };
  }

  static COMPLETE_INVOICE_TASK = '[Invoice] Complete Invoice Task';

  completeInvoiceTask(invoice): Action {
    return {
      type: InvoiceActions.COMPLETE_INVOICE_TASK,
      payload: invoice
    };
  }

  static COMPLETE_INVOICE_TASK_SUCCESS = '[Invoice] Complete Invoice Task Success';

  completeInvoiceTaskSuccess(message): Action {
    return {
      type: InvoiceActions.COMPLETE_INVOICE_TASK_SUCCESS,
      payload: message
    };
  }

  static CLAIM_INVOICE_TASK = '[Invoice] Assign Invoice Task';

  claimInvoiceTask(invoice): Action {
    return {
      type: InvoiceActions.CLAIM_INVOICE_TASK,
      payload: invoice
    };
  }

  static CLAIM_INVOICE_TASK_SUCCESS = '[Invoice] Assign Invoice Task Success';

  claimInvoiceTaskSuccess(task): Action {
    return {
      type: InvoiceActions.CLAIM_INVOICE_TASK_SUCCESS,
      payload: task
    };
  }

  static RELEASE_INVOICE_TASK = '[Invoice] Release Invoice Task';

  releaseInvoiceTask(invoice): Action {
    return {
      type: InvoiceActions.RELEASE_INVOICE_TASK,
      payload: invoice
    };
  }

  static RELEASE_INVOICE_TASK_SUCCESS = '[Invoice] Release Invoice Task Success';

  releaseInvoiceTaskSuccess(task): Action {
    return {
      type: InvoiceActions.RELEASE_INVOICE_TASK_SUCCESS,
      payload: task
    };
  }

  static FIND_INVOICE_BY_ID = '[Invoice] Find Invoice By Id';

  findInvoiceById(id): Action {
    return {
      type: InvoiceActions.FIND_INVOICE_BY_ID,
      payload: id
    };
  }

  static FIND_INVOICE_BY_ID_SUCCESS = '[Invoice] Find Invoice By Id Success';

  findInvoiceByIdSuccess(invoice): Action {
    return {
      type: InvoiceActions.FIND_INVOICE_BY_ID_SUCCESS,
      payload: invoice
    };
  }

  static FIND_INVOICE_BY_REFERENCE_NO = '[Invoice] Find Invoice By Reference No';

  findInvoiceByReferenceNo(referenceNo): Action {
    return {
      type: InvoiceActions.FIND_INVOICE_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_INVOICE_BY_REFERENCE_NO_SUCCESS = '[Invoice] Find Invoice By Reference No Success';

  findInvoiceByReferenceNoSuccess(invoice): Action {
    return {
      type: InvoiceActions.FIND_INVOICE_BY_REFERENCE_NO_SUCCESS,
      payload: invoice
    };
  }

  static FIND_INVOICE_ITEMS = '[Invoice] Find Invoice Items';

  findInvoiceItems(invoice): Action {
    console.log("findInvoiceItems for invoice: " + invoice);
    return {
      type: InvoiceActions.FIND_INVOICE_ITEMS,
      payload: invoice
    };
  }

  static FIND_INVOICE_ITEMS_SUCCESS = '[Invoice] Find Invoice Items Success';

  findInvoiceItemsSuccess(items): Action {
    console.log("findInvoiceItemsSuccess");
    return {
      type: InvoiceActions.FIND_INVOICE_ITEMS_SUCCESS,
      payload: items
    };
  }


  static UPDATE_INVOICE = '[Invoice] Update Invoice';

  updateInvoice(invoice): Action {
    return {
      type: InvoiceActions.UPDATE_INVOICE,
      payload: invoice
    };
  }

  static UPDATE_INVOICE_SUCCESS = '[Invoice] Update Invoice Success';

  updateInvoiceSuccess(invoice): Action {
    return {
      type: InvoiceActions.UPDATE_INVOICE_SUCCESS,
      payload: invoice
    };
  }

  static REMOVE_INVOICE = '[Invoice] Remove Invoice';

  removeInvoice(invoice): Action {
    return {
      type: InvoiceActions.REMOVE_INVOICE,
      payload: invoice
    };
  }

  static REMOVE_INVOICE_SUCCESS = '[Invoice] Remove Invoice Success';

  removeInvoiceSuccess(invoice): Action {
    return {
      type: InvoiceActions.REMOVE_INVOICE_SUCCESS,
      payload: invoice
    };
  }

  static ADD_INVOICE_ITEM = '[Invoice] Add Invoice Item';

  addInvoiceItem(invoice, item): Action {
    return {
      type: InvoiceActions.ADD_INVOICE_ITEM,
      payload: {invoice:invoice, item:item}
    };
  }

  static ADD_INVOICE_ITEM_SUCCESS = '[Invoice] Add Invoice Item Success';

  addInvoiceItemSuccess(message): Action {
    return {
      type: InvoiceActions.ADD_INVOICE_ITEM_SUCCESS,
      payload: message
    };
  }
  
  static DELETE_INVOICE_ITEM = '[Invoice] Delete Invoice Item';

  deleteInvoiceItem(invoice, item): Action {
    return {
      type: InvoiceActions.DELETE_INVOICE_ITEM,
      payload: {invoice:invoice, item:item}
    };
  }

  static DELETE_INVOICE_ITEM_SUCCESS = '[Invoice] Add Invoice Item Success';

  deleteInvoiceItemSuccess(message): Action {
    return {
      type: InvoiceActions.DELETE_INVOICE_ITEM_SUCCESS,
      payload: message
    };
  }
  
  static UPDATE_INVOICE_ITEM = '[Invoice] Update Invoice Item';

  updateInvoiceItem(invoice, item): Action {
    return {
      type: InvoiceActions.UPDATE_INVOICE_ITEM,
      payload: {invoice:invoice, item:item}
    };
  }

  static UPDATE_INVOICE_ITEM_SUCCESS = '[Invoice] Update Invoice Item Success';

  updateInvoiceItemSuccess(message): Action {
    return {
      type: InvoiceActions.UPDATE_INVOICE_ITEM_SUCCESS,
      payload: message
    };
  }

}
