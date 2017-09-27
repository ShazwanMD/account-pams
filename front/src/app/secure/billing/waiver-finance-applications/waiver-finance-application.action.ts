import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class WaiverFinanceApplicationActions {

  static FIND_ASSIGNED_WAIVER_FINANCE_APPLICATION_TASKS = '[WaiverFinanceApplication] Find Assigned WaiverFinanceApplication Tasks';

  findAssignedWaiverFinanceApplicationTasks(): Action {
    console.log("findAssignedWaiverFinanceApplicationTasks");
    return {
      type: WaiverFinanceApplicationActions.FIND_ASSIGNED_WAIVER_FINANCE_APPLICATION_TASKS
    };
  }

  static FIND_ASSIGNED_WAIVER_FINANCE_APPLICATION_TASKS_SUCCESS = '[WaiverFinanceApplication] Find Assigned WaiverFinanceApplication Tasks Success';

  findAssignedWaiverFinanceApplicationTasksSuccess(tasks): Action {
    console.log("findAssignedWaiverFinanceApplicationTasksSuccess");
    return {
      type: WaiverFinanceApplicationActions.FIND_ASSIGNED_WAIVER_FINANCE_APPLICATION_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_POOLED_WAIVER_FINANCE_APPLICATION_TASKS = '[WaiverFinanceApplication] Find Pooled WaiverFinanceApplication Tasks';

  findPooledWaiverFinanceApplicationTasks(): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_POOLED_WAIVER_FINANCE_APPLICATION_TASKS
    };
  }

  static FIND_POOLED_WAIVER_FINANCE_APPLICATION_TASKS_SUCCESS = '[WaiverFinanceApplication] Find Pooled WaiverFinanceApplication Tasks Success';

  findPooledWaiverFinanceApplicationTasksSuccess(tasks): Action {
    console.log("findPooledWaiverFinanceApplicationTasksSuccess");
    return {
      type: WaiverFinanceApplicationActions.FIND_POOLED_WAIVER_FINANCE_APPLICATION_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_TASK_BY_TASK_ID = '[WaiverFinanceApplication] Find WaiverFinanceApplication Task By Task Id';

  findWaiverFinanceApplicationTaskByTaskId(taskId): Action {
    console.log("findWaiverFinanceApplicationTaskByTaskId");
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_TASK_BY_TASK_ID,
      payload: taskId
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_TASK_BY_TASK_ID_SUCCESS = '[WaiverFinanceApplication] Find WaiverFinanceApplication Task By Task Id Success';

  findWaiverFinanceApplicationTaskByTaskIdSuccess(task): Action {
    console.log("findWaiverFinanceApplicationTaskByTaskIdSuccess");
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_TASK_BY_TASK_ID_SUCCESS,
      payload: task
    };
  }

  static START_WAIVER_FINANCE_APPLICATION_TASK = '[WaiverFinanceApplication] Start WaiverFinanceApplication Task';

  startWaiverFinanceApplicationTask(WaiverFinanceApplication): Action {
    return {
      type: WaiverFinanceApplicationActions.START_WAIVER_FINANCE_APPLICATION_TASK,
      payload: WaiverFinanceApplication
    };
  }

  static START_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS = '[WaiverFinanceApplication] Start WaiverFinanceApplication Task Success';

  startWaiverFinanceApplicationTaskSuccess(task): Action {
    return {
      type: WaiverFinanceApplicationActions.START_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static COMPLETE_WAIVER_FINANCE_APPLICATION_TASK = '[WaiverFinanceApplication] Complete WaiverFinanceApplication Task';

  completeWaiverFinanceApplicationTask(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.COMPLETE_WAIVER_FINANCE_APPLICATION_TASK,
      payload: invoice
    };
  }

  static COMPLETE_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS = '[WaiverFinanceApplication] Complete WaiverFinanceApplication Task Success';

  completeWaiverFinanceApplicationTaskSuccess(task): Action {
    return {
      type: WaiverFinanceApplicationActions.COMPLETE_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static CLAIM_WAIVER_FINANCE_APPLICATION_TASK = '[WaiverFinanceApplication] Claim WaiverFinanceApplication Task';

  claimWaiverFinanceApplicationTask(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.CLAIM_WAIVER_FINANCE_APPLICATION_TASK,
      payload: invoice
    };
  }

  static CLAIM_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS = '[WaiverFinanceApplication] Claim WaiverFinanceApplication Task Success';

  claimWaiverFinanceApplicationTaskSuccess(task): Action {
    return {
      type: WaiverFinanceApplicationActions.CLAIM_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static RELEASE_WAIVER_FINANCE_APPLICATION_TASK = '[WaiverFinanceApplication] Release WaiverFinanceApplication Task';

  releaseWaiverFinanceApplicationTask(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.RELEASE_WAIVER_FINANCE_APPLICATION_TASK,
      payload: invoice
    };
  }

  static RELEASE_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS = '[WaiverFinanceApplication] Release WaiverFinanceApplication Task Success';

  releaseWaiverFinanceApplicationTaskSuccess(task): Action {
    return {
      type: WaiverFinanceApplicationActions.RELEASE_WAIVER_FINANCE_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_BY_ID = '[WaiverFinanceApplication] Find WaiverFinanceApplication By Id';

  findWaiverFinanceApplicationById(id): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_BY_ID,
      payload: id
    };
  }

  static FIND_COMPLETED_WAIVER_FINANCE_APPLICATIONS = '[WaiverFinanceApplication] Find Completed WaiverFinanceApplication';

  findCompletedWaiverFinanceApplications(): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_COMPLETED_WAIVER_FINANCE_APPLICATIONS,
    };
  }

  static FIND_COMPLETED_WAIVER_FINANCE_APPLICATIONS_SUCCESS = '[WaiverFinanceApplication] Find Completed WaiverFinanceApplication Success';

  findCompletedWaiverFinanceApplicationsSuccess(invoices): Action {
    console.log('findCompletedInvoicesSuccess');
    return {
      type: WaiverFinanceApplicationActions.FIND_COMPLETED_WAIVER_FINANCE_APPLICATIONS_SUCCESS,
      payload: invoices,
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_BY_ID_SUCCESS = '[WaiverFinanceApplication] Find WaiverFinanceApplication By Id Success';

  findWaiverFinanceApplicationByIdSuccess(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_BY_ID_SUCCESS,
      payload: invoice
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_BY_REFERENCE_NO = '[WaiverFinanceApplication] Find WaiverFinanceApplication By Reference No';

  findWaiverFinanceApplicationByReferenceNo(referenceNo): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_BY_REFERENCE_NO_SUCCESS = '[WaiverFinanceApplication] Find WaiverFinanceApplication By Reference No Success';

  findWaiverFinanceApplicationByReferenceNoSuccess(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_BY_REFERENCE_NO_SUCCESS,
      payload: invoice
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_ITEMS = '[WaiverFinanceApplication] Find WaiverFinanceApplication Items';

  findWaiverFinanceApplicationItems(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_ITEMS,
      payload: invoice
    };
  }

  static FIND_WAIVER_FINANCE_APPLICATION_ITEMS_SUCCESS = '[WaiverFinanceApplication] Find WaiverFinanceApplication Items Success';

  findWaiverFinanceApplicationItemsSuccess(items): Action {
    console.log("findWaiverApplicationTransactionsSuccess");
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_ITEMS_SUCCESS,
      payload: items
    };
  }

  static FIND_ARCHIVED_WAIVER_FINANCE_APPLICATIONS = '[WaiverFinanceApplication] Find Archived WaiverFinanceApplication';

  findArchivedWaiverFinanceApplications(): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_ARCHIVED_WAIVER_FINANCE_APPLICATIONS,
    };
  }

  static FIND_ARCHIVED_WAIVER_FINANCE_APPLICATIONS_SUCCESS = '[WaiverFinanceApplication] Find Archived WaiverFinanceApplication Success';

  findArchivedWaiverFinanceApplicationsSuccess(invoices): Action {
    console.log('findArchivedWaiverApplicationsSuccess');
    return {
      type: WaiverFinanceApplicationActions.FIND_ARCHIVED_WAIVER_FINANCE_APPLICATIONS_SUCCESS,
      payload: invoices,
    };
  }

  static UPDATE_WAIVER_FINANCE_APPLICATION = '[WaiverFinanceApplication] Update WaiverFinanceApplication';

  updateWaiverFinanceApplication(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.UPDATE_WAIVER_FINANCE_APPLICATION,
      payload: invoice
    };
  }

  static UPDATE_WAIVER_FINANCE_APPLICATION_SUCCESS = '[WaiverFinanceApplication] Update WaiverFinanceApplication Success';

  updateWaiverFinanceApplicationSuccess(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.UPDATE_WAIVER_FINANCE_APPLICATION_SUCCESS,
      payload: invoice
    };
  }

  static REMOVE_WAIVER_FINANCE_APPLICATION = '[WaiverFinanceApplication] Remove WaiverFinanceApplication';

  removeWaiverFinanceApplication(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.REMOVE_WAIVER_FINANCE_APPLICATION,
      payload: invoice
    };
  }

  static REMOVE_WAIVER_FINANCE_APPLICATION_SUCCESS = '[WaiverFinanceApplication] Remove WaiverFinanceApplication Success';

  removeWaiverFinanceApplicationSuccess(invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.REMOVE_WAIVER_FINANCE_APPLICATION_SUCCESS,
      payload: invoice
    };
  }
  
  static ADD_WAIVER_INVOICE = '[WaiverFinanceApplication] Add Waiver Invoice';

  addWaiverInvoice(waiverFinanceApplication, invoice): Action {
    return {
      type: WaiverFinanceApplicationActions.ADD_WAIVER_INVOICE,
      payload: {waiverFinanceApplication, invoice}
    };
  }

  static ADD_WAIVER_INVOICE_SUCCESS = '[WaiverFinanceApplication] Add Waiver Invoice Success';

  addWaiverInvoiceSuccess(message): Action {
    return {
      type: WaiverFinanceApplicationActions.ADD_WAIVER_INVOICE_SUCCESS,
      payload: message
    };
  }
  
  static ADD_WAIVER_ACCOUNT_CHARGE = '[WaiverFinanceApplication] Add Waiver Account Charge';

  addWaiverAccountCharge(waiverFinanceApplication, accountCharge): Action {
    return {
      type: WaiverFinanceApplicationActions.ADD_WAIVER_ACCOUNT_CHARGE,
      payload: {waiverFinanceApplication, accountCharge}
    };
  }

  static ADD_WAIVER_ACCOUNT_CHARGE_SUCCESS = '[WaiverFinanceApplication] Add Waiver Account Charge Success';

  addWaiverAccountChargeSuccess(message): Action {
    return {
      type: WaiverFinanceApplicationActions.ADD_WAIVER_ACCOUNT_CHARGE_SUCCESS,
      payload: message
    };
  }
  
  static ADD_WAIVER_DEBIT_NOTE = '[WaiverFinanceApplication] Add Waiver Debit Note';

  addWaiverDebitNote(waiverFinanceApplication, debitNote): Action {
    return {
      type: WaiverFinanceApplicationActions.ADD_WAIVER_DEBIT_NOTE,
      payload: {waiverFinanceApplication, debitNote}
    };
  }

  static ADD_WAIVER_DEBIT_NOTE_SUCCESS = '[WaiverFinanceApplication] Add Waiver Debit Note Success';

  addWaiverDebitNoteSuccess(message): Action {
    return {
      type: WaiverFinanceApplicationActions.ADD_WAIVER_DEBIT_NOTE_SUCCESS,
      payload: message
    };
  }
  
  static UPDATE_WAIVER = '[WaiverFinanceApplication] Update Waiver';

  updateWaivers(waiverFinanceApplication): Action {
    return {
      type: WaiverFinanceApplicationActions.UPDATE_WAIVER,
      payload: waiverFinanceApplication
    };
  }

  static UPDATE_WAIVER_SUCCESS = '[WaiverFinanceApplication] Update Waiver Success';

  updateWaiversSuccess(message): Action {
    return {
      type: WaiverFinanceApplicationActions.UPDATE_WAIVER_SUCCESS,
      payload: message
    };
  }
  
  static ITEM_TO_WAIVER_INVOICE = '[WaiverFinanceApplication] Item to Waiver Item';

  itemToWaiverItem(invoice, waiverFinanceApplication): Action {
    return {
      type: WaiverFinanceApplicationActions.ITEM_TO_WAIVER_INVOICE,
      payload: {invoice, waiverFinanceApplication}
    };
  }

  static ITEM_TO_WAIVER_INVOICE_SUCCESS = '[WaiverFinanceApplication] Item to Waiver Item Success';

  itemToWaiverItemSuccess(message): Action {
    return {
      type: WaiverFinanceApplicationActions.ITEM_TO_WAIVER_INVOICE_SUCCESS,
      payload: message
    };
  }
  
  static FIND_WAIVER_INVOICE = '[WaiverFinanceApplication] Find Waiver Invoice';

  findWaiversByInvoice(waiverFinanceApplication): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_INVOICE,
      payload: waiverFinanceApplication
    };
  }

  static FIND_WAIVER_INVOICE_SUCCESS = '[WaiverFinanceApplication] Find Waiver Invoice Success';

  findWaiversByInvoiceSuccess(message): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_INVOICE_SUCCESS,
      payload: message
    };
  }
  
  static FIND_WAIVER_ITEMS = '[WaiverFinanceApplication] Find Waiver Items';

  findWaiverItems(waiverFinanceApplication): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_ITEMS,
      payload: waiverFinanceApplication
    };
  }

  static FIND_WAIVER_ITEMS_SUCCESS = '[WaiverFinanceApplication] Find Waiver Items Success';

  findWaiverItemsSuccess(message): Action {
    return {
      type: WaiverFinanceApplicationActions.FIND_WAIVER_ITEMS_SUCCESS,
      payload: message
    };
  }
}
