import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class KnockoffActions {
    
    static FIND_KNOCKOFFS = '[Knockoff] Find Knockoffs';

    findKnockoffs(): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFFS
      };
    }

    static FIND_KNOCKOFFS_SUCCESS = '[Knockoff] Find Knockoffs Success';

    findKnockoffsSuccess(knockoff): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFFS_SUCCESS,
        payload: knockoff
      };
    }
      
    static FIND_KNOCKOFF_BY_REFERENCE_NO = '[Knockoff] Find Knockoff By Reference No';

    findKnockoffByReferenceNo(referenceNo): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO,
        payload: referenceNo
      };
    }

    static FIND_KNOCKOFF_BY_REFERENCE_NO_SUCCESS = '[Knockoff] Find Knockoff By Reference No Success';

    findKnockoffByReferenceNoSuccess(knockoff): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO_SUCCESS,
        payload: knockoff
      };
    }
    
    static FIND_KNOCKOFF_TASK_BY_TASK_ID = '[Knockoff] Find Knockoff Task By Task Id';

    findKnockoffTaskByTaskId(taskId): Action {
      console.log('findKnockoffTaskByTaskId');
      return {
        type: KnockoffActions.FIND_KNOCKOFF_TASK_BY_TASK_ID,
        payload: taskId,
      };
    }

    static FIND_KNOCKOFF_TASK_BY_TASK_ID_SUCCESS = '[Knockoff] Find Knockoff Task By Task Id Success';

    findKnockoffTaskByTaskIdSuccess(task): Action {
      console.log('findKnockoffTaskByTaskIdSuccess');
      return {
        type: KnockoffActions.FIND_KNOCKOFF_TASK_BY_TASK_ID_SUCCESS,
        payload: task,
      };
    }
    
    static START_KNOCKOFF_TASK = '[Knockoff] Start Knockoff Task';
    startKnockoffTask(knockoff, referenceNo): Action {
        console.log("kncokoff dlm action: " + knockoff);
      return {
        type: KnockoffActions.START_KNOCKOFF_TASK,
        payload: {knockoff, referenceNo}
      };
    }

    static START_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Start Knockoff Task Success';

    startKnockoffTaskSuccess(referenceNo): Action {
      return {
        type: KnockoffActions.START_KNOCKOFF_TASK_SUCCESS,
        payload: referenceNo,
      };
    }

    static COMPLETE_KNOCKOFF_TASK = '[Knockoff] Complete Knockoff Task';

    completeKnockoffTask(knockoff): Action {
      return {
        type: KnockoffActions.COMPLETE_KNOCKOFF_TASK,
        payload: knockoff,
      };
    }

    static COMPLETE_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Complete Knockoff Task Success';

    completeKnockoffTaskSuccess(message): Action {
      return {
        type: KnockoffActions.COMPLETE_KNOCKOFF_TASK_SUCCESS,
        payload: message,
      };
    }

    static CLAIM_KNOCKOFF_TASK = '[Knockoff] Assign Knockoff Task';

    claimKnockoffTask(knockoff): Action {
      return {
        type: KnockoffActions.CLAIM_KNOCKOFF_TASK,
        payload: knockoff,
      };
    }

    static CLAIM_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Assign Knockoff Task Success';

    claimKnockoffTaskSuccess(message): Action {
      return {
        type: KnockoffActions.CLAIM_KNOCKOFF_TASK_SUCCESS,
        payload: message,
      };
    }

    static RELEASE_KNOCKOFF_TASK = '[Knockoff] Release Knockoff Task';

    releaseKnockoffTask(knockoff): Action {
      return {
        type: KnockoffActions.RELEASE_KNOCKOFF_TASK,
        payload: knockoff,
      };
    }

    static RELEASE_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Release Knockoff Task Success';

    releaseKnockoffTaskSuccess(message): Action {
      return {
        type: KnockoffActions.RELEASE_KNOCKOFF_TASK_SUCCESS,
        payload: message,
      };
    }
    
    static FIND_COMPLETED_KNOCKOFFS = '[Knockoff] Find Completed Knockoff';

    findCompletedKnockoffs(): Action {
      return {
        type: KnockoffActions.FIND_COMPLETED_KNOCKOFFS,
      };
    }

    static FIND_COMPLETED_KNOCKOFFS_SUCCESS = '[Knockoff] Find Completed Knockoff Success';

    findCompletedKnockoffsSuccess(knockoffs): Action {
      console.log('findCompletedKnockoffsSuccess');
      return {
        type: KnockoffActions.FIND_COMPLETED_KNOCKOFFS_SUCCESS,
        payload: knockoffs,
      };
    }

    static FIND_ARCHIVED_KNOCKOFFS = '[Knockoff] Find Archived Knockoff';

    findArchivedknockoffs(): Action {
      return {
        type: KnockoffActions.FIND_ARCHIVED_KNOCKOFFS,
      };
    }

    static FIND_ARCHIVED_KNOCKOFFS_SUCCESS = '[Knockoff] Find Archived Knockoff Success';

    findArchivedknockoffsSuccess(knockoffs): Action {
      console.log('findArchivedInvoicesSuccess');
      return {
        type: KnockoffActions.FIND_ARCHIVED_KNOCKOFFS_SUCCESS,
        payload: knockoffs,
      };
    }

    static FIND_ASSIGNED_KNOCKOFF_TASKS = '[Knockoff] Find Assigned Knockoff Tasks';

    findAssignedKnockoffTasks(): Action {
        console.log('findAssignedKnockoffTasks');
      return {
        type: KnockoffActions.FIND_ASSIGNED_KNOCKOFF_TASKS,
      };
    }

    static FIND_ASSIGNED_KNOCKOFF_TASKS_SUCCESS = '[Knockoff] Find Assigned Knockoff Tasks Success';

    findAssignedKnockoffTasksSuccess(tasks): Action {
      console.log('findAssignedKnockoffTasksSuccess');
      console.log('action knockoff' + tasks);
      return {
        type: KnockoffActions.FIND_ASSIGNED_KNOCKOFF_TASKS_SUCCESS,
        payload: tasks,
      };
    }

    static FIND_POOLED_KNOCKOFF_TASKS = '[Knockoff] Find Pooled Knockoff Tasks';

    findPooledKnockoffTasks(): Action {
      return {
        type: KnockoffActions.FIND_POOLED_KNOCKOFF_TASKS,
      };
    }

    static FIND_POOLED_KNOCKOFF_TASKS_SUCCESS = '[Knockoff] Find Pooled Knockoff Tasks Success';

    findPooledKnockoffTasksSuccess(knockoffs): Action {
      console.log('findPooledKnockoffTasksSuccess');
      return {
        type: KnockoffActions.FIND_POOLED_KNOCKOFF_TASKS_SUCCESS,
        payload: knockoffs,
      };
    }
//    static SAVE_KNOCKOFF = '[Knockoff] Save Knockoff';
//
//    saveKnockoff(knockoff, payment): Action {
//      return {
//        type: KnockoffActions.SAVE_KNOCKOFF,
//        payload: {knockoff: knockoff, payment: payment},
//      };
//    }
//
//    static SAVE_KNOCKOFF_SUCCESS = '[Knockoff] Save Knockoff Success';
//
//    saveKnockoffSuccess(knockoff): Action {
//      return {
//        type: KnockoffActions.SAVE_KNOCKOFF_SUCCESS,
//        payload: knockoff
//      };
//    }
}