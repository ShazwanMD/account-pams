import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class WaiverApplicationActions {

  static FIND_ASSIGNED_WAIVER_APPLICATION_TASKS = '[WaiverApplication] Find Assigned WaiverApplication Tasks';

  findAssignedWaiverApplicationTasks(): Action {
    return {
      type: WaiverApplicationActions.FIND_ASSIGNED_WAIVER_APPLICATION_TASKS
    };
  }

  static FIND_ASSIGNED_WAIVER_APPLICATION_TASKS_SUCCESS = '[WaiverApplication] Find Assigned WaiverApplication Tasks Success';

  findAssignedWaiverApplicationTasksSuccess(tasks): Action {
    console.log("findAssignedWaiverApplicationTasksSuccess");
    return {
      type: WaiverApplicationActions.FIND_ASSIGNED_WAIVER_APPLICATION_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_POOLED_WAIVER_APPLICATION_TASKS = '[WaiverApplication] Find Pooled WaiverApplication Tasks';

  findPooledWaiverApplicationTasks(): Action {
    return {
      type: WaiverApplicationActions.FIND_POOLED_WAIVER_APPLICATION_TASKS
    };
  }

  static FIND_POOLED_WAIVER_APPLICATION_TASKS_SUCCESS = '[WaiverApplication] Find Pooled WaiverApplication Tasks Success';

  findPooledWaiverApplicationTasksSuccess(tasks): Action {
    console.log("findPooledWaiverApplicationTasksSuccess");
    return {
      type: WaiverApplicationActions.FIND_POOLED_WAIVER_APPLICATION_TASKS_SUCCESS,
      payload: tasks
    };
  }

  static FIND_WAIVER_APPLICATION_TASK_BY_TASK_ID = '[WaiverApplication] Find WaiverApplication Task By Task Id';

  findWaiverApplicationTaskByTaskId(taskId): Action {
    console.log("findWaiverApplicationTaskByTaskId");
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_TASK_BY_TASK_ID,
      payload: taskId
    };
  }

  static FIND_WAIVER_APPLICATION_TASK_BY_TASK_ID_SUCCESS = '[WaiverApplication] Find WaiverApplication Task By Task Id Success';

  findWaiverApplicationTaskByTaskIdSuccess(task): Action {
    console.log("findWaiverApplicationTaskByTaskIdSuccess");
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_TASK_BY_TASK_ID_SUCCESS,
      payload: task
    };
  }

  static START_WAIVER_APPLICATION_TASK = '[WaiverApplication] Start WaiverApplication Task';

  startWaiverApplicationTask(waiverApplication): Action {
    return {
      type: WaiverApplicationActions.START_WAIVER_APPLICATION_TASK,
      payload: waiverApplication
    };
  }

  static START_WAIVER_APPLICATION_TASK_SUCCESS = '[WaiverApplication] Start WaiverApplication Task Success';

  startWaiverApplicationTaskSuccess(task): Action {
    return {
      type: WaiverApplicationActions.START_WAIVER_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static COMPLETE_WAIVER_APPLICATION_TASK = '[WaiverApplication] Complete WaiverApplication Task';

  completeWaiverApplicationTask(invoice): Action {
    return {
      type: WaiverApplicationActions.COMPLETE_WAIVER_APPLICATION_TASK,
      payload: invoice
    };
  }

  static COMPLETE_WAIVER_APPLICATION_TASK_SUCCESS = '[WaiverApplication] Complete WaiverApplication Task Success';

  completeWaiverApplicationTaskSuccess(task): Action {
    return {
      type: WaiverApplicationActions.COMPLETE_WAIVER_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static CLAIM_WAIVER_APPLICATION_TASK = '[WaiverApplication] Claim WaiverApplication Task';

  claimWaiverApplicationTask(invoice): Action {
    return {
      type: WaiverApplicationActions.CLAIM_WAIVER_APPLICATION_TASK,
      payload: invoice
    };
  }

  static CLAIM_WAIVER_APPLICATION_TASK_SUCCESS = '[WaiverApplication] Claim WaiverApplication Task Success';

  claimWaiverApplicationTaskSuccess(task): Action {
    return {
      type: WaiverApplicationActions.CLAIM_WAIVER_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static RELEASE_WAIVER_APPLICATION_TASK = '[WaiverApplication] Release WaiverApplication Task';

  releaseWaiverApplicationTask(invoice): Action {
    return {
      type: WaiverApplicationActions.RELEASE_WAIVER_APPLICATION_TASK,
      payload: invoice
    };
  }

  static RELEASE_WAIVER_APPLICATION_TASK_SUCCESS = '[WaiverApplication] Release WaiverApplication Task Success';

  releaseWaiverApplicationTaskSuccess(task): Action {
    return {
      type: WaiverApplicationActions.RELEASE_WAIVER_APPLICATION_TASK_SUCCESS,
      payload: task
    };
  }

  static FIND_WAIVER_APPLICATION_BY_ID = '[WaiverApplication] Find WaiverApplication By Id';

  findWaiverApplicationById(id): Action {
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_ID,
      payload: id
    };
  }

  static FIND_COMPLETED_WAIVER_APPLICATIONS = '[WaiverApplication] Find Completed WaiverApplications';

  findCompletedWaiverApplications(): Action {
    return {
      type: WaiverApplicationActions.FIND_COMPLETED_WAIVER_APPLICATIONS,
    };
  }

  static FIND_COMPLETED_WAIVER_APPLICATIONS_SUCCESS = '[WaiverApplication] Find Completed WaiverApplications Success';

  findCompletedWaiverApplicationsSuccess(invoices): Action {
    console.log('findCompletedInvoicesSuccess');
    return {
      type: WaiverApplicationActions.FIND_COMPLETED_WAIVER_APPLICATIONS_SUCCESS,
      payload: invoices,
    };
  }

  static FIND_WAIVER_APPLICATION_BY_ID_SUCCESS = '[WaiverApplication] Find WaiverApplication By Id Success';

  findWaiverApplicationByIdSuccess(invoice): Action {
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_ID_SUCCESS,
      payload: invoice
    };
  }

  static FIND_WAIVER_APPLICATION_BY_REFERENCE_NO = '[WaiverApplication] Find WaiverApplication By Reference No';

  findWaiverApplicationByReferenceNo(referenceNo): Action {
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_WAIVER_APPLICATION_BY_REFERENCE_NO_SUCCESS = '[WaiverApplication] Find WaiverApplication By Reference No Success';

  findWaiverApplicationByReferenceNoSuccess(invoice): Action {
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_REFERENCE_NO_SUCCESS,
      payload: invoice
    };
  }

  static FIND_WAIVER_APPLICATION_ITEMS = '[WaiverApplication] Find WaiverApplication Items';

  findWaiverApplicationItems(invoice): Action {
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_ITEMS,
      payload: invoice
    };
  }

  static FIND_WAIVER_APPLICATION_ITEMS_SUCCESS = '[WaiverApplication] Find WaiverApplication Items Success';

  findWaiverApplicationItemsSuccess(items): Action {
    console.log("findWaiverApplicationTransactionsSuccess");
    return {
      type: WaiverApplicationActions.FIND_WAIVER_APPLICATION_ITEMS_SUCCESS,
      payload: items
    };
  }

  static FIND_ARCHIVED_WAIVER_APPLICATIONS = '[WaiverApplication] Find Archived WaiverApplications';

  findArchivedWaiverApplications(): Action {
    return {
      type: WaiverApplicationActions.FIND_ARCHIVED_WAIVER_APPLICATIONS,
    };
  }

  static FIND_ARCHIVED_WAIVER_APPLICATIONS_SUCCESS = '[WaiverApplication] Find Archived WaiverApplications Success';

  findArchivedWaiverApplicationsSuccess(invoices): Action {
    console.log('findArchivedWaiverApplicationsSuccess');
    return {
      type: WaiverApplicationActions.FIND_ARCHIVED_WAIVER_APPLICATIONS_SUCCESS,
      payload: invoices,
    };
  }

  static UPDATE_WAIVER_APPLICATION = '[WaiverApplication] Update WaiverApplication';

  updateWaiverApplication(waiverApplication): Action {
    return {
      type: WaiverApplicationActions.UPDATE_WAIVER_APPLICATION,
      payload: waiverApplication
    };
  }

  static UPDATE_WAIVER_APPLICATION_SUCCESS = '[WaiverApplication] Update WaiverApplication Success';

  updateWaiverApplicationSuccess(message): Action {
    return {
      type: WaiverApplicationActions.UPDATE_WAIVER_APPLICATION_SUCCESS,
      payload: message
    };
  }

  static REMOVE_WAIVER_APPLICATION = '[WaiverApplication] Remove WaiverApplication';

  removeWaiverApplication(invoice): Action {
    return {
      type: WaiverApplicationActions.REMOVE_WAIVER_APPLICATION,
      payload: invoice
    };
  }

  static REMOVE_WAIVER_APPLICATION_SUCCESS = '[WaiverApplication] Remove WaiverApplication Success';

  removeWaiverApplicationSuccess(invoice): Action {
    return {
      type: WaiverApplicationActions.REMOVE_WAIVER_APPLICATION_SUCCESS,
      payload: invoice
    };
  }
  
  static REMOVE_WAIVER_APPLICATION_TASK = '[WaiverApplication] Remove WaiverApplication Task';
  removeWaiverApplicationTask(waiverApplicationTask): Action {
      return {
          type: WaiverApplicationActions.REMOVE_WAIVER_APPLICATION_TASK,
          payload: waiverApplicationTask,
    };
  }

  static REMOVE_WAIVER_APPLICATION_TASK_SUCCESS = '[WaiverApplication] Remove WaiverApplication Task Success';
  removeWaiverApplicationTaskSuccess(task): Action {
      return {
          type: WaiverApplicationActions.REMOVE_WAIVER_APPLICATION_TASK_SUCCESS,
          payload: task,
    };
  }
}
