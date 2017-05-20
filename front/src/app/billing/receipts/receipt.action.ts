import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class ReceiptActions {

  static FIND_COMPLETED_RECEIPTS = '[Receipt] Find Completed Receipts';

  findCompletedReceipts(): Action {
    return {
      type: ReceiptActions.FIND_COMPLETED_RECEIPTS
    };
  }

  static FIND_COMPLETED_RECEIPTS_SUCCESS = '[Receipt] Find Completed Receipts Success';

  findCompletedReceiptsSuccess(receipts): Action {
    console.log("findCompletedReceiptsSuccess");
    return {
      type: ReceiptActions.FIND_COMPLETED_RECEIPTS_SUCCESS,
      payload: receipts
    };
  }


  static FIND_ASSIGNED_RECEIPT_TASKS = '[Receipt] Find Assigned Receipt Tasks';

  findAssignedReceiptTasks(): Action {
    return {
      type: ReceiptActions.FIND_ASSIGNED_RECEIPT_TASKS
    };
  }

  static FIND_ASSIGNED_RECEIPT_TASKS_SUCCESS = '[Receipt] Find Assigned Receipt Tasks Success';

  findAssignedReceiptTasksSuccess(tasks): Action {
    console.log("findAssignedReceiptTasksSuccess");
    return {
      type: ReceiptActions.FIND_ASSIGNED_RECEIPT_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_POOLED_RECEIPT_TASKS = '[Receipt] Find Pooled Receipt Tasks';

  findPooledReceiptTasks(): Action {
    return {
      type: ReceiptActions.FIND_POOLED_RECEIPT_TASKS
    };
  }

  static FIND_POOLED_RECEIPT_TASKS_SUCCESS = '[Receipt] Find Pooled Receipt Tasks Success';

  findPooledReceiptTasksSuccess(tasks): Action {
    console.log("findAssignedReceiptTasksSuccess");
    return {
      type: ReceiptActions.FIND_POOLED_RECEIPT_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_RECEIPT_TASK_BY_TASK_ID = '[Receipt] Find Receipt Task By Task Id';

  findReceiptTaskByTaskId(taskId): Action {
    console.log("findReceiptTaskByTaskId");
    return {
      type: ReceiptActions.FIND_RECEIPT_TASK_BY_TASK_ID,
      payload: taskId
    };
  }

  static FIND_RECEIPT_TASK_BY_TASK_ID_SUCCESS = '[Receipt] Find Receipt Task By Task Id Success';

  findReceiptTaskByTaskIdSuccess(task): Action {
    console.log("findReceiptTaskByTaskIdSuccess");
    return {
      type: ReceiptActions.FIND_RECEIPT_TASK_BY_TASK_ID_SUCCESS,
      payload: task
    };
  }

  static START_RECEIPT_TASK = '[Receipt] Start Receipt Task';

  startReceiptTask(receipt): Action {
    return {
      type: ReceiptActions.START_RECEIPT_TASK,
      payload: receipt
    };
  }

  static START_RECEIPT_TASK_SUCCESS = '[Receipt] Start Receipt Task Success';

  startReceiptTaskSuccess(referenceNo): Action {
    return {
      type: ReceiptActions.START_RECEIPT_TASK_SUCCESS,
      payload: referenceNo
    };
  }

  static COMPLETE_RECEIPT_TASK = '[Receipt] Complete Receipt Task';

  completeReceiptTask(receipt): Action {
    return {
      type: ReceiptActions.COMPLETE_RECEIPT_TASK,
      payload: receipt
    };
  }

  static COMPLETE_RECEIPT_TASK_SUCCESS = '[Receipt] Complete Receipt Task Success';

  completeReceiptTaskSuccess(message): Action {
    return {
      type: ReceiptActions.COMPLETE_RECEIPT_TASK_SUCCESS,
      payload: message
    };
  }

  static CLAIM_RECEIPT_TASK = '[Receipt] Assign Receipt Task';

  claimReceiptTask(receipt): Action {
    return {
      type: ReceiptActions.CLAIM_RECEIPT_TASK,
      payload: receipt
    };
  }

  static CLAIM_RECEIPT_TASK_SUCCESS = '[Receipt] Assign Receipt Task Success';

  claimReceiptTaskSuccess(task): Action {
    return {
      type: ReceiptActions.CLAIM_RECEIPT_TASK_SUCCESS,
      payload: task
    };
  }

  static RELEASE_RECEIPT_TASK = '[Receipt] Release Receipt Task';

  releaseReceiptTask(receipt): Action {
    return {
      type: ReceiptActions.RELEASE_RECEIPT_TASK,
      payload: receipt
    };
  }

  static RELEASE_RECEIPT_TASK_SUCCESS = '[Receipt] Release Receipt Task Success';

  releaseReceiptTaskSuccess(task): Action {
    return {
      type: ReceiptActions.RELEASE_RECEIPT_TASK_SUCCESS,
      payload: task
    };
  }

  static FIND_RECEIPT_BY_ID = '[Receipt] Find Receipt By Id';

  findReceiptById(id): Action {
    return {
      type: ReceiptActions.FIND_RECEIPT_BY_ID,
      payload: id
    };
  }

  static FIND_RECEIPT_BY_ID_SUCCESS = '[Receipt] Find Receipt By Id Success';

  findReceiptByIdSuccess(receipt): Action {
    return {
      type: ReceiptActions.FIND_RECEIPT_BY_ID_SUCCESS,
      payload: receipt
    };
  }

  static FIND_RECEIPT_BY_REFERENCE_NO = '[Receipt] Find Receipt By Reference No';

  findReceiptByReferenceNo(referenceNo): Action {
    return {
      type: ReceiptActions.FIND_RECEIPT_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_RECEIPT_BY_REFERENCE_NO_SUCCESS = '[Receipt] Find Receipt By Reference No Success';

  findReceiptByReferenceNoSuccess(receipt): Action {
    return {
      type: ReceiptActions.FIND_RECEIPT_BY_REFERENCE_NO_SUCCESS,
      payload: receipt
    };
  }

  static FIND_RECEIPT_ITEMS = '[Receipt] Find Receipt Items';

  findReceiptItems(receipt): Action {
    console.log("findReceiptItems for receipt: " + receipt);
    return {
      type: ReceiptActions.FIND_RECEIPT_ITEMS,
      payload: receipt
    };
  }

  static FIND_RECEIPT_ITEMS_SUCCESS = '[Receipt] Find Receipt Items Success';

  findReceiptItemsSuccess(items): Action {
    console.log("findReceiptItemsSuccess");
    return {
      type: ReceiptActions.FIND_RECEIPT_ITEMS_SUCCESS,
      payload: items
    };
  }


  static UPDATE_RECEIPT = '[Receipt] Update Receipt';

  updateReceipt(receipt): Action {
    return {
      type: ReceiptActions.UPDATE_RECEIPT,
      payload: receipt
    };
  }

  static UPDATE_RECEIPT_SUCCESS = '[Receipt] Update Receipt Success';

  updateReceiptSuccess(receipt): Action {
    return {
      type: ReceiptActions.UPDATE_RECEIPT_SUCCESS,
      payload: receipt
    };
  }

  static REMOVE_RECEIPT = '[Receipt] Remove Receipt';

  removeReceipt(receipt): Action {
    return {
      type: ReceiptActions.REMOVE_RECEIPT,
      payload: receipt
    };
  }

  static REMOVE_RECEIPT_SUCCESS = '[Receipt] Remove Receipt Success';

  removeReceiptSuccess(receipt): Action {
    return {
      type: ReceiptActions.REMOVE_RECEIPT_SUCCESS,
      payload: receipt
    };
  }

  static ADD_RECEIPT_ITEM = '[Receipt] Add Receipt Item';

  addReceiptItem(receipt, item): Action {
    return {
      type: ReceiptActions.ADD_RECEIPT_ITEM,
      payload: {receipt:receipt, item:item}
    };
  }

  static ADD_RECEIPT_ITEM_SUCCESS = '[Receipt] Add Receipt Item Success';

  addReceiptItemSuccess(message): Action {
    return {
      type: ReceiptActions.ADD_RECEIPT_ITEM_SUCCESS,
      payload: message
    };
  }

  static DELETE_RECEIPT_ITEM = '[Receipt] Delete Receipt Item';

  deleteReceiptItem(receipt, item): Action {
    return {
      type: ReceiptActions.DELETE_RECEIPT_ITEM,
      payload: {receipt:receipt, item:item}
    };
  }

  static DELETE_RECEIPT_ITEM_SUCCESS = '[Receipt] Add Receipt Item Success';

  deleteReceiptItemSuccess(message): Action {
    return {
      type: ReceiptActions.DELETE_RECEIPT_ITEM_SUCCESS,
      payload: message
    };
  }

  static UPDATE_RECEIPT_ITEM = '[Receipt] Update Receipt Item';

  updateReceiptItem(receipt, item): Action {
    return {
      type: ReceiptActions.UPDATE_RECEIPT_ITEM,
      payload: {receipt:receipt, item:item}
    };
  }

  static UPDATE_RECEIPT_ITEM_SUCCESS = '[Receipt] Update Receipt Item Success';

  updateReceiptItemSuccess(message): Action {
    return {
      type: ReceiptActions.UPDATE_RECEIPT_ITEM_SUCCESS,
      payload: message
    };
  }

}
